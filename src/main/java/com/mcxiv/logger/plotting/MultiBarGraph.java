package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.tools.RandomColor;
import com.mcxiv.logger.util.Iterator;

import java.util.ArrayList;

public class MultiBarGraph implements Plot.BarGraph {

    String title = null;

    ArrayList<String> yLabels = null;
    ArrayList<String> xLabels = null;

    ArrayList<ArrayList<Integer>> values = new ArrayList<>();

    double scale = 0;

    String bar = Box.B_F;

    // Compost

    int longestXLabel = 0;
    int longestYLabel = 0;

    int leastValue = Integer.MAX_VALUE;
    int highestValue = Integer.MIN_VALUE;

    @Override
    public Plot.BarGraph title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Plot.BarGraph XLabel(String... names) {
        if (xLabels == null) xLabels = new ArrayList<>();
        for (String xLabel : names) {
            xLabels.add(xLabel);
            longestXLabel = Math.max(longestXLabel, xLabel.length());
        }
        return this;
    }

    @Override
    public Plot.BarGraph XLabel(int a, int b, Iterator its) {
        if (xLabels == null) xLabels = new ArrayList<>();
        for (int i = a; i < b; i++) {
            String val = its.consume(i).toString();
            xLabels.add(val);
            longestXLabel = Math.max(longestXLabel, val.length());
        }
        return this;
    }

    @Override
    public Plot.BarGraph YLabel(String... names) {
        if (yLabels == null) yLabels = new ArrayList<>();
        for (String yLabel : yLabels) {
            yLabels.add(yLabel);
            longestYLabel = Math.max(longestYLabel, yLabel.length());
        }
        return this;
    }

    @Override
    public Plot.BarGraph YLabel(int a, int b, Iterator its) {
        if (yLabels == null) yLabels = new ArrayList<>();
        for (int i = a; i < b; i++) {
            String val = its.consume(i).toString();
            yLabels.add(val);
            longestYLabel = Math.max(longestYLabel, val.length());
        }
        return this;
    }

    @Override
    public Plot.BarGraph values(int... vals) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int val : vals) {
            list.add(val);
            if (highestValue < val) highestValue = val;
            if (leastValue > val) leastValue = val;
        }
        values.add(list);
        return this;
    }

    @Override
    public Plot.BarGraph values(int a, int b, Iterator its) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = a; i < b; i++) {
            int val = (int) its.consume(i);
            list.add(val);
            if (highestValue < val) highestValue = val;
            if (leastValue > val) leastValue = val;
        }
        values.add(list);
        return this;
    }

    @Override
    public Plot.BarGraph charHeight(int h) {
        if (h % 2 == 0) h--;
        scale = (h + (title == null ? 1. : 2.)) / highestValue;
        return this;
    }

    @Override
    public Plot.BarGraph scale(double h) {
        scale = h;
        return this;
    }

    @Override
    public Plot.BarGraph setBarType(String bar) {
        this.bar = bar;
        return this;
    }

    @Override
    public void create(FLog log) {
        Packet packet = log.newPacket();

        if (scale == 0) scale = 11.0 / highestValue;
        int charHeight = (int) (highestValue * scale);

        if (xLabels == null) createDefaultXLabels();
        if (yLabels == null) createDefaultYLabels();

        if (yLabels.size() < charHeight) while (charHeight > yLabels.size()) yLabels.add("");

        if (xLabels.size() < values.size()) while (xLabels.size() < values.size()) xLabels.add("");


        String[] colors = new String[values.size()];
        for (int i = 0; i < values.size(); i++)
            colors[i] = ":#" + new RandomColor().yieldHex() + ":";


        for (int dh = 0; dh < charHeight; dh++) {

            packet.prtf(":%" + longestYLabel + "s: ::").consume(yLabels.get(yLabels.size() - dh - 1));

            if (dh == charHeight / 2) {
                packet.raw("\u254A");
                for (int i = 0; i < bar.length(); i++)
                    packet.raw("\u2501");
                for (int group_i = 0; group_i < values.get(0).size(); group_i++) {
                    for (int bar_i = 0; bar_i < values.size(); bar_i++) {
                        if (values.get(bar_i).get(group_i) >= 0)
                            for (int i = 0; i < bar.length(); i++)
                                packet.raw("\u2537");
                        else
                            for (int i = 0; i < bar.length(); i++)
                                packet.raw("\u252F");
                    }
                    for (int i = 0; i < bar.length(); i++)
                        packet.raw("\u2501");
                }
//                for (int i = 0; i < values.get(0).size() * (values.size() + 1)+1; i++) packet.raw("\u253F");

            } else {

                packet.prtf("", ":#e%" + bar.length() + "s:").consume("\u2528", "\u253C");

                for (int group_i = 0; group_i < values.get(0).size(); group_i++) {
                    for (int bar_i = 0; bar_i < values.size(); bar_i++) {

                        int value = values.get(bar_i).get(group_i) / 2;

                        if ((dh < charHeight / 2.) && (charHeight / 2. - dh < (value * scale)))
                            packet.prtf(colors[bar_i]).consume(bar);

                        else if ((dh > charHeight / 2) && (charHeight / 2. - dh > (value * scale)))
                            packet.prtf(colors[bar_i]).consume(bar);

                        else packet.prtf(":#e%" + bar.length() + "s:").consume("\u253C");

                    }
                    packet.prtf(":#e%" + bar.length() + "s:").consume("\u253C");
                }
            }


            if (dh < colors.length)
                packet.prtf("::    " + colors[dh] + " ::", ":%-" + longestXLabel + "s:").consume(bar, xLabels.get(dh));
            else packet.prtf(":%" + 4 + bar.length() + 1 + longestXLabel + "s:").consume("");

            packet.raw("\n");
        }


        packet.consume();
    }

    private void createDefaultXLabels() {
        xLabels = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            String nm = "Label " + (i + 1);
            xLabels.add(nm);
            longestXLabel = Math.max(longestXLabel, nm.length());
        }
    }

    private void createDefaultYLabels() {
        yLabels = new ArrayList<>();
        for (int i = (int) (-highestValue * scale / 2); i <= highestValue * scale / 2; i++) {
            String nm = "" + i;
            yLabels.add(nm);
            longestYLabel = Math.max(longestYLabel, nm.length());
        }
    }
}
