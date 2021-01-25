package com.mcxiv.logger.processor;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

public abstract class ALog extends FLog {

    public static FLog getNew() {
        return new Logger_AnnotationRetriever();
    }

    public static FLog getNew(OutputStream stream) {
        return new Logger_AnnotationRetriever(stream);
    }

    public static FLog getNew(ByteConsumer consumer) {
        return new Logger_AnnotationRetriever(consumer);
    }

    public static FLog getNew(StringsConsumer consumer) {
        return new Logger_AnnotationRetriever(consumer);
    }

}
