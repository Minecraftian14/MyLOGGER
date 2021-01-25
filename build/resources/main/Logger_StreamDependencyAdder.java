package com.mcxiv.logger.processor;

import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;
import java.io.PrintStream;

abstract class Logger_StreamDependencyAdder extends Logger_LevelDependencyAdder {

    protected StringsConsumer writer;

    public Logger_StreamDependencyAdder(OutputStream stream) {
        super();
        final PrintStream sw = new PrintStream(stream);
        writer = st -> {
            for (String s : st) sw.print(s);
        };
    }

    public Logger_StreamDependencyAdder() {
        super();
        writer = st -> {
            for (String s : st) System.out.print(s);
        };
    }

    public Logger_StreamDependencyAdder(ByteConsumer consumer) {
        super();
        writer = sts -> {
            for (String st : sts) for (byte b : st.getBytes()) consumer.consume(b);
        };
    }

    public Logger_StreamDependencyAdder(StringsConsumer consumer) {
        super();
        writer = consumer;
    }

}
