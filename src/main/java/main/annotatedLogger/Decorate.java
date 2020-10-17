package main.annotatedLogger;

import main.utlities.ConsoleColors;

public interface Decorate {
    String decorate(String s);

    Decorate red = s -> ConsoleColors.ANSI.RED + s + ConsoleColors.ANSI.RESET;
}
