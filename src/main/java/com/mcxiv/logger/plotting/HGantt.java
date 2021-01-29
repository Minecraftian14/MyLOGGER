package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.tools.RandomColor;
import com.mcxiv.logger.util.Iterator;

public class HGantt implements Plot.Gantt {

    String title = null;

    String[] xLabels = null;
    String[] yLabels = null;

    int[] valuesTos = null;
    int[] valuesFroms = null;

    double scale = 0.1;

    // Compost

    int longestYLabel = 0;
    int longestXLabel = 0;
    int leastValue = Integer.MAX_VALUE;
    int greatestValue = 0;

    @Override
    public Plot.Gantt title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public Plot.Gantt XLabel(String... names) {
        xLabels = names;
        for (String xLabel : xLabels)
            longestXLabel = Math.max(longestXLabel, xLabel.length());
        return this;
    }

    @Override
    public Plot.Gantt XLabel(int a, int b, Iterator its) {
        xLabels = Iterator.toArray(a, b, 1, its);
        for (String xLabel : xLabels)
            longestXLabel = Math.max(longestXLabel, xLabel.length());
        return this;
    }

    @Override
    public Plot.Gantt YLabel(String... names) {
        yLabels = names;
        for (String yLabel : yLabels)
            longestYLabel = Math.max(longestYLabel, yLabel.length());
        return this;
    }

    @Override
    public Plot.Gantt YLabel(int a, int b, Iterator its) {
        yLabels = Iterator.toArray(a, b, 1, its);
        for (String yLabel : yLabels)
            longestYLabel = Math.max(longestYLabel, yLabel.length());
        return this;
    }

    @Override
    public Plot.Gantt values(int... values) {
        valuesFroms = new int[values.length / 2];
        valuesTos = new int[values.length / 2];
        for (int i = 0, j = 0; i < valuesTos.length * 2; i += 2, j++) {
            valuesFroms[j] = values[i];
            valuesTos[j] = values[i + 1];
            leastValue = Math.min(leastValue, Math.min(values[i], values[i + 1]));
            greatestValue = Math.max(greatestValue, Math.max(values[i], values[i + 1]));
        }
        return this;
    }

    @Override
    public Plot.Gantt values(int a, int b, Iterator its) {
        valuesFroms = new int[(b - a) / 2];
        valuesTos = new int[(b - a) / 2];
        for (int i = a, j = 0; i < b; i += 2, j++) {
            valuesFroms[j] = (int) its.consume(i);
            valuesTos[j] = (int) its.consume(i + 1);
            leastValue = Math.min(leastValue, Math.min(valuesFroms[j], valuesTos[j]));
            greatestValue = Math.max(greatestValue, Math.max(valuesFroms[i], valuesTos[j]));
        }
        return this;
    }

    @Override
    public Plot.Gantt valuesFroms(int... values) {
        valuesFroms = values;
        for (int valuesFrom : valuesFroms) {
            leastValue = Math.min(leastValue, valuesFrom);
            greatestValue = Math.max(greatestValue, valuesFrom);
        }
        return this;
    }

    @Override
    public Plot.Gantt valuesFroms(int a, int b, Iterator its) {
        valuesFroms = Iterator.toIntArray(a, b, 1, its);
        for (int valuesFrom : valuesFroms) {
            leastValue = Math.min(leastValue, valuesFrom);
            greatestValue = Math.max(greatestValue, valuesFrom);
        }
        return this;
    }

    @Override
    public Plot.Gantt valuesTos(int... values) {
        valuesTos = values;
        for (int valuesTo : valuesTos) {
            leastValue = Math.min(leastValue, valuesTo);
            greatestValue = Math.max(greatestValue, valuesTo);
        }
        return this;
    }

    @Override
    public Plot.Gantt valuesTos(int a, int b, Iterator its) {
        valuesTos = Iterator.toIntArray(a, b, 1, its);
        for (int valuesTo : valuesTos) {
            leastValue = Math.min(leastValue, valuesTo);
            greatestValue = Math.max(greatestValue, valuesTo);
        }
        return this;
    }

    @Override
    public void create(FLog log) {
        Packet packet = log.newPacket();

        for (int dh = 0; dh < valuesTos.length; dh++) {
            packet.prtf(":%" + longestYLabel + "s:").consume(yLabels[dh]);

            packet.raw(" \u2528");

            RandomColor c = new RandomColor();

            for (int i = 0; i < valuesFroms[dh]; i++)
                packet.prtf(":#e:").consume("\u253C");
            for (int i = valuesFroms[dh]; i < valuesTos[dh]; i++)
                packet.prtf(":#" + c.yieldHex() + ":").consume(Box.B_F);
            for (int i = valuesTos[dh]; i < greatestValue; i++)
                packet.prtf(":#e:").consume("\u253C");

            packet.raw("\n");
        }

        packet.consume();
    }

}
