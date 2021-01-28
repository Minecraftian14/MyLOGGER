package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.util.StringsConsumer;

class Logger_MethodImplierBody extends Logger_StreamDependencyAdder {

    public Logger_MethodImplierBody(FLog... loggers) {
        super(loggers);
    }

    @Override
    public void prt(String... msg) {
        for (FLog logger : loggers) logger.prt(msg.clone());
    }

    @Override
    public void prt(Object... obj) {
        for (FLog logger : loggers) logger.prt(obj.clone());
    }

    @Override
    public void raw(String raw) {
        for (FLog logger : loggers) logger.raw(raw);
    }

    @Override
    public StringsConsumer prtf(String... format) {
        return msg -> {
            for (FLog logger : loggers) logger.prtf(format).consume(msg.clone());
        };
    }

    @Override
    public Packet newPacket() {
        return new OurPacket();
    }

    private class OurPacket extends Packet {

        Packet[] packets;

        public OurPacket() {
            packets = new Packet[loggers.length];
            for (int i = 0; i < loggers.length; i++) packets[i] = loggers[i].newPacket();
        }

        @Override
        public void prt(String... msg) {
            for (Packet packet : packets) packet.prt(msg.clone());
        }

        @Override
        public void prt(Object... obj) {
            for (Packet packet : packets) packet.prt(obj.clone());
        }

        @Override
        public void raw(String raw) {
            for (Packet packet : packets) packet.raw(raw);
        }

        @Override
        public StringsConsumer prtf(String... format) {
            return msg -> {
                for (Packet packet : packets) packet.prtf(format).consume(msg.clone());
            };
        }

        @Override
        public void consume() {
            for (Packet packet : packets) packet.consume();
        }
    }

}
