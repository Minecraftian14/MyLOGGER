package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.RandomColor;

import java.util.regex.Pattern;

import static com.mcxiv.logger.tools.C.map;

public abstract class Decoration {

    Decorate[] decorates;

    public Decoration(String... value) {
    }

    static Pattern re_prepre = Pattern.compile("^([^:]*)[:][:]");
    static Pattern re_pre = Pattern.compile("[:][:]([^:]*)[:]");
    static Pattern re_content = Pattern.compile("(?:(?:[^:][:])|(?:^:))([^:]+)(?:(?:[:][^:])|(?::$))");
    static Pattern re_suf = Pattern.compile("[:]([^:]*)[:][:]");
    static Pattern re_sufsuf = Pattern.compile("[:][:]([^:x]*)$");

    static Pattern re_Ccolor = Pattern.compile("[$]((?:" + map.keySet().stream().sorted((a, b) -> b.length() - a.length()).reduce("", (a, b) -> a.equals("") ? b : a + ")|(?:" + b) + "))");
    static Pattern re_6color = Pattern.compile("[#]([A-Fa-f0-9]{6})");
    static Pattern re_3color = Pattern.compile("[#]([A-Fa-f0-9]{3})");
    static Pattern re_1color = Pattern.compile("[#]([A-Fa-f0-9])");

    static String[] a = new String[]{map.get("BK"), map.get("W"), map.get("R"), map.get("G"), map.get("B"), map.get("Y"), map.get("M"), map.get("C")};

    public static Decoration getRandomDecoration(Class<? extends Decoration> clazz) {

        RandomColor c = new RandomColor();

        switch (clazz.getName()) {
            case "com.mcxiv.logger.decorations.ConsoleDecoration":
                return new ConsoleDecoration(":#" + c.yieldHex() + ": ::",
                        ":#" + c.getBright().yieldHex() + "]: ::",
                        ":#" + c.getDark().yieldHex() + "]: ::",
                        ":#" + c.getBright().yieldHex() + "]");

            case "com.mcxiv.logger.decorations.TagDecoration":
                return new TagDecoration(":#" + c.yieldHex() + ": ::",
                        ":#" + c.getBright().yieldHex() + "]: ::",
                        ":#" + c.getDark().yieldHex() + "]: ::",
                        ":#" + c.getBright().yieldHex() + "]");

            default:
                return new EmptyDecoration();
        }
    }

    public String decorate(String... input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < Math.min(input.length, decorates.length); i++)
            input[i] = decorates[i].decorate(input[i]);

        for (String msg : input) builder.append(msg);
        return builder.toString();
    }

}
