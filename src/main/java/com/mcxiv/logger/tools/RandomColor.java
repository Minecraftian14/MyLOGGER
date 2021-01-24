package com.mcxiv.logger.tools;

import com.mcxiv.logger.decorations.ConsoleDecoration;

import java.awt.*;

public class RandomColor {

    int r, g, b;


    /**
     * A collection of random colors between 0-215 inclusive
     */
    static String[] colors = new String[216];

    static {
        for (int i = 0; i < 216; i++)
            colors[i] = C.getFontColor(16 + i);
        for (int i = 0; i < colors.length; i++) {
            int index = (int) (Math.random() * colors.length);
            String temp = colors[i];
            colors[i] = colors[index];
            colors[index] = temp;
        }
    }

    public static String getRandom() {
        return ConsoleDecoration.hexToFont(((int) (256 * Math.random()) >> 16) & 0xFF, ((int) (256 * Math.random()) >> 8) & 0xFF, (int) (256 * Math.random()) & 0xFF);
    }

    public static String getRandomAt(int i) {
        i = 0xff000000 | i;
        return ConsoleDecoration.hexToFont((i >> 16) & 0xFF, (i >> 8) & 0xFF, i & 0xFF);
    }

    public RandomColor() {
        r = (int) (255 * Math.random());
        g = (int) (255 * Math.random());
        b = (int) (255 * Math.random());
    }

    public RandomColor(int _r, int _g, int _b) {
        r = _r;
        g = _g;
        b = _b;
    }

    public RandomColor getDark() {
        return new RandomColor(interpolate(r, 0), interpolate(g, 0), interpolate(b, 0));
    }

    public RandomColor getBright() {
        return new RandomColor(interpolate(r, 255), interpolate(g, 255), interpolate(b, 255));
    }

    public Color yieldColor() {
        return new Color(r, g, b);
    }

    public String yieldHex() {
        return Integer.toHexString(r).toUpperCase() + Integer.toHexString(g).toUpperCase() + Integer.toHexString(b).toUpperCase();
    }

    private static int interpolate(int f, int t) {
        return (f + t) / 2;
    }

}
