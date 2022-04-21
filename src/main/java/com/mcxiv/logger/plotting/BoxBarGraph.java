package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.tools.RandomColor;
import com.mcxiv.logger.util.Iterator;

class BoxBarGraph implements Plot.BarGraph {

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
        scale = (h - (title == null ? 2 : 4)) / highestBar;
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
        int charHeight = Math.max(4, (int) (highestBar * scale));

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


        if (title != null) { // centering the title about x axis and putting it.
            packet.raw("\n");
            packet.raw(Box.TL_DC);
            for (int i = 0; i < longestYLabel + values.length * bar.length() + (longestXLabel + 6) * Math.ceil(xLabels.length / (float) charHeight) + 6; i++)  // Beam until top border's and x label's separator's meeting
                packet.raw(Box.DB);
            packet.raw(Box.TR_DC);
            packet.raw("\n");
            packet.raw(Box.DP);
            for (int i = 0; i < longestYLabel + 2; i++)
                packet.raw(" ");
            packet.prtf(":b%-" + (int) (values.length * bar.length() + (longestXLabel + 6) * Math.ceil(xLabels.length / (float) charHeight) + 4) + "s:").consume(title);
            packet.raw(Box.DP);
            packet.raw("\n");
        }

        packet.raw(title == null ? Box.TL_DC : Box.R_DC);                                  // Top Left Corner
        for (int i = 0; i < longestYLabel + 1; i++)                 // Beam until top border's and y axis's meeting
            packet.raw(Box.DB);
        packet.raw("\u2564");                                   // Double Beam to Single Pillar down connector
        for (int i = 0; i < values.length * bar.length() + 2; i++)  // Beam until top border's and x label's separator's meeting
            packet.raw(Box.DB);
        packet.raw(Box.B_DC);                                   // Double Beam to Double Pillar down connector
        for (int i = 0; i < (longestXLabel + 6) * Math.ceil(xLabels.length / (float) charHeight) + 1; i++)                     // Double Beam to end connector
            packet.raw(Box.DB);
        packet.raw(title == null ? Box.TR_DC : Box.L_DC);
        packet.raw("\n");                     // Top Right Corner


        form = Box.DP + "%" + longestYLabel + "s \u2524 ";

        // Note that these operations are evaluated row wise.
        for (int dh = 0; dh < charHeight; dh++) { // for each row

            // Adding in yLabels, or, a space if not enough provided.
            // Note that these names/space are formatted to have a padding and an axis fragment.
            if (dh >= charHeight - yLabels.length)
                packet.raw(String.format(form, yLabels[charHeight - dh - 1]));
            else packet.raw(String.format(form, ""));

            // Putting in a Bar if bar is to be printed else a space.
            for (int dw = 0; dw < values.length; dw++) { // for each bar space
                if (values[dw] * scale >= charHeight - dh)//{
                    packet.prtf(colors[dw]).consume(bar);
//                    packet.raw(RandomColor.getRandomAt(dw));packet.raw(bar);}
                else for (int i = 0; i < bar.length(); i++) packet.raw(" ");
            }
            packet.raw(" ");/*packet.raw(C.RS);*/
            packet.raw(Box.DP);

            // Putting in xLabels if they are present.
            // if (xLabels != null && dh < xLabels.length)
            // packet.raw(xLabels[dh]);
            // Putting in all xLabels such that if they exceed the charheight they are printed appearing to exist in separate column.
            for (int j = 0; j < Math.ceil(xLabels.length / (float) charHeight); j++) {
                int s = dh + j * charHeight;
                if (s < xLabels.length)
                    packet.prtf("::    : :", colors[s], ":: : :").consume("", bar, xLabels[s]);
            }

            if (dh >= xLabels.length) {
                packet.prtf("::      :%" + longestXLabel + "s:").consume("");
            }

            packet.raw(" ");
            packet.raw(Box.DP);
            packet.raw("\n");//packet.raw(C.RS);
        }


        packet.raw(Box.BL_DC);                                              // Bottom Left Corner
        for (int i = 0; i < longestYLabel + 1; i++)                             // filling up Double Beam to cover ylabels
            packet.raw(Box.DB);
        packet.raw("\u2567");                                               // putting a Double Beam to Single Pillar up connector
        for (int i = 0; i < values.length * bar.length() + 2; i++)              // applying x axis
            packet.raw(Box.DB);
        packet.raw(Box.T_DC);                                               // putting a Double Beam to Double Pillar up connector
        for (int i = 0; i < (longestXLabel + 6) * Math.ceil(xLabels.length / (float) charHeight) + 1; i++)                             // filling up Double Beam to cover xlabels
            packet.raw(Box.DB);
        packet.raw(Box.BR_DC);                                              // Bottom Right Corner


        packet.consume();
    }

    private static String[] defaultXLables(int[] values) {
        return Iterator.toArray(0, values.length, 1, i -> values[i]);
    }

    private static String[] defaultYLables(int highestBar, double scale) {
        return Iterator.toArray(0, (int) (highestBar * scale), 1, i -> String.format("%.3f", i / scale));
    }
}
