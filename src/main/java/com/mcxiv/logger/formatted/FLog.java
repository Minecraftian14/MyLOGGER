package com.mcxiv.logger.formatted;

import com.mcxiv.logger.tools.LogLevel;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.LevelDependent;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

public abstract class FLog extends LevelDependent<FLog> implements Logger_MethodCollection {

    public static FLog getNew() {
        return new Logger_AnnotationCompiler();
    }

    public static FLog getNew(OutputStream stream) {
        return new Logger_AnnotationCompiler(stream);
    }

    public static FLog getNew(ByteConsumer consumer) {
        return new Logger_AnnotationCompiler(consumer);
    }

    public static FLog getNew(StringsConsumer consumer) {
        return new Logger_AnnotationCompiler(consumer);
    }

}
