package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.RandomColor;

import java.util.function.Function;
import java.util.regex.Pattern;

import static com.mcxiv.logger.tools.C.map;

public abstract class Decoration {

    private static Function<String[], Decoration> decorator = ConsoleDecoration::new;
    private static String DECORATION_CLASS = ConsoleDecoration.class.getName();

    public static Decoration getDecoration(String... codes) {
        return decorator.apply(codes);
    }

    public static void setDecoration(Function<String[], Decoration> decoration) {
        decorator = decoration;
        DECORATION_CLASS = decoration.apply(new String[0]).getClass().getName();
    }


    Decorate[] decorates;
    boolean last_one_repeats = false;
    int repeater_index = -1;

    public Decoration(String... value) {
    }

    static Pattern re_prepre = Pattern.compile("^([^:]*)[:][:]");
    static Pattern re_pre = Pattern.compile("[:][:]([^:]*)[:]");
    static Pattern re_content = Pattern.compile("(?:(?:[^:][:])|(?:^:))([^:]+)(?:(?:[:][^:])|(?::$))");
    static Pattern re_suf = Pattern.compile("[:]([^:]*)[:][:]");
    static Pattern re_sufsuf = Pattern.compile("[:][:]([^:x]*)$");

    static Pattern re_formatting = Pattern.compile("([%][0-9-]*[s])");
    static Pattern re_centerFormatting = Pattern.compile("[%][*]([0-9]+)[s]");
    static Pattern re_timeFormat = Pattern.compile("[<]([\\w /;]+)[>]");
    static Pattern re_wordWrap = Pattern.compile("[w]([0-9]+)[w]");

    static Pattern re_Ccolor = Pattern.compile("[$]((?:" + map.keySet().stream().sorted((a, b) -> b.length() - a.length()).reduce("", (a, b) -> a.equals("") ? b : a + ")|(?:" + b) + "))");
    static Pattern re_6color = Pattern.compile("[#]([A-Fa-f0-9]{6})");
    static Pattern re_3color = Pattern.compile("[#]([A-Fa-f0-9]{3})");
    static Pattern re_1color = Pattern.compile("[#]([A-Fa-f0-9])");
    static Pattern re_6Bcolor = Pattern.compile("[@]([A-Fa-f0-9]{6})");
    static Pattern re_3Bcolor = Pattern.compile("[@]([A-Fa-f0-9]{3})");
    static Pattern re_1Bcolor = Pattern.compile("[@]([A-Fa-f0-9])");
    static Pattern re_Scolor = Pattern.compile("([\\[][@]?[A-Fa-f0-9]{6,8}[]])");

    static String[] a = new String[]{map.get("BK"), map.get("W"), map.get("R"), map.get("G"), map.get("B"), map.get("Y"), map.get("M"), map.get("C")};

    public static Decoration getRandomDecoration() {

        RandomColor c = new RandomColor();

        String[] codes = new String[]{
                ":#" + c.yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::",
                ":#" + c.getDark().yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::"
        };

        switch (DECORATION_CLASS) {
            case "com.mcxiv.logger.decorations.ConsoleDecoration":
                return new ConsoleDecoration(codes);

            case "com.mcxiv.logger.decorations.TagDecoration":
                return new TagDecoration(codes);

            default:
                return new EmptyDecoration();
        }
    }

    public static String center(int len, String txt) {
        txt = String.format("%" + (len - txt.length()) / 2 + "s", " ") + txt;
        return String.format("%-" + len + "s", txt);
    }

    public String decorate(String... input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < Math.min(input.length, decorates.length); i++)
            input[i] = decorates[i].decorate(input[i]);

        if (last_one_repeats && decorates.length < input.length)
            for (int i = decorates.length; i < input.length; i++)
                input[i] = decorates[repeater_index].decorate(input[i]);

        for (String msg : input) builder.append(msg);
        return builder.toString();
    }

}
