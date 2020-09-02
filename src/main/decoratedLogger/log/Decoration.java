package main.decoratedLogger.log;

import main.utlities.ConsoleColors.ANSI;
import main.utlities.RandomColour;

import java.util.List;

import static main.utlities.ConsoleColors.ANSI.map;
import static main.utlities.Html.Wrap.*;

public class Decoration {

    public static final Decoration DEFAULT = new Decoration("ub", "", "", "n");
    Decorate[] decorates_for_console;
    Decorate[] decorates_for_web;

    /**
     * b -> bold
     * i -> italics
     * _ -> underlined
     * h -> highlighted
     * s -> small
     * - -> striked
     * . -> subscript
     * ' -> superscript
     * n -> newline, more the number of n, more new lines
     * t -> tab, more the number of t, more the tabs
     * <p>
     * [237abf] | [1f4] -> colour in html
     * ANSI code -> colour in console
     * <p>
     * :expr -> expr to be used as separator.
     * <strong>Please use it in the end!!!</strong>
     *
     * @param codes
     */
    public Decoration(String... codes) {

        decorates_for_console = new Decorate[codes.length];
        decorates_for_web = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];

            /*            boolean bold = code.contains("b");
            boolean ital = code.contains("i");
            boolean unde = code.contains("_");
            boolean mark = code.contains("h");
            boolean smal = code.contains("s");
            boolean dele = code.contains("-");
            boolean subs = code.contains(".");
            boolean supe = code.contains("'");*/

            Decorate d = s -> "_" + s + "_";

            String colour = code.contains("[") && code.contains("]") ?
                    code.substring(code.indexOf("[") + 1, code.indexOf("]")) : "";

            StringBuilder format_for_console = new StringBuilder();
            map.values().forEach(s -> {
                if (code.contains(s)) format_for_console.append(s);
            });
            if (code.contains("b") && format_for_console.indexOf(ANSI.BOLD) == -1) format_for_console.append(ANSI.BOLD);
            if (code.contains("_") && format_for_console.indexOf(ANSI.UNDERLINED) == -1)
                format_for_console.append(ANSI.UNDERLINED);

            decorates_for_console[i] = s -> format_for_console.toString() + s + ANSI.RESET
                    + (code.contains(":") ? code.substring(code.indexOf(":") + 1) : "")
                    + (code.contains("n") ? "\n" : "");

            String format_for_web = "*";
            for (int j = 0; j < code.chars().filter(value -> value == 'n').count(); j++)
                format_for_web = "   " + format_for_web;
            if (code.contains("b")) format_for_web = BOLD(format_for_web);
            if (code.contains("i")) format_for_web = ITAL(format_for_web);
            if (code.contains("_")) format_for_web = UNDE(format_for_web);
            if (code.contains("h")) format_for_web = MARK(format_for_web);
            if (code.contains("s")) format_for_web = SMAL(format_for_web);
            if (code.contains("-")) format_for_web = DELE(format_for_web);
            if (code.contains(".")) format_for_web = SUBS(format_for_web);
            if (code.contains("'")) format_for_web = SUPE(format_for_web);
            if (!colour.equals("")) format_for_web = COLO(format_for_web, colour);
//            if (code.contains("n")) format_for_web = BREK(format_for_web);
            for (int j = 0; j < code.chars().filter(value -> value == 'n').count(); j++)
                format_for_web = BREK(format_for_web);

            String final_format_for_web = format_for_web;
            decorates_for_web[i] = s -> final_format_for_web.substring(0, final_format_for_web.indexOf("*"))
                    + s + (code.contains(":") ? code.substring(code.indexOf(":") + 1) : "")
                    + final_format_for_web.substring(final_format_for_web.indexOf("*") + 1);
        }
    }

    public static Decoration getRandomDecoration() {
        RandomColour c = new RandomColour();

        int ind = (int) (8 * Math.random());
        String c1 = List.of(map.get("0"), map.get("W"), map.get("R"), map.get("G"), map.get("B"), map.get("Y"), map.get("M"), map.get("C")).get(ind);
        String c2 = List.of(map.get("0B"), map.get("WB"), map.get("RB"), map.get("GB"), map.get("BB"), map.get("YB"), map.get("MB"), map.get("CB")).get(ind);

        return new Decoration(c1 + "b[" + c.yieldHex() + "]: ",
                c2 + "[" + c.getBright().yieldHex() + "]: ",
                c1 + "b[" + c.getDark().yieldHex() + "]: ",
                c2 + "n[" + c.getBright().yieldHex() + "]");
    }

    public String decorate_console(String... input) {
        StringBuilder builder = new StringBuilder();
        String[] messages = input.clone();

        for (int i = 0; i < Math.min(messages.length, decorates_for_console.length); i++)
            messages[i] = decorates_for_console[i].decorate(messages[i]);

        for (String msg : messages) builder.append(msg);
        return builder.toString();
    }

    public String decorate_web(String... input) {
        StringBuilder builder = new StringBuilder();
        String[] messages = input.clone();

        for (int i = 0; i < Math.min(messages.length, decorates_for_web.length); i++)
            messages[i] = decorates_for_web[i].decorate(messages[i]);

        for (String msg : messages) builder.append(msg);
        return builder.toString();
    }

    public static void main(String[] args) {
        Decoration d = new Decoration("bi[ff0000]" + map.get("BB") + map.get("B"),
                "h'[0000ff]" + map.get("B") + map.get("YBBck"), "-s[ff00ff]" + map.get("M"),
                "_.[00ff00]" + map.get("G") + map.get("u"));

        Decoration d2 = new Decoration("bi[ff0000]: ", "h'[0000ff]:hehehe", "-s[ff00ff]:   ", "_.[00ff00]");

        System.out.println(d.decorate_console("Hello ", "World ", "buddy", "!"));
        System.out.println(d.decorate_web("Hello ", "World ", "buddy", "!"));

        System.out.println(d2.decorate_console("Hello", "World", "buddy", "!"));
        System.out.println(d2.decorate_web("Hello", "World", "buddy", "!"));

        System.out.println(DEFAULT.decorate_console("Hello", "World", "buddy", "!"));
        System.out.println(DEFAULT.decorate_web("Hello ", "World ", "buddy", "!"));

        System.out.println(new Decoration("b", "s:k", "n").decorate_web("A", "B", "C"));

    }

}
