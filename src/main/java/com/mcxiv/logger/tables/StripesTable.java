package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;

import java.util.ArrayList;

class StripesTable extends TableAdaptor {

    ArrayList<Integer> rowWidth;
    ArrayList<String[]> rows;

    String title = null;
    String[] header;

    String titleFormat = null;
    String headFormat1 = null;
    String headFormat2 = null;
    String rowFormat1 = null;
    String rowFormat2 = null;

    static String TITLE_FORMAT = "@eb";
    static String HEAD_FORMAT1 = "@bb";
    static String HEAD_FORMAT2 = "@cb";
    static String ROW_FORMAT1 = "@d";
    static String ROW_FORMAT2 = "@e";

    public StripesTable() {
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
        rowFormat1 = codes[0];
        rowFormat2 = codes[1];
        return this;
    }

    @Override
    public Table formatTitle(String code) {
        titleFormat = code;
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
        headFormat1 = codes[0];
        headFormat2 = codes[1];
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
    public int getWidth() {
        return rowWidth.stream().reduce(0, Integer::sum) + rowWidth.size() * 2;
    }

    @Override
    public void create(FLog mainLog) {
        if(level!=null&&!level.accepted())return;

        Packet packet = mainLog.newPacket();

        // Initialising all formats, if not specified, the default is used.
        if (titleFormat == null) titleFormat = TITLE_FORMAT;
        if (headFormat1 == null) headFormat1 = HEAD_FORMAT1;
        if (headFormat2 == null) headFormat2 = HEAD_FORMAT2;
        if (rowFormat1 == null) rowFormat1 = ROW_FORMAT1;
        if (rowFormat2 == null) rowFormat2 = ROW_FORMAT2;

        if (title != null)
            packet.prtf(":n%" + rowWidth.stream().reduce(0, Integer::sum) + "s" + titleFormat + ":").consume(title);

        for (int i = 0; i < header.length; i++)
            packet.prtf(":: :%-" + rowWidth.get(i) + "s" + (i % 2 == 0 ? headFormat1 : headFormat2) + ": ::").consume(header[i]);
        packet.raw("\n");

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++)
                packet.prtf(":: :%-" + rowWidth.get(i) + "s" + (i % 2 == 0 ? rowFormat1 : rowFormat2) + ": ::").consume(row[i]);
            packet.raw("\n");
        }

        packet.consume();
    }

}

/*@Override
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
        StringBuilder table = new StringBuilder(String.format(head_form_sb.append("\n").toString(), (Object[]) header));

        // for every row {filling in values of that row into row form and then appending it after header}
        for (String[] row : rows) table.append(String.format(row_form, (Object[]) row));

        return table.toString();
    }*/