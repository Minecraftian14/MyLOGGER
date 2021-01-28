package com.mcxiv.logger.formatted;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

class Logger_MethodImplierBody extends Logger_StreamDependencyAdder {

    public Logger_MethodImplierBody(OutputStream stream) {
        super(stream);
    }

    public Logger_MethodImplierBody() {
        super();
    }

    public Logger_MethodImplierBody(ByteConsumer consumer) {
        super(consumer);
    }

    public Logger_MethodImplierBody(StringsConsumer consumer) {
        super(consumer);
    }

    @Override
    public void prt(String... msg) {
        Decoration decoration = Decorations.get(Decorations.CONSOLE);
        writer.consume(decoration.decorate(msg));
    }

    @Override
    public void prt(Object... obj) {
        Decoration decoration = Decorations.get(Decorations.CONSOLE);
        String[] stf = new String[obj.length];
        for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
        writer.consume(decoration.decorate(stf));
    }

    @Override
    public void raw(String raw) {
        prtf("").consume(raw);
    }

    @Override
    public StringsConsumer prtf(String... format) {
        Decoration decoration = Decorations.getSpecific(Decorations.CONSOLE, format);
        return msg -> writer.consume(decoration.decorate(msg));
    }

    @Override
    public Packet newPacket() {
        return new OurPacket();
    }

    private class OurPacket extends Packet{

        @Override
        public void prt(String... msg) {
            Decoration decoration = Decorations.get(Decorations.CONSOLE);
            builder.append(decoration.decorate(msg));
        }

        @Override
        public void prt(Object... obj) {
            Decoration decoration = Decorations.get(Decorations.CONSOLE);
            String[] stf = new String[obj.length];
            for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
            builder.append(decoration.decorate(stf));
        }

        @Override
        public void raw(String raw) {
            prtf("").consume(raw);
        }

        @Override
        public StringsConsumer prtf(String... format) {
            Decoration decoration = Decorations.getSpecific(Decorations.CONSOLE, format);
            return msg -> builder.append(decoration.decorate(msg));
        }

        @Override
        public void consume() {
            Logger_MethodImplierBody.this.raw(builder.toString());
        }
    }

}
