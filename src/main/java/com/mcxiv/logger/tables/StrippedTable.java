package com.mcxiv.logger.tables;

import com.mcxiv.logger.decorations.Decoration;

import java.util.ArrayList;
import java.util.Arrays;

class StrippedTable extends TableAdaptor {

    ArrayList<Integer> rowWidth;
    ArrayList<String[]> rows;

    String title = null;
    String[] header;

    Decoration titleFormat = null;
    Decoration headFormat1 = null;
    Decoration headFormat2 = null;
    Decoration rowFormat1 = null;
    Decoration rowFormat2 = null;

    static Decoration TITLE_FORMAT = Decoration.getDecoration(":@eb:");
    static Decoration HEAD_FORMAT1 = Decoration.getDecoration(":@bb:");
    static Decoration HEAD_FORMAT2 = Decoration.getDecoration(":@cb:");
    static Decoration ROW_FORMAT1 = Decoration.getDecoration(":@d:");
    static Decoration ROW_FORMAT2 = Decoration.getDecoration(":@e:");

    public StrippedTable() {
        rowWidth = new ArrayList<>();
        rows = new ArrayList<>();
    }

    /**
     * <p>
     * <b>Important</b> - The number of inputs should be at least 2 elements big.
     * The first to corresponds to Row Format 1 and the second Row Format 2, the rest all are ignored.
     * </p>
     */
    @Override
    public Table format(String... codes) {
        rowFormat1 = Decoration.getDecoration(codes[0]);
        rowFormat2 = Decoration.getDecoration(codes[1]);
        return this;
    }

    @Override
    public Table formatTitle(String code) {
        titleFormat = Decoration.getDecoration(code);
        return this;
    }

    /**
     * <p>
     * <b>Important</b> - The number of inputs should be at least 2 elements big.
     * The first to corresponds to Head Format 1 and Head Format 2, the rest all are ignored.
     * </p>
     */
    @Override
    public Table formatHead(String... codes) {
        headFormat1 = Decoration.getDecoration(codes[0]);
        headFormat2 = Decoration.getDecoration(codes[1]);
        return this;
    }

    @Override
    public Table title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Table head(String... msg) {
        header = msg;
        for (String title : header) rowWidth.add(title.length());
        return this;
    }

    @Override
    public Table row(String... msg) {
        rows.add(msg);
        for (int i = 0; i < msg.length; i++)
            if (rowWidth.get(i) < msg[i].length())
                rowWidth.set(i, msg[i].length());
        return this;
    }

    @Override
    public Table iter(int a, int b, Iterator... its) {

        for (int i = a; i < b; i++) { // for each row

            String[] msg = new String[its.length]; // every cell

            for (int j = 0; j < msg.length; j++)
                msg[j] = its[j].consume(i).toString(); // for each cell; jth cell on ith row

            rows.add(msg);

            for (int j = 0; j < its.length; j++) // for each cell/column updating row width
                if (rowWidth.get(j) < msg[j].length())
                    rowWidth.set(j, msg[j].length());

        }
        return this;
    }

    @Override
    public Table iter(int a, int b, int c, Iterator... its) {

        for (int i = a; i < b; i += c) { // for each row

            String[] msg = new String[its.length]; // every cell

            for (int j = 0; j < msg.length; j++)
                msg[j] = its[j].consume(i).toString(); // for each cell; jth cell on ith row

            rows.add(msg);

            for (int j = 0; j < its.length; j++) // for each cell/column updating row width
                if (rowWidth.get(j) < msg[j].length())
                    rowWidth.set(j, msg[j].length());

        }
        return this;
    }

    @SafeVarargs
    @Override
    public final <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its) {

        for (int i = 0; i < main.length; i += groupSize) { // For every bunch <-> For each row

            String[] msg = new String[its.length]; // every cell

            for (int j = 0; j < its.length; j++) // for each cell
                msg[j] = its[j].consume(i / groupSize, Arrays.copyOfRange(main, i, i + groupSize)).toString();

            rows.add(msg);

            for (int j = 0; j < its.length; j++) // for each cell/column updating row width
                if (rowWidth.get(j) < msg[j].length())
                    rowWidth.set(j, msg[j].length());

        }

        return this;
    }

    @Override
    public String create() {

        // Initialising all formats, if not specified, the default is used.
        if (titleFormat == null) titleFormat = TITLE_FORMAT;
        if (headFormat1 == null) headFormat1 = HEAD_FORMAT1;
        if (headFormat2 == null) headFormat2 = HEAD_FORMAT2;
        if (rowFormat1 == null) rowFormat1 = ROW_FORMAT1;
        if (rowFormat2 == null) rowFormat2 = ROW_FORMAT2;

        // Row Form, here, according to element count in one row a form is created in which cell data is entered in a later process.
        StringBuilder row_form_sb = new StringBuilder();
        for (int i = 0; i < rowWidth.size(); i++)
            row_form_sb.append((i % 2 == 0 ? rowFormat1 : rowFormat2).decorate(" %" + rowWidth.get(i) + "s "));
        String row_form = row_form_sb.append("\n").toString();

        StringBuilder head_form_sb = new StringBuilder(); // A similar thing but for header only.

        // If there is a title specified, append it to header matching the table width.
        if (title != null) {
            int w = rowWidth.size() * 2; // number of cells * number of padding spaces provided per cell
            for (Integer rw : rowWidth) w += rw; // adding up individual row widths to w
            // now, w = table width.

            // calculating count of all the 'double percentage', ie, %%
            int persc = 0;
            for (int i = 0; i < title.length(); i++)
                if (title.substring(i).startsWith("%%")) {
                    persc++;
                    i++;
                }

            StringBuilder form = new StringBuilder(String.format(" %-" + (w - 2) + "s ", title));
            for (int i = 0; i < persc; i++)
                form.append(" "); // adding in all the spaces required to fill up the space deducted by conversion of %% to % when format is used.

            head_form_sb.append(titleFormat.decorate(form.toString())).append("\n");
        }

        for (int i = 0; i < rowWidth.size(); i++) // creating the form for header.
            head_form_sb.append((i % 2 == 0 ? headFormat1 : headFormat2).decorate(" %" + rowWidth.get(i) + "s "));

        // filling in values of header into head form
        StringBuilder table = new StringBuilder(String.format(head_form_sb.append("\n").toString(), header));

        // for every row {filling in values of that row into row form and then appending it after header}
        for (String[] row : rows) table.append(String.format(row_form, row));

        return table.toString();
    }
}
