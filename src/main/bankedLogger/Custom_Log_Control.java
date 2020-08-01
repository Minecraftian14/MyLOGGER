package main.bankedLogger;

import main.utlities.ConsoleColors;

import java.io.OutputStream;
import java.io.PrintStream;

public class Custom_Log_Control extends Log_Control {

    PrintStream out;

    public Custom_Log_Control(String text, OutputStream stream) {
        super(text);
        out = new PrintStream(stream, true);
    }

    @Override
    public void print(Object... msgs) {
        out.print(start_color);
        for (Object msg : msgs) System.out.print(msg.toString());
        out.println(ConsoleColors.RESET);
    }

    @Override
    public void println(Object... msgs) {
        print(msgs);
        out.print("\n");
    }

}
