package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.RandomColor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

public class Decorations {

    public static final String CONSOLE = "console";
    public static final String RAW_FILE = "raw file";
    public static final String TAG = "tag";
    public static final String EMPTY = "empty";

    private static HashMap<Tag, Decoration> decorations_map = new HashMap<>();

    public static Decoration get(String decorator) {

        StackTraceElement element = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < 6; i++) {

            if (!(stackTrace[i].getClassName().endsWith("Logger_MethodImplierBody") || stackTrace[i].getClassName().endsWith("Logger_LogFileWriter") || stackTrace[i].getClassName().contains("$"))) {
                element = stackTrace[i];
                break;
            }
        }
        if (element == null) element = Thread.currentThread().getStackTrace()[3];

//        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace())
//            System.out.println(stackTraceElement.getClassName() + "  " + stackTraceElement.getMethodName());

        Tag tag = new Tag(element.getClassName(), element.getMethodName(), decorator);
        if (decorations_map.containsKey(tag))
            return decorations_map.get(tag);

        else
            try {
                Class<?> clazz = Class.forName(element.getClassName());

                Format format = null;

                for (Method m : clazz.getMethods())
                    if (m.getName().equals(element.getMethodName()))
                        if (m.isAnnotationPresent(Format.class))
                            format = m.getAnnotation(Format.class);

                if (format == null && element.getMethodName().equals("<init>"))
                    for (Constructor<?> c : clazz.getConstructors())
                        if (c.isAnnotationPresent(Format.class))
                            format = c.getAnnotation(Format.class);

                if (format == null)
                    if (clazz.isAnnotationPresent(Format.class))
                        format = clazz.getAnnotation(Format.class);

                if (format != null) {
                    Decoration t = getSpecific(decorator, format.value());
                    decorations_map.put(tag, t);
                    return t;
                }
            } catch (Exception ignored) {
            }

        Decoration t = getRandom(decorator);
        decorations_map.put(tag, t);
        return t;
    }

    public static Decoration getSpecific(String decorator, String... formats) {
        switch (decorator) {
            case CONSOLE:
                return new ConsoleDecoration(formats);
            case TAG:
                return new TagDecoration(formats);
            case RAW_FILE:
                return new RawFileDecoration(formats);
            case EMPTY:
            default:
                return new EmptyDecoration();
        }
    }

    public static Decoration getRandom(String decorator) {
        RandomColor c = new RandomColor();

        String[] codes = new String[]{
                ":#" + c.yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::",
                ":#" + c.getDark().yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::"
        };

        return getSpecific(decorator, codes);
    }


    private static class Tag {

        String className;
        String executableName;
        String decorator;

        public Tag(String className, String executableName, String decorator) {
            this.className = className;
            this.executableName = executableName;
            this.decorator = decorator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag tag = (Tag) o;
            return Objects.equals(className, tag.className) &&
                    Objects.equals(executableName, tag.executableName) &&
                    Objects.equals(decorator, tag.decorator);
        }

        @Override
        public int hashCode() {
            return Objects.hash(className, executableName, decorator);
        }
    }

}
