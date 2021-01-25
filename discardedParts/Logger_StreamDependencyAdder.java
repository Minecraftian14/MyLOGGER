package com.mcxiv.logger;

import com.mcxiv.logger.util.ByteConsumer;

import java.io.IOException;
import java.io.OutputStream;

abstract class Logger_StreamDependencyAdder extends Log {

    protected OutputStream stream;

    public Logger_StreamDependencyAdder(OutputStream stream) {
        this.stream = stream;
    }

    public Logger_StreamDependencyAdder() {
        this.stream = System.out;
    }

    public Logger_StreamDependencyAdder(ByteConsumer consumer) {
        this.stream = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                consumer.consume((byte) b);
            }

            @Override
            public void write(byte[] bytes) throws IOException {
                for (byte b : bytes)
                    consumer.consume(b);
            }

        };
    }

}
