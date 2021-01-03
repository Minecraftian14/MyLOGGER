package com.mcxiv.logger;

import com.mcxiv.logger.tools.C;
import com.mcxiv.logger.tools.Configuration;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.TriConsumer;

import java.io.OutputStream;
import java.util.function.Consumer;

abstract class Logger_AssemblyConsumerMachine extends Logger_PrintingMethodAdder {

    Consumer<String[]> g;
    Consumer<String[]> n;
    Consumer<String[]> i;
    Consumer<String[]> a;
    Consumer<String[]> e;
    Consumer<String[]> s;

    Consumer<Object[]> g_o;
    Consumer<Object[]> n_o;
    Consumer<Object[]> i_o;
    Consumer<Object[]> a_o;
    Consumer<Object[]> e_o;
    Consumer<Object[]> s_o;

    public Logger_AssemblyConsumerMachine(OutputStream stream) {
        super(stream);
        initialise();
    }

    public Logger_AssemblyConsumerMachine() {
        super();
        initialise();
    }

    public Logger_AssemblyConsumerMachine(ByteConsumer consumer) {
        super(consumer);
        initialise();
    }

    public void initialise() {
        initialise(Configuration.getPrependableDefault());
    }

    public void initialise(Configuration config) {

        TriConsumer<String, String[], String> helper = (pre, msg, pos) -> {
            print(pre);
            for (String m : msg) print(m + " ");
            print(pos);
        };

        g = s -> helper.accept(config.g, s, C.RS + "\b\n");
        n = s -> helper.accept(config.n, s, C.RS + "\b\n");
        i = s -> helper.accept(config.i, s, C.RS + "\b\n");
        a = s -> helper.accept(config.a + " ", s, " " + C.RS + "\b\n");
        e = s -> helper.accept(config.e, s, C.RS + "\b\n");
        s = s -> helper.accept(config.s, s, C.RS + "\b");

        TriConsumer<String, Object[], String> objectHelper = (pre, msg, pos) -> {
            print(pre);
            for (Object m : msg) print(m);
            print(pos);
        };

        g_o = o -> objectHelper.accept(config.g, o, C.RS + "\n");
        n_o = o -> objectHelper.accept(config.n, o, C.RS + "\n");
        i_o = o -> objectHelper.accept(config.i, o, C.RS + "\n");
        a_o = o -> objectHelper.accept(config.a + " ", o, " " + C.RS + "\n");
        e_o = o -> objectHelper.accept(config.e, o, C.RS + "\n");
        s_o = o -> objectHelper.accept(config.s, o, C.RS);

    }

}
