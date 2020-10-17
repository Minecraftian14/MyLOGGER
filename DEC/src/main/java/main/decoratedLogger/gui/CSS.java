package main.decoratedLogger.gui;

public class CSS {

    int r, g, b;

    public CSS() {
        r = getRandomNum();
        g = getRandomNum();
        b = getRandomNum();
    }

    public String getNormal() {
        return "-fx-background-color: linear-gradient(" + toHex(r, g, b) + ", " + toHex(r - 10, g - 10, b - 10) + ");";
    }

    public String getHover() {
        return "-fx-background-color: linear-gradient(from 0% 93% to 0% 100%, " + toHex(r, g, b) + " 0%, " + toHex(r - 20, g - 20, b - 20) + " 100%);\n";
    }

    private static String toHex(int r, int g, int b) {
        return "#" + (Integer.toString(r, 16) + Integer.toString(g, 16) + Integer.toString(b, 16)).toUpperCase();
    }

    public static int getRandomNum() {
        return 64 + (int) (127.5 * Math.random());
    }

}
