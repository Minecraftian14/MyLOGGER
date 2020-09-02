package main.utlities;

import java.util.HashMap;

public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String MAJENTA_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE

    public static class ANSI {

        // Reset
        public static final String RESET = "\u001B[0m";

        // 8 color
        public static final String BLACK = "\u001B[30m";
        public static final String WHITE = "\u001B[37m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String BLUE = "\u001B[34m";
        public static final String YELLOW = "\u001B[33m";
        public static final String MAJENTA = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";

        // 16 color (added bright)
        public static final String BRIGHT_BLACK = "\u001b[30;1m";
        public static final String BRIGHT_RED = "\u001b[31;1m";
        public static final String BRIGHT_GREEN = "\u001b[32;1m";
        public static final String BRIGHT_YELLOW = "\u001b[33;1m";
        public static final String BRIGHT_BLUE = "\u001b[34;1m";
        public static final String BRIGHT_MAJENTA = "\u001b[35;1m";
        public static final String BRIGHT_CYAN = "\u001b[36;1m";
        public static final String BRIGHT_WHITE = "\u001b[37;1m";

        public static final String BLACK_BACKGROUND = "\u001B[40m";
        public static final String RED_BACKGROUND = "\u001B[41m";
        public static final String GREEN_BACKGROUND = "\u001B[42m";
        public static final String YELLOW_BACKGROUND = "\u001B[43m";
        public static final String BLUE_BACKGROUND = "\u001B[44m";
        public static final String MAJENTA_BACKGROUND = "\u001B[45m";
        public static final String CYAN_BACKGROUND = "\u001B[46m";
        public static final String WHITE_BACKGROUND = "\u001B[47m";

        public static final String BLACK_BRIGHT_BACKGROUND = "\u001B[40;1m";
        public static final String RED_BRIGHT_BACKGROUND = "\u001B[41;1m";
        public static final String GREEN_BRIGHT_BACKGROUND = "\u001B[42;1m";
        public static final String YELLOW_BRIGHT_BACKGROUND = "\u001B[43;1m";
        public static final String BLUE_BRIGHT_BACKGROUND = "\u001B[44;1m";
        public static final String MAJENTA_BRIGHT_BACKGROUND = "\u001B[45;1m";
        public static final String CYAN_BRIGHT_BACKGROUND = "\u001B[46;1m";
        public static final String WHITE_BRIGHT_BACKGROUND = "\u001B[47;1m";

        public static final String BOLD = "\u001b[1m";
        public static final String UNDERLINED = "\u001b[4m";
        public static final String REVERSED = "\u001b[7m";  // Swap background and font color.

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

        public static String getFontColor(int i) {
            return "\u001b[38;5;" + i + "m";
        }

        public static String getBackColor(int i) {
            return "\u001b[48;5;" + i + "m";
        }

        // public static List<String> list = List.of(BLACK, WHITE, RED, GREEN, BLUE, YELLOW, MAJENTA, CYAN, BRIGHT_BLACK, BRIGHT_WHITE, BRIGHT_RED, BRIGHT_GREEN, BRIGHT_BLUE, BRIGHT_YELLOW, BRIGHT_MAJENTA, BRIGHT_CYAN, BLACK_BACKGROUND, WHITE_BACKGROUND, RED_BACKGROUND, GREEN_BACKGROUND, BLUE_BACKGROUND, YELLOW_BACKGROUND, MAJENTA_BACKGROUND, CYAN_BACKGROUND, BLACK_BRIGHT_BACKGROUND, WHITE_BRIGHT_BACKGROUND, RED_BRIGHT_BACKGROUND, GREEN_BRIGHT_BACKGROUND, BLUE_BRIGHT_BACKGROUND, YELLOW_BRIGHT_BACKGROUND, MAJENTA_BRIGHT_BACKGROUND, CYAN_BRIGHT_BACKGROUND, BOLD, UNDERLINED, REVERSED);

        public static HashMap<String, String> map = createKeyToColourMap();

        private static HashMap<String, String> createKeyToColourMap() {
            HashMap<String, String> out = new HashMap<>();
            out.put("0", BLACK);
            out.put("W", WHITE);
            out.put("R", RED);
            out.put("G", GREEN);
            out.put("B", BLUE);
            out.put("Y", YELLOW);
            out.put("M", MAJENTA);
            out.put("C", CYAN);
            out.put("0B", BRIGHT_BLACK);
            out.put("WB", BRIGHT_WHITE);
            out.put("RB", BRIGHT_RED);
            out.put("GB", BRIGHT_GREEN);
            out.put("BB", BRIGHT_BLUE);
            out.put("YB", BRIGHT_YELLOW);
            out.put("MB", BRIGHT_MAJENTA);
            out.put("CB", BRIGHT_CYAN);
            out.put("0Bck", BLACK_BACKGROUND);
            out.put("WBck", WHITE_BACKGROUND);
            out.put("RBck", RED_BACKGROUND);
            out.put("GBck", GREEN_BACKGROUND);
            out.put("BBck", BLUE_BACKGROUND);
            out.put("YBck", YELLOW_BACKGROUND);
            out.put("MBck", MAJENTA_BACKGROUND);
            out.put("CBck", CYAN_BACKGROUND);
            out.put("0BBck", BLACK_BRIGHT_BACKGROUND);
            out.put("WBBck", WHITE_BRIGHT_BACKGROUND);
            out.put("RBBck", RED_BRIGHT_BACKGROUND);
            out.put("GBBck", GREEN_BRIGHT_BACKGROUND);
            out.put("BBBck", BLUE_BRIGHT_BACKGROUND);
            out.put("YBBck", YELLOW_BRIGHT_BACKGROUND);
            out.put("MBBck", MAJENTA_BRIGHT_BACKGROUND);
            out.put("CBBck", CYAN_BRIGHT_BACKGROUND);
            out.put("b", BOLD);
            out.put("u", UNDERLINED);
            out.put("r", REVERSED);
            out.put("x", RESET);
            return out;
        }

    }

    public static void main(String[] args) {
        printColors();
        printBackColors();
    }

    public static void printColors() {
        for (int i = 0; i < 16; i++)
            System.out.print(ANSI.getFontColor(i) + "▉" + ANSI.RESET);
        for (int i = 0; i < 16; i++)
            System.out.print(ANSI.getFontColor(i) + " " + i + " = ▉  " + ANSI.RESET);

        System.out.println();
        for (int i = 16; i < 256; i++) {
            System.out.print(ANSI.getFontColor(i) + "▉" + ANSI.RESET);
            if ((i + 3) % 6 == 0) {
                System.out.print("\t\t");
                for (int j = i - 5; j < i; j++)
                    System.out.print(ANSI.getFontColor(j) + " " + j + " = ▉  " + ANSI.RESET);
                System.out.println();
            }
        }
    }


    public static void printBackColors() {
        for (int i = 0; i < 16; i++)
            System.out.print(ANSI.getBackColor(i) + " " + ANSI.RESET);
        for (int i = 0; i < 16; i++)
            System.out.print(ANSI.getBackColor(i) + " " + i + " =    " + ANSI.RESET);

        System.out.println();
        for (int i = 16; i < 256; i++) {
            System.out.print(ANSI.getBackColor(i) + " " + ANSI.RESET);
            if ((i + 3) % 6 == 0) {
                System.out.print("\t\t");
                for (int j = i - 5; j < i; j++)
                    System.out.print(ANSI.getBackColor(j) + " " + j + " " + ANSI.RESET);
                System.out.println();

            }
        }
    }


}
