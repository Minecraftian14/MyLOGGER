package com.mcxiv.logger.tools;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C {

    // Reset
    public static final String RESET = "\u001B[0m", RS = RESET;

    // 8 basic color set
    public static final String BLACK = "\u001B[30m", BK = BLACK;
    public static final String WHITE = "\u001B[37m", W = WHITE;
    public static final String RED = "\u001B[31m", R = RED;
    public static final String GREEN = "\u001B[32m", G = GREEN;
    public static final String BLUE = "\u001B[34m", B = BLUE;
    public static final String YELLOW = "\u001B[33m", Y = YELLOW;
    public static final String MAGENTA = "\u001B[35m", M = MAGENTA;
    public static final String CYAN = "\u001B[36m", C = CYAN;

    // 8 bright color set
    public static final String BRIGHT_BLACK = "\u001b[30;1m", BBK = BRIGHT_BLACK;
    public static final String BRIGHT_RED = "\u001b[31;1m", BR = BRIGHT_RED;
    public static final String BRIGHT_GREEN = "\u001b[32;1m", BG = BRIGHT_GREEN;
    public static final String BRIGHT_YELLOW = "\u001b[33;1m", BY = BRIGHT_YELLOW;
    public static final String BRIGHT_BLUE = "\u001b[34;1m", BB = BRIGHT_BLUE;
    public static final String BRIGHT_MAGENTA = "\u001b[35;1m", BM = BRIGHT_MAGENTA;
    public static final String BRIGHT_CYAN = "\u001b[36;1m", BC = BRIGHT_CYAN;
    public static final String BRIGHT_WHITE = "\u001b[37;1m", BW = BRIGHT_WHITE;

    // 8 simple background set
    public static final String BLACK_BACKGROUND = "\u001B[40m", BKBG = BLACK_BACKGROUND;
    public static final String RED_BACKGROUND = "\u001B[41m", RBG = RED_BACKGROUND;
    public static final String GREEN_BACKGROUND = "\u001B[42m", GBG = GREEN_BACKGROUND;
    public static final String YELLOW_BACKGROUND = "\u001B[43m", YBG = YELLOW_BACKGROUND;
    public static final String BLUE_BACKGROUND = "\u001B[44m", BBG = BLUE_BACKGROUND;
    public static final String MAGENTA_BACKGROUND = "\u001B[45m", MBG = MAGENTA_BACKGROUND;
    public static final String CYAN_BACKGROUND = "\u001B[46m", CBG = CYAN_BACKGROUND;
    public static final String WHITE_BACKGROUND = "\u001B[47m", WBG = WHITE_BACKGROUND;

    // 8 bright background set
    public static final String BLACK_BRIGHT_BACKGROUND = "\u001B[40;1m", BKBBG = BLACK_BRIGHT_BACKGROUND;
    public static final String RED_BRIGHT_BACKGROUND = "\u001B[41;1m", RBBG = RED_BRIGHT_BACKGROUND;
    public static final String GREEN_BRIGHT_BACKGROUND = "\u001B[42;1m", GBBG = GREEN_BRIGHT_BACKGROUND;
    public static final String YELLOW_BRIGHT_BACKGROUND = "\u001B[43;1m", YBBG = YELLOW_BRIGHT_BACKGROUND;
    public static final String BLUE_BRIGHT_BACKGROUND = "\u001B[44;1m", BBBG = BLUE_BRIGHT_BACKGROUND;
    public static final String MAGENTA_BRIGHT_BACKGROUND = "\u001B[45;1m", MBBG = MAGENTA_BRIGHT_BACKGROUND;
    public static final String CYAN_BRIGHT_BACKGROUND = "\u001B[46;1m", CBBG = CYAN_BRIGHT_BACKGROUND;
    public static final String WHITE_BRIGHT_BACKGROUND = "\u001B[47;1m", WBBG = WHITE_BRIGHT_BACKGROUND;

    public static final String BOLD = "\u001b[1m", FB = BOLD; // Idea: Yes
    public static final String FAINT = "\u001b[2m", FFa = FAINT;
    public static final String ITALICS = "\u001b[3m", FI = ITALICS;
    public static final String UNDERLINED = "\u001b[4m", FU = UNDERLINED; // Idea: Yes
    public static final String BLINK_SLOW = "\u001b[5m", BlS = BLINK_SLOW;
    public static final String BLINK_FAST = "\u001b[6m", BlF = BLINK_FAST;
    public static final String REVERSED = "\u001b[7m", FR = REVERSED; // Idea: Yes
    public static final String HIDE_START = "\u001b[8m", HdS = HIDE_START;
    public static final String STRIKE = "\u001b[9m", FS = STRIKE; // Idea: Yes

    public static final String FRAKTUR = "\u001b[20m", FFk = FRAKTUR;
    public static final String UNDERLINED_THICK = "\u001b[21m", FUt = UNDERLINED_THICK; // Idea: Yes

    public static final String BLINK_OFF = "\u001b[25m", BlO = BLINK_OFF;

    public static final String HIDE_OFF = "\u001b[28m", HdO = HIDE_OFF;

    public static final String FRAMED = "\u001b[51m", FFr = FRAMED; // Idea: Yes
    public static final String CIRCLED = "\u001b[52m", FCr = CIRCLED; // Idea: Yes
    public static final String OVERLINED = "\u001b[53m", FOv = OVERLINED;

    public static final String SUPER_SCRIPT = "\u001b[73m", FSS = SUPER_SCRIPT;
    public static final String SUB_SCRIPT = "\u001b[74m", FSs = SUB_SCRIPT;

    public static final String CLEAR_SCREEN_FROM_CURSOR_TILL_END = "\u001b[0J";
    public static final String CLEAR_SCREEN_FROM_CURSOR_TO_START = "\u001b[1J";
    public static final String CLEAR_SCREEN_ENTIRE = "\u001b[2J";
    public static final String CLEAR_LINE_FROM_CURSOR_TILL_END = "\u001b[0K";
    public static final String CLEAR_LINE_FROM_CURSOR_TO_START = "\u001b[1K";
    public static final String CLEAR_LINE_ENTIRE = "\u001b[2K";

    public static final String SAVE_CURSOR_POSITION = "\u001b[{s}";
    public static final String LOAD_CURSOR_POSITION = "\u001b[{u}";

    public static String CURSOR_UP(int num) {
        return "\u001b[" + num + "A";
    }

    public static String CURSOR_DOWN(int num) {
        return "\u001b[" + num + "B";
    }

    public static String CURSOR_RIGHT(int num) {
        return "\u001b[" + num + "C";
    }

    public static String CURSOR_LEFT(int num) {
        return "\u001b[" + num + "D";
    }

    public static String NEXT_LINE(int num) {
        return "\u001b[" + num + "E";
    }

    public static String PREV_LINE(int num) {
        return "\u001b[" + num + "F";
    }

    public static String SET_COLUMN(int num) {
        return "\u001b[" + num + "G";
    }

    public static String SET_POSITION(int row, int col) {
        return "\u001b[" + row + ";" + col + "H";
    }


    public static class hex {

        public static class font {
            public static String to3Bit(int r, int g, int b) {
                return String.format("\u001B[%dm", 30 + (b / 128) * 4 + (g / 128) * 2 + r / 128);
            }

            public static String to4Bit(int r, int g, int b) {
                return String.format("\u001B[%d%sm", 30 + (b / 128) * 4 + (g / 128) * 2 + r / 128, r + g + b > 384 ? ";1" : "");
            }

            public static String to8Bit(int r, int g, int b) {
                return String.format("\u001b[38;5;%dm", 16 + (r / 51) * 36 + (g / 51) * 6 + b / 51);
            }

            public static String to24Bit(int r, int g, int b) {
                return String.format("\u001b[38;2;%d;%d;%dm", r, g, b);
            }
        }

        public static class back {
            public static String to3Bit(int r, int g, int b) {
                return String.format("\u001B[%dm", 40 + (b / 128) * 4 + (g / 128) * 2 + r / 128);
            }

            public static String to4Bit(int r, int g, int b) {
                return String.format("\u001B[%d%sm", 40 + (b / 128) * 4 + (g / 128) * 2 + r / 128, r + g + b > 384 ? ";1" : "");
            }

            public static String to8Bit(int r, int g, int b) {
                return String.format("\u001b[48;5;%dm", 16 + (r / 51) * 36 + (g / 51) * 6 + b / 51);
            }

            public static String to24Bit(int r, int g, int b) {
                return String.format("\u001b[48;2;%d;%d;%dm", r, g, b);
            }
        }

        static Pattern color6d = Pattern.compile("[#@]?([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})[a-fA-F0-9]{0,2}");

        public static String split6d(String rgb, TriIntInputAdaptor method) {
            Matcher m = color6d.matcher(rgb);
            if (m.find() && m.groupCount() >= 3)
                return method.act(Integer.parseInt(m.group(1), 16), Integer.parseInt(m.group(2), 16), Integer.parseInt(m.group(3), 16));
            throw new IllegalArgumentException(method + " doesn't seem to be a color code...");
        }

        static Pattern color3d = Pattern.compile("[#@]?([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])[a-fA-F0-9]?");

        public static String split3d(String rgb, TriIntInputAdaptor method) {
            Matcher m = color3d.matcher(rgb);
            if (m.find() && m.groupCount() >= 3)
                return method.act(Integer.parseInt(m.group(1) + m.group(1), 16), Integer.parseInt(m.group(2) + m.group(2), 16), Integer.parseInt(m.group(3) + m.group(3), 16));
            throw new IllegalArgumentException(method + " doesn't seem to be a color code...");
        }

        public static String split1d(String gr, TriIntInputAdaptor method) {
            gr += gr;
            return method.act(Integer.parseInt(gr, 16), Integer.parseInt(gr, 16), Integer.parseInt(gr, 16));
        }

        public interface TriIntInputAdaptor {
            String act(int a, int b, int c);
        }

    }


    /////// WILL BE DEPRECATED - TO BE REPLACED BY BETTER COMMANDS
    public static String getFontColor(int i) {
        return "\u001b[38;5;" + i + "m";
    }

    public static int hexTo216(int r, int g, int b) {
        r /= 42.6; // [0-256) -> [0-6)
        g /= 42.6; // [0-256) -> [0-6)
        b /= 42.6; // [0-256) -> [0-6)
        return 16 + b + 6 * g + 36 * r;
    }

    public static String hexTo24bitFont(int r, int g, int b) {
        return "\u001b[38;2;" + r + ";" + g + ";" + b + "m";
    }

    public static String hexTo24bitBack(int r, int g, int b) {
        return "\u001b[48;2;" + r + ";" + g + ";" + b + "m";
    }

    public static int hexToGray(int g) {
        return 232 + (int) (g / 10.7);
    }

    public static String getBackColor(int i) {
        return "\u001b[48;5;" + i + "m";
    }

    public static int hex3ToColor(String color) {
        if (color.length() != 3) throw new NumberFormatException(color + " should be 3 char long!");
        return hexTo216(Integer.parseInt(color.charAt(0) + "0", 16), Integer.parseInt(color.charAt(1) + "0", 16), Integer.parseInt(color.charAt(2) + "0", 16));
    }

    public static int hex6ToColor(String color) {
        if (color.length() != 6) throw new NumberFormatException(color + " should be 6 char long!");
        return hexTo216(Integer.parseInt(color.substring(0, 2), 16), Integer.parseInt(color.substring(2, 4), 16), Integer.parseInt(color.substring(4, 6), 16));
    }
    //////////////////////////////////////////////////////////////

    // public static List<String> list = List.of(BLACK, WHITE, RED, GREEN, BLUE, YELLOW, MAJENTA, CYAN, BRIGHT_BLACK, BRIGHT_WHITE, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_BLUE, BRIGHT_YELLOW, BRIGHT_MAJENTA, BRIGHT_CYAN, BLACK_BACKGROUND, WHITE_BACKGROUND, RED_BACKGROUND, GREEN_BACKGROUND, BLUE_BACKGROUND, YELLOW_BACKGROUND, MAJENTA_BACKGROUND, CYAN_BACKGROUND, BLACK_BRIGHT_BACKGROUND, WHITE_BRIGHT_BACKGROUND, RED_BRIGHT_BACKGROUND, GREEN_BRIGHT_BACKGROUND, BLUE_BRIGHT_BACKGROUND, YELLOW_BRIGHT_BACKGROUND, MAJENTA_BRIGHT_BACKGROUND, CYAN_BRIGHT_BACKGROUND, BOLD, UNDERLINED, REVERSED);

    public static final HashMap<String, String> map = new HashMap<>();

    static {
        map.put("BK", BLACK);
        map.put("W", WHITE);
        map.put("R", RED);
        map.put("G", GREEN);
        map.put("B", BLUE);
        map.put("Y", YELLOW);
        map.put("M", MAGENTA);
        map.put("C", CYAN);
        map.put("BBK", BRIGHT_BLACK);
        map.put("BW", BRIGHT_WHITE);
        map.put("BR", BRIGHT_RED);
        map.put("BG", BRIGHT_GREEN);
        map.put("BB", BRIGHT_BLUE);
        map.put("BY", BRIGHT_YELLOW);
        map.put("BM", BRIGHT_MAGENTA);
        map.put("BC", BRIGHT_CYAN);
        map.put("BKBG", BLACK_BACKGROUND);
        map.put("WBG", WHITE_BACKGROUND);
        map.put("RBG", RED_BACKGROUND);
        map.put("GBG", GREEN_BACKGROUND);
        map.put("BBG", BLUE_BACKGROUND);
        map.put("YBG", YELLOW_BACKGROUND);
        map.put("MBG", MAGENTA_BACKGROUND);
        map.put("CBG", CYAN_BACKGROUND);
        map.put("BKBBG", BLACK_BRIGHT_BACKGROUND);
        map.put("WBBG", WHITE_BRIGHT_BACKGROUND);
        map.put("RBBG", RED_BRIGHT_BACKGROUND);
        map.put("GBBG", GREEN_BRIGHT_BACKGROUND);
        map.put("BBBG", BLUE_BRIGHT_BACKGROUND);
        map.put("YBBG", YELLOW_BRIGHT_BACKGROUND);
        map.put("MBBG", MAGENTA_BRIGHT_BACKGROUND);
        map.put("CBBG", CYAN_BRIGHT_BACKGROUND);
        map.put("FB", BOLD);
        map.put("FU", UNDERLINED);
        map.put("FR", REVERSED);
        map.put("RS", RESET);

//        System.out.println("[$]((?:" + map.keySet().stream().reduce("", (a, b) -> a.equals("") ? b : a + ")|(?:" + b) + "))");
    }

    public static int length(String s) {
        int ls = 0;

        main:
        for (int i = 0; i < s.length(); i++) {
            for (String col : map.values()) {
                if (s.substring(i).startsWith(col)) {
                    i += col.length();
                    continue main;
                }
            }
            ls++;
        }

        return ls;
    }
}
