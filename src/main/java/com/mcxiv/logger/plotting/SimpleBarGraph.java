package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.tools.RandomColor;
import com.mcxiv.logger.util.Iterator;

class SimpleBarGraph implements Plot.BarGraph {

    String title = null;
    String[] xLabels = null;
    String[] yLabels = null;
    int[] values = null;

    double scale = 0.1;
    String bar = Box.B_F;

    @Override
    public Plot.BarGraph title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Plot.BarGraph XLabel(String... names) {
        xLabels = names;
        return this;
    }

    @Override
    public Plot.BarGraph XLabel(int a, int b, Iterator its) {
        xLabels = Iterator.toArray(a, b, 1, its);
        return this;
    }

    @Override
    public Plot.BarGraph YLabel(String... names) {
        yLabels = names;
        return this;
    }

    @Override
    public Plot.BarGraph YLabel(int a, int b, Iterator its) {
        yLabels = Iterator.toArray(a, b, 1, its);
        return this;
    }

    @Override
    public Plot.BarGraph values(int... values) {
        this.values = values;
        return this;
    }

    @Override
    public Plot.BarGraph values(int a, int b, Iterator its) {
        values = new int[b - a];
        for (int i = a, k = 0; i < b; i++, k++)
            values[k] = (int) its.consume(i);
        return this;
    }

    @Override
    public Plot.BarGraph charHeight(int h) {
        double highestBar = Integer.MIN_VALUE;
        for (int i : values) if (highestBar < i) highestBar = i;
        scale = (h - (title == null ? 1 : 2)) / highestBar;
        return this;
    }

    @Override
    public Plot.BarGraph scale(double h) {
        this.scale = h;
        return this;
    }

    @Override
    public Plot.BarGraph setBarType(String bar) {
        this.bar = bar;
        return this;
    }

    @Override
    public void create(FLog mainLog) {
        Packet packet = mainLog.newPacket();

        // Getting the highest value provided here!
        int highestBar = Integer.MIN_VALUE;
        for (int i : values) if (highestBar < i) highestBar = i;

        // Getting the height of the highest bar in char count.
        int charHeight = (int) (highestBar * scale);

        // Longest Y Label, applied before the y axis.
        int longestYLabel = Integer.MIN_VALUE;
        if (yLabels == null) yLabels = defaultYLables(highestBar, scale);
        for (String s : yLabels) if (longestYLabel < s.length()) longestYLabel = s.length();

        // Longest X Label, useful when number of titles exceed bar height,
        int longestXLabel = Integer.MIN_VALUE;
        if (xLabels == null) xLabels = defaultXLables(values);
        for (String s : xLabels) if (longestXLabel < s.length()) longestXLabel = s.length();


        // Initialising a set of random colors so that they match the bars and the x labels.
        // And setting xLabels to be of same length.
        String form = "%-" + longestXLabel + "s";
        String[] colors = new String[xLabels.length];
        for (int i = 0; i < xLabels.length; i++) {
            colors[i] = ":#" + new RandomColor().yieldHex() + ":";
            xLabels[i] = String.format(form, xLabels[i]);
        }


        //

        form = " %" + longestYLabel + "s \u2528 ";

        // Note that these operations are evaluated row wise.
        for (int dh = 0; dh < charHeight; dh++) { // for each row

            // Adding in yLabels, or, a space if not enough provided.
            // Note that these names/space are formatted to have a padding and an axis fragment.
            if (dh >= charHeight - yLabels.length) {
                packet.raw(String.format(form, yLabels[charHeight - dh - 1]));
            } else packet.raw(String.format(form, ""));

            // Putting in a Bar if bar is to be printed else a space.
            for (int dw = 0; dw < values.length; dw++) { // for each bar space
                if ((bar.equals(Box.B_F)) && ((int) (values[dw] * scale) == charHeight - dh - 1) && ((values[dw] * scale) % 1 > 0.5))
                    packet.prtf(colors[dw]).consume("\u2584");
                else if (values[dw] * scale >= charHeight - dh)
                    packet.prtf(colors[dw]).consume(bar);
                else for (int i = 0; i < bar.length(); i++) packet.raw(" ");
            }

            // Putting in all xLabels such that if they exceed the charheight they are printed appearing to exist in separate column.
            for (int j = 0; j < Math.ceil(xLabels.length / (float) charHeight); j++) {
                int s = dh + j * charHeight;
                if (s < xLabels.length)
                    packet.prtf("::    : :", colors[s], ":: : :").consume("", bar, xLabels[s]);
            }

            packet.raw("\n");
        }

        for (int i = 0; i < longestYLabel + 2; i++) // applying space in order to match up with the ylabels width and padding
            packet.raw(" ");
        packet.raw("\u2517");  // applying a bottom left corner
        for (int i = 0; i < values.length * bar.length() + 2; i++) // applying x axis
            packet.raw("\u2501");

        // centering the title about x axis and putting it.
        if (title != null)
            packet.prtf("::\n:b%*" + (values.length * bar.length() + (longestYLabel + 4) * 2) + "s:").consume(title);

//        mainLog.raw(builder.toString());
        packet.consume();
    }

    private static String[] defaultXLables(int[] values) {
        return Iterator.toArray(0, values.length, 1, i -> values[i]);
    }

    private static String[] defaultYLables(int highestBar, double scale) {
        return Iterator.toArray(0, (int) (highestBar * scale), 1, i -> String.format("%.3f", i / scale));
    }
}
