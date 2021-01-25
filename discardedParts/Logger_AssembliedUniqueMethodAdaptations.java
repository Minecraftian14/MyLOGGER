package com.mcxiv.logger;

import com.mcxiv.logger.util.ByteConsumer;

import java.io.OutputStream;

class Logger_AssembliedUniqueMethodAdaptations extends Logger_AssemblyConsumerMachine {

    public Logger_AssembliedUniqueMethodAdaptations(OutputStream stream) {
        super(stream);
    }

    public Logger_AssembliedUniqueMethodAdaptations() {
        super();
    }

    public Logger_AssembliedUniqueMethodAdaptations(ByteConsumer consumer) {
        super(consumer);
    }

    @Override
    public void general(String... i) {
        g.accept(i);
    }

    @Override
    public void notice(String... i) {
        n.accept(i);
    }

    @Override
    public void info(String... i) {
        this.i.accept(i);
    }

    @Override
    public void advert(String... i) {
        a.accept(i);
    }

    @Override
    public void error(String... i) {
        e.accept(i);
    }

    int last_length = 0;

    @Override
    public void status(String... i) {
        for (int j = 0; j < last_length; j++)
            writer.print('\b');
        last_length = 3;
        for (String o : i)
            last_length += o.length();
        s.accept(i);
    }

    @Override
    public void general(Object... i) {
        g_o.accept(i);
    }

    @Override
    public void notice(Object... i) {
        n_o.accept(i);
    }

    @Override
    public void info(Object... i) {
        i_o.accept(i);
    }

    @Override
    public void advert(Object... i) {
        a_o.accept(i);
    }

    @Override
    public void error(Object... i) {
        e_o.accept(i);
    }

    @Override
    public void status(Object... i) {
        for (int j = 0; j < last_length; j++)
            writer.print('\b');
        last_length = 3;
        for (Object o : i)
            last_length += o.toString().length();
        s_o.accept(i);
    }
}
