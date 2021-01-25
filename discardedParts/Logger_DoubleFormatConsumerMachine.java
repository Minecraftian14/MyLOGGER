package com.mcxiv.logger;

import com.mcxiv.logger.tools.Configuration;
import com.mcxiv.logger.util.ByteConsumer;

import java.io.OutputStream;
import java.util.function.Consumer;

abstract class Logger_DoubleFormatConsumerMachine extends Logger_PrintingMethodAdder {

    Consumer<String> g;
    Consumer<String> n;
    Consumer<String> i;
    Consumer<String> a;
    Consumer<String> e;
    Consumer<String> s;

    Consumer<Object> g_o;
    Consumer<Object> n_o;
    Consumer<Object> i_o;
    Consumer<Object> a_o;
    Consumer<Object> e_o;
    Consumer<Object> s_o;

    public Logger_DoubleFormatConsumerMachine(OutputStream stream) {
        super(stream);
        initialise();
    }

    public Logger_DoubleFormatConsumerMachine() {
        super();
        initialise();
    }

    public Logger_DoubleFormatConsumerMachine(ByteConsumer consumer) {
        super(consumer);
        initialise();
    }

    public void initialise() {
        initialise(Configuration.getDoubleFormatDefault());
    }

    public void initialise(Configuration config) {

        g = s -> writer.printf(config.g, s);
        n = s -> writer.printf(config.n, s);
        i = s -> writer.printf(config.i, s);
        a = s -> writer.printf(config.a, s);
        e = s -> writer.printf(config.e, s);
        s = s -> writer.printf(config.s, s);

        g_o = o -> writer.printf(config.g, o);
        n_o = o -> writer.printf(config.n, o);
        i_o = o -> writer.printf(config.i, o);
        a_o = o -> writer.printf(config.a, o);
        e_o = o -> writer.printf(config.e, o);
        s_o = o -> writer.printf(config.s, o);

    }

}
