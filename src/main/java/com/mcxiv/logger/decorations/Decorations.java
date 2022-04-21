package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.RandomColor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Objects;

public class Decorations {

    public static final String CONSOLE = "console";
    public static final String RAW = "raw file";
    public static final String TAG = "tag";
    public static final String HTML = "html";
    public static final String EMPTY = "empty";

    private static final HashMap<Tag, Decoration> decorations_map = new HashMap<>();

    public static void put(Tag tag, Decoration decoration) {
        decorations_map.put(tag, decoration);
    }

    public static Decoration get(String decorator) {

        StackTraceElement element = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 2; i < Math.min(16, stackTrace.length); i++) {
//            if (!(stackTrace[i].getClassName().endsWith("Logger_MethodImplierBody") || stackTrace[i].getClassName().endsWith("Logger_LogFileWriter") || stackTrace[i].getClassName().contains("$"))) {
            String n = stackTrace[i].getClassName();
            if(n.startsWith("java")) // TODO: Can we just ignore all internal calls by anything in java package? Like, in the end they cant even have the format annotations...
                continue;
            n = n.substring(n.lastIndexOf(".") + 1);
            if (!n.startsWith("Logger_")) {
                element = stackTrace[i];//Logger_AnnotationRetriever
                break;
            }
        }
        if (element == null) element = Thread.currentThread().getStackTrace()[3];

//        System.out.println(element.getClassName());
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
                    Decoration t = getSpecific(tag, decorator, format.value());
                    decorations_map.put(tag, t);
                    return t;
                }
            } catch (Exception ignored) {
            }

        Decoration t = getRandom(tag, decorator);
        decorations_map.put(tag, t);
        return t;
    }

    public static Decoration getSpecific(Tag tag, String decorator, String... formats) {
        switch (decorator) {
            case CONSOLE:
                return new ConsoleDecoration(tag, formats);
            case TAG:
                return new TagDecoration(tag, formats);
            case HTML:
                return new HTMLDecoration(tag, formats);
            case RAW:
                return new RawDecoration(tag, formats);
            case EMPTY:
            default:
                return new EmptyDecoration();
        }
    }

    public static Decoration getRandom(Tag tag, String decorator) {
        RandomColor c = new RandomColor();

        String[] codes = new String[]{
                ":#" + c.yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::",
                ":#" + c.getDark().yieldHex() + ": ::",
                ":#" + c.getBright().yieldHex() + ": ::"
        };

        return getSpecific(tag, decorator, codes);
    }


    public static class Tag {

        String packageName;
        String className;
        String executableName;
        String decorator;

        public Tag(String classAddress, String executableName, String decorator) {
            for (int i = 0; i < classAddress.length(); i++) {
                if (Character.isUpperCase(classAddress.charAt(i))) {
                    packageName = classAddress.substring(0, i - 1);
                    className = classAddress.substring(i);
                }
            }
            this.executableName = executableName;
            this.decorator = decorator;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Tag tag = (Tag) o;
            return Objects.equals(packageName, tag.packageName) &&
                   Objects.equals(className, tag.className) &&
                   Objects.equals(executableName, tag.executableName) &&
                   Objects.equals(decorator, tag.decorator);
        }

        @Override
        public int hashCode() {
            return Objects.hash(packageName, className, executableName, decorator);
        }
    }

}
