package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.tools.C;
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

        BarGraph bar(int a, int b, Iterator its);

        BarGraph charHeight(int h);

        BarGraph scale(double h);

        BarGraph setBarType(String bar);

        String create();

    }


    public static String image(int w, int h, ColorFunction color) {

        StringBuilder builder = new StringBuilder();

        for (int j = 0; j < h; j += 2) {
            for (int i = 0; i < w; i++) {
                builder.append(C.getFontColor(C.hexTo216(color.at(i, j).R(), color.at(i, j).G(), color.at(i, j).B())));
                builder.append(Box.B_F);
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public static String image(int w, int h, int new_w, int new_h, ColorFunction color) {
        double scalex = w / (double) new_w;
        double scaley = h / (double) new_h;
        return image(new_w, new_h, (x, y) -> color.at((int) (x * scalex), (int) (y * scaley)));
    }

    public interface ColorFunction {
        ColorBox at(int x, int y);
    }

    public interface ColorBox {
        int R();

        int G();

        int B();
    }

}
