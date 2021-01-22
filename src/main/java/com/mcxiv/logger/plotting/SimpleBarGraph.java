package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.tools.C;
import com.mcxiv.logger.tools.RandomColor;
import com.mcxiv.logger.util.Iterator;

class SimpleBarGraph implements Plot.BarGraph {

    String title = null;
    String[] xLabels = null;
    String[] yLabels = null;
    int[] values = null;

    double scale = 0.1;

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
        xLabels = (String[]) Iterator.toArray(a, b, 1, its);
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
    public Plot.BarGraph bar(int... values) {
        this.values = values;
        return this;
    }

    @Override
    public Plot.BarGraph W(int w) {
        return this;
    }

    @Override
    public Plot.BarGraph H(int h) {
        return this;
    }

    @Override
    public String create() {

        int highestBar = Integer.MIN_VALUE;
        for (int i : values) if (highestBar < i) highestBar = i;

        int longestName = Integer.MIN_VALUE;
        if (yLabels == null) yLabels = defaultYLables(highestBar);
        for (String s : yLabels) if (longestName < s.length()) longestName = s.length();


        StringBuilder builder = new StringBuilder("\n");


        String form = " %" + longestName + "s \u2528 ";

        for (int dh = 0, lim = (int) (highestBar * scale); dh < lim; dh++) {

            if (dh >= lim - yLabels.length)
                builder.append(String.format(form, yLabels[lim - dh - 1]));
            else builder.append(String.format(form, ""));

            for (int dw = 0; dw < values.length; dw++) { // +3 of pads and border

                if (values[dw] * scale >= lim - dh)
                    builder.append(RandomColor.getRandomAt(dw)).append(Box.B_F);
                else builder.append(" ");

            }

            if (xLabels != null && dh < xLabels.length)
                builder.append("    ").append(RandomColor.getRandomAt(dh)).append(Box.B_F).append(C.RS).append(" ").append(xLabels[dh]);

            builder.append("\n").append(C.RS);
        }

        for (int i = 0; i < longestName + 2; i++)
            builder.append(" ");
        builder.append("\u2517");
        for (int i = 0; i < values.length + 2; i++)
            builder.append("\u2501");

        return builder.toString();
    }

    private static String[] defaultYLables(int highestBar) {
        String[] sts = new String[highestBar];
        for (int i = 0; i < highestBar; i++)
            sts[i] = "" + i;
        return sts;
    }
}
