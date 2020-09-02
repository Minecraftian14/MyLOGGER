package main.bankedLogger;

import main.utlities.ConsoleColors;

public class Log_Control {

    String start_color;

    public Log_Control(String Color) {
        start_color = Color;
    }

    public Log_Control(String text, String background) {
        start_color = text + background;
    }

    public void print(Object... msgs) {
        System.out.print(start_color);
        for (Object msg : msgs) System.out.print(msg.toString());
        System.out.println(ConsoleColors.RESET);
    }

    public void println(Object... msgs) {
        print(msgs);
        System.out.print("\n");
    }

    public void printsep(String sep, Object... msgs) {
        if (msgs.length <= 1) {
            print(msgs);
            return;
        }
        for (int i = 0; i < msgs.length - 1; i++)
            msgs[i] += sep;
        print(msgs);
    }

    public void printsp(Object... msgs) {
        printsep(" ", msgs);
    }

}
