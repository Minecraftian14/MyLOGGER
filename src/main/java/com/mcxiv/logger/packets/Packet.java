package com.mcxiv.logger.packets;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.Printer;
import com.mcxiv.logger.util.StringsConsumer;

public abstract class Packet implements Printer {

    protected StringBuilder builder;

    public Packet() {
        builder = new StringBuilder();
    }

    @Override
    public abstract void prt(String... msg);

    @Override
    public abstract void prt(Object... obj);

    @Override
    public abstract void raw(String raw);

    @Override
    public abstract StringsConsumer prtf(String... format);

    public abstract void consume();

}
