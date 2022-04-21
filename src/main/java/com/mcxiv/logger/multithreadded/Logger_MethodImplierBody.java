package com.mcxiv.logger.multithreadded;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.util.StringsConsumer;

import java.util.concurrent.ExecutorService;

class Logger_MethodImplierBody extends Logger_StreamDependencyAdder {

    public Logger_MethodImplierBody(FLog logger) {
        super(logger);
    }

    public Logger_MethodImplierBody(ExecutorService executorService, FLog logger) {
        super(executorService, logger);
    }

    @Override
    public void prt(String... msg) {
        Decoration decoration = Decorations.get(decorator_name);
        executorService.submit(() -> logger.raw(decoration.decorate(msg)));
    }

    @Override
    public void prt(Object... obj) {
        Decoration decoration = Decorations.get(decorator_name);
        executorService.submit(() -> {
            String[] stf = new String[obj.length];
            for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
            logger.raw(decoration.decorate(stf));
        });
    }

    @Override
    public void raw(String raw) {
        executorService.submit(() -> logger.raw(raw));
    }

    @Override
    public StringsConsumer prtf(String... format) {
        return msg -> executorService.submit(() -> {
            Decoration decoration = Decorations.getSpecific(null, decorator_name, format);
            logger.raw(decoration.decorate(msg));
        });
    }

    @Override
    public Packet newPacket() {
        return new OurPacket();
    }

    private class OurPacket extends Packet {

        Packet packet;

        public OurPacket() {
            packet = logger.newPacket();
        }

        @Override
        public void prt(String... msg) {
            Decoration decoration = Decorations.get(decorator_name);
            executorService.submit(() -> packet.raw(decoration.decorate(msg)));
        }

        @Override
        public void prt(Object... obj) {
            Decoration decoration = Decorations.get(decorator_name);
            executorService.submit(() -> {
                String[] stf = new String[obj.length];
                for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
                packet.raw(decoration.decorate(stf));
            });
        }

        @Override
        public void raw(String raw) {
            executorService.submit(() -> packet.raw(raw));
        }

        @Override
        public StringsConsumer prtf(String... format) {
            return msg -> executorService.submit(() -> {
                Decoration decoration = Decorations.getSpecific(null, decorator_name, format);
                packet.raw(decoration.decorate(msg));
            });
        }

        @Override
        public void consume() {
            executorService.submit(() -> packet.consume());
        }
    }
}
