package com.mcxiv.logger.processor;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;
import java.util.HashMap;

class Logger_AnnotationRetriever extends Logger_StreamDependencyAdder {

    public Logger_AnnotationRetriever(OutputStream stream) {
        super(stream);
    }

    public Logger_AnnotationRetriever() {
        super();
    }

    public Logger_AnnotationRetriever(ByteConsumer consumer) {
        super(consumer);
    }

    public Logger_AnnotationRetriever(StringsConsumer consumer) {
        super(consumer);
    }

    static HashMap<String, Decoration> decorations = new HashMap<>();

    private static Decoration getDecoration() {

        StackTraceElement element = Thread.currentThread().getStackTrace()[3];

        String key = element.getClassName()+":" + element.getMethodName();

        Decoration decoration = ProcessedDecorations.getFor(key);

        if (decoration!=null) return decoration;

        decoration = Decoration.getRandomDecoration();
        ProcessedDecorations.putNew(key, decoration);

        return decoration;
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
