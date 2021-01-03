package com.mcxiv.logger.formatted;

import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringConsumer;

import java.io.OutputStream;

public abstract class FLog extends Logger_DecorationManager {

    public static FLog getNew() {
        return new Logger_AnnotationCompiler();
    }

    public static FLog getNew(OutputStream stream) {
        return new Logger_AnnotationCompiler(stream);
    }

    public static FLog getNew(ByteConsumer consumer) {
        return new Logger_AnnotationCompiler(consumer);
    }

    public static FLog getNew(StringConsumer consumer) {
        return new Logger_AnnotationCompiler(consumer);
    }

}
