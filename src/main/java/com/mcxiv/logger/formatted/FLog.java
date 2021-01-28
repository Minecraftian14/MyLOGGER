package com.mcxiv.logger.formatted;

import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.LevelDependent;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

public abstract class FLog extends LevelDependent<FLog> implements Logger_MethodCollection {

    protected String decorator_name = Decorations.CONSOLE;

    public static FLog getNew() {
        return new Logger_MethodImplierBody();
    }

    public static FLog getNew(OutputStream stream) {
        return new Logger_MethodImplierBody(stream);
    }

    public static FLog getNew(ByteConsumer consumer) {
        return new Logger_MethodImplierBody(consumer);
    }

    public static FLog getNew(StringsConsumer consumer) {
        return new Logger_MethodImplierBody(consumer);
    }

    @Override
    public void setDecorationType(String name) {
        decorator_name=name;
    }

    @Override
    public String getDecorationType() {
        return decorator_name;
    }

    protected static final FLog EMPTY_VESSEL = new FLog() {
        @Override
        public Packet newPacket() {
            return null;
        }

        @Override
        public FLog provide() {
            return null;
        }

        @Override
        public FLog provideEmpty() {
            return null;
        }

        @Override
        public void prt(String... msg) {
        }

        @Override
        public void prt(Object... obj) {
        }

        @Override
        public void raw(String raw) {
        }


        @Override
        public StringsConsumer prtf(String... format) {
            return null;
        }
    };
}
