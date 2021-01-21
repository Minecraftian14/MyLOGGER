package com.mcxiv.logger.formatted;

import com.mcxiv.logger.tools.LogLevel;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

public abstract class FLog implements LogLevel.LogLevelAdaptor<FLog>, Logger_MethodCollection {

    private static FLog emptyVessel = new FLog() {
        @Override
        public FLog vital() {
            return null;
        }

        @Override
        public FLog error() {
            return null;
        }

        @Override
        public FLog warn() {
            return null;
        }

        @Override
        public FLog notice() {
            return null;
        }

        @Override
        public FLog debug() {
            return null;
        }

        @Override
        public FLog general() {
            return null;
        }

        @Override
        public void prt(String... msg) {
        }
        @Override
        public void prt(Object... obj) {
        }
        @Override
        public StringsConsumer prtf(String... format) {
            return st -> {
            };
        }
    };

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

    protected static FLog getEmptyVessel() {
        return emptyVessel;
    }
}
