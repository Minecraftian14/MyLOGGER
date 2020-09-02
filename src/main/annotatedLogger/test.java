package main.annotatedLogger;

import static main.utlities.ConsoleColors.ANSI.*;

public class test {

    public static void main(String[] args) {

        that();
        and();
        That();

    }

    // No format give a random colour
    private static void that() {
        LOG.prtl("A", "B", "C", "D");
    }

    @Format({BLUE + "b: ", "_::", YELLOW})
    private static void and() {
        LOG.prtl("AND:", "Round Number", "9");
    }

    @Format({GREEN_BACKGROUND + "b:789 ", "i"})
    private static void That() {
        LOG.prtl("USER", "Hello");
    }

}
