package com.mcxiv.logger.formatted;

import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringConsumer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

abstract class Logger_StreamDependencyAdder extends FLog {

    protected StringConsumer writer;

    public Logger_StreamDependencyAdder(OutputStream stream) {
        super();
        final PrintStream sw = new PrintStream(stream);
        writer = sw::print;
    }

    public Logger_StreamDependencyAdder() {
        super();
        writer = System.out::print;
    }

    public Logger_StreamDependencyAdder(ByteConsumer consumer) {
        super();
        writer = st -> {
            for (byte b : st.getBytes()) consumer.consume(b);
        };
    }

    public Logger_StreamDependencyAdder(StringConsumer consumer) {
        super();
        writer = consumer;
    }

}
