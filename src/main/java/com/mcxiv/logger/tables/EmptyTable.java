package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;

import java.util.ArrayList;

class EmptyTable extends TableAdaptor {

    ArrayList<Integer> rowWidth;
    ArrayList<String[]> rows;

    String title = null;

    public EmptyTable() {
        rowWidth = new ArrayList<>();
        rows = new ArrayList<>();
    }

    /**
     * <p>
     * <b>Important</b> - The number of inputs should be at least 1 element big.
     * The rest all are ignored.
     * </p>
     */
    @Override
    public Table format(String... codes) {
        return this;
    }

    @Override
    public Table formatTitle(String code) {
        return this;
    }

    @Override
    public Table formatHead(String... codes) {
        return this;
    }


    @Override
    public Table title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Table head(String... msg) {
        rows.add(msg);
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
    public int getWidth() {
        return rowWidth.stream().reduce(0, Integer::sum) + rowWidth.size() * 2;
    }

    @Override
    public void create(FLog mainLog) {
        if (level != null && !level.accepted()) return;


        Packet packet = mainLog.newPacket();

        StringBuilder table = new StringBuilder();

        if (title != null) packet.prtf(":: :n:").consume(title);

        for (String[] row : rows) {
            for (int i = 0; i < row.length; i++)
                packet.prtf(":: :%-" + rowWidth.get(i) + "s: ::").consume(row[i]);
            packet.raw("\n");
        }

        packet.consume();

    }
}


    /*@Override
    public String create() {

        StringBuilder table = new StringBuilder();

        int w = rowWidth.size() * 2; // number of cells * (number of padding spaces provided per cell + border character per cell) + one extra border character which lyes unpaired with the cells.
        for (Integer rw : rowWidth) w += rw; // adding up individual row widths to w
        // now, w = table width.


        // If there is a title specified, append it to header matching the table width.
        if (title != null) table.append(" ").append(title).append("\n");


        // Adjusting each row element to fit it's respective row width.
        for (String[] row : rows)
            for (int j = 0; j < row.length; j++)
                row[j] = String.format(" %" + rowWidth.get(j) + "s ", row[j]);

        // for every row {filling in values of that row into row form and then appending it after header}
        for (String[] row : rows) {
            for (String cell : row)
                table.append(cell);
            table.append("\n");
        }

        return table.toString();
    }*/