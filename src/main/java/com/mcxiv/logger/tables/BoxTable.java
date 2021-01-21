package com.mcxiv.logger.tables;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.tools.Environment;

import java.util.ArrayList;
import java.util.Arrays;

class BoxTable implements Table {

    ArrayList<Integer> rowWidth;
    ArrayList<String[]> rows;

    String title = null;
    String[] header;

    Decoration titleFormat = null;
    Decoration headFormat = null;
    Decoration rowFormat = null;

    static Decoration TITLE_FORMAT = Environment.getDecoration(Box.DP + ":: :b: ::" + Box.DP);
    static Decoration HEAD_FORMAT = Environment.getDecoration(Box.DP + ":: :b: ::" + Box.DP, ":: :b~: ::" + Box.DP);
    static Decoration ROW_FORMAT = Environment.getDecoration(Box.DP + ":: : : ::" + Box.DP, ":: :~: ::" + Box.DP);

    public BoxTable() {
        rowWidth = new ArrayList<>();
        rows = new ArrayList<>();
    }

    /**
     * <p>
     * <b>Important</b> - The number of inputs should be at least 2 elements big.
     * The first to corresponds to Column 1 and the second Column 2, the rest all are ignored.
     * </p>
     */
    @Override
    public Table format(String... codes) {
        rowFormat = Environment.getDecoration(codes[0], codes[1]);
        return this;
    }

    @Override
    public Table formatTitle(String code) {
        titleFormat = Environment.getDecoration(code);
        return this;
    }

    /**
     * <p>
     * <b>Important</b> - The number of inputs should be at least 1 elements big.
     * The first to corresponds to Head Format, the rest all are ignored.
     * </p>
     */
    @Override
    public Table formatHead(String... codes) {
        headFormat = Environment.getDecoration(codes[0]);
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
        for (String title : msg) rowWidth.add(title.length());
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

    @Override
    public <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its) {
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
        if (headFormat == null) headFormat = HEAD_FORMAT;
        if (rowFormat == null) rowFormat = ROW_FORMAT;


        StringBuilder table = new StringBuilder();

        int w = rowWidth.size() * 3 + 1; // number of cells * (number of padding spaces provided per cell + border character per cell) + one extra border character which lyes unpaired with the cells.
        for (Integer rw : rowWidth) w += rw; // adding up individual row widths to w
        // now, w = table width.


        // If there is a title specified, append it to header matching the table width.
        if (title != null) {

            table.append(Box.TR_DC);
            for (int i = 0; i < w - 2; i++) table.append(Box.DB);
            table.append(Box.TL_DC).append("\n");

            // calculating count of all the 'double percentage', ie, %%
            int persc = 0;
            for (int i = 0; i < title.length(); i++)
                if (title.substring(i).startsWith("%%")) {
                    persc++;
                    i++;
                }

            StringBuilder form = new StringBuilder();
            form.append(String.format("%-" + (w - 4) + "s", title));
            for (int i = 0; i < persc; i++)
                form.append(" "); // adding in all the spaces required to fill up the space deducted by conversion of %% to % when format is used.

            table.append(titleFormat.decorate(form.toString())).append("\n");

            table.append(Box.L_DC);
            for (int i = 0; i < rowWidth.size(); i++) {
                for (int j = 0; j < rowWidth.get(i) + 2; j++) table.append(Box.DB);
                if (i != rowWidth.size() - 1) table.append(Box.B_DC);
            }
            table.append(Box.R_DC).append("\n");

        } else {
            table.append(Box.TR_DC);
            for (int i = 0; i < rowWidth.size(); i++) {
                for (int j = 0; j < rowWidth.get(i) + 2; j++) table.append(Box.DB);
                if (i != rowWidth.size() - 1) table.append(Box.B_DC);
            }
            table.append(Box.TL_DC).append("\n");
        }

        // Adjusting each header element to fit it's respective row width.
        for (int i = 0; i < header.length; i++)
            header[i] = String.format("%" + rowWidth.get(i) + "s", header[i]);

        // filling in values of header into head form
        table.append(headFormat.decorate(header)).append("\n");

        table.append(Box.L_DC);
        for (int i = 0; i < rowWidth.size(); i++) {
            for (int j = 0; j < rowWidth.get(i) + 2; j++) table.append(Box.DB);
            if (i != rowWidth.size() - 1) table.append(Box.A_DC);
        }
        table.append(Box.R_DC).append("\n");

        // Adjusting each row element to fit it's respective row width.
        for (String[] row : rows)
            for (int j = 0; j < row.length; j++)
                row[j] = String.format("%" + rowWidth.get(j) + "s", row[j]);

        // for every row {filling in values of that row into row form and then appending it after header}
        for (String[] row : rows) table.append(rowFormat.decorate(row)).append("\n");

        table.append(Box.BR_DC);
        for (int i = 0; i < rowWidth.size(); i++) {
            for (int j = 0; j < rowWidth.get(i) + 2; j++) table.append(Box.DB);
            if (i != rowWidth.size() - 1) table.append(Box.T_DC);
        }
        table.append(Box.BL_DC).append("\n");

        return table.toString();
    }
}