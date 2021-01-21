package com.mcxiv.logger.formatted;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

class Logger_AnnotationCompiler extends Logger_StreamDependencyAdder {

    public Logger_AnnotationCompiler(OutputStream stream) {
        super(stream);
    }

    public Logger_AnnotationCompiler() {
        super();
    }

    public Logger_AnnotationCompiler(ByteConsumer consumer) {
        super(consumer);
    }

    public Logger_AnnotationCompiler(StringsConsumer consumer) {
        super(consumer);
    }

    static HashMap<String, Decoration> decorations = new HashMap<>();

    private static Decoration getDecoration() {

        StackTraceElement element = Thread.currentThread().getStackTrace()[3];

        String key = element.getClassName() + element.getMethodName();
        if (decorations.containsKey(key))
            return decorations.get(key);

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
                    Decoration t = Decoration.getDecoration(format.value());
                    decorations.put(key, t);
                    return t;
                }
            } catch (Exception ignored) {
            }

        Decoration t = Decoration.getRandomDecoration();
        decorations.put(key, t);
        return t;
    }

    @Override
    public void prt(String... msg) {
        Decoration decoration = getDecoration();
        writer.consume(decoration.decorate(msg));
    }

    @Override
    public void prt(Object... obj) {
        Decoration decoration = getDecoration();
        String[] stf = new String[obj.length];
        for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
        writer.consume(decoration.decorate(stf));
    }

    @Override
    public void raw(String raw) {
        writer.consume(raw);
    }

    @Override
    public StringsConsumer prtf(String... format) {
        Decoration decoration = Decoration.getDecoration(format);
        return msg -> writer.consume(decoration.decorate(msg));
    }
}
