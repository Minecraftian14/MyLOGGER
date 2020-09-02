package main.utlities;

import java.awt.*;

public class RandomColour {

    int r, g, b;

    public RandomColour() {
        r = (int) (255 * Math.random());
        g = (int) (255 * Math.random());
        b = (int) (255 * Math.random());
    }

    public RandomColour(int _r, int _g, int _b) {
        r = _r;
        g = _g;
        b = _b;
    }

    public RandomColour getDark() {
        return new RandomColour(interpolate(r, 0), interpolate(g, 0), interpolate(b, 0));
    }

    public RandomColour getBright() {
        return new RandomColour(interpolate(r, 255), interpolate(g, 255), interpolate(b, 255));
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
