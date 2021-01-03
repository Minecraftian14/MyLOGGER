package main.bankedLogger;

import main.utlities.ConsoleColors;

public class Neo_Test {

    private void funky_method_name() {
        LogBank.LOG().println("__1________fmn________1__");
    }

    private void another_method() {
        LogBank.LOG().println("__2_________am________2__");
    }

    private void yet_another_method() {
        LogBank.LOG().println("__3_________yam________3__");
    }

    public Neo_Test() {
        LogBank.addNewControl("funky_method_name", new Log_Control(ConsoleColors.PURPLE_BOLD_BRIGHT));
        LogBank.addNewControl("another_method", new Log_Control(ConsoleColors.RED_UNDERLINED));
        LogBank.addNewControl("yet_another_method", new Log_Control(ConsoleColors.YELLOW_BACKGROUND));

        funky_method_name();
        another_method();
        yet_another_method();
    }

    public static void main(String[] args) {
        new Neo_Test();
    }

}
