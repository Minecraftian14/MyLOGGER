package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.decorations.ConsoleDecoration;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.Iterator;

public class Plot {

    public static interface BarGraph {

        static BarGraph simple() {
            return new SimpleBarGraph();
        }

        static BarGraph box() {
            return new BoxBarGraph();
        }

        BarGraph title(String title);

        BarGraph XLabel(String... names);

        BarGraph XLabel(int a, int b, Iterator its);

        BarGraph YLabel(String... names);

        BarGraph YLabel(int a, int b, Iterator its);

        BarGraph values(int... values);

        BarGraph values(int a, int b, Iterator its);

        BarGraph charHeight(int h);

        BarGraph scale(double h);

        BarGraph setBarType(String bar);

        void create(FLog log);

    }


    public static String image(int w, int h, IntFunction r, IntFunction g, IntFunction b) {

        StringBuilder builder = new StringBuilder();

        for (int j = 0; j < h; j += 2) {
            for (int i = 0; i < w; i++) {
                builder.append(ConsoleDecoration.hexToFont(r.get(i, j), g.get(i, j), b.get(i, j)));
                builder.append(Box.B_F);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String image(int w, int h, int new_w, int new_h, IntFunction r, IntFunction g, IntFunction b) {
        double scalex = w / (double) new_w;
        double scaley = h / (double) new_h;
        return image(new_w, new_h, (x, y) -> r.get((int) (x * scalex), (int) (y * scaley)), (x, y) -> g.get((int) (x * scalex), (int) (y * scaley)), (x, y) -> b.get((int) (x * scalex), (int) (y * scaley)));
    }

    public interface IntFunction {
        int get(int x, int y);
    }

}
