package com.mcxiv.logger.plotting;

import com.mcxiv.logger.util.Iterator;

public class Plot {
    public static interface BarGraph {
        static BarGraph simple() {
            return new SimpleBarGraph();
        }

        BarGraph title(String title);

        BarGraph XLabel(String... names);

        BarGraph XLabel(int a, int b, Iterator its);

        BarGraph YLabel(String... names);

        BarGraph YLabel(int a, int b, Iterator its);

        BarGraph bar(int... values);

        BarGraph W(int w);

        BarGraph H(int h);

        String create();
    }
}
