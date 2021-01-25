package com.mcxiv.logger;

import com.mcxiv.logger.util.ByteConsumer;

import java.io.IOException;
import java.io.OutputStream;

abstract class Logger_BiStreamChannel extends Logger_StreamDependencyAdder {

    public Logger_BiStreamChannel(OutputStream streamA, OutputStream streamB) {
        super(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                streamA.write(b);
                streamB.write(b);
            }

            @Override
            public void write(byte[] b) throws IOException {
                streamA.write(b);
                streamB.write(b);
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                streamA.write(b, off, len);
                streamB.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                streamA.flush();
                streamB.flush();
            }

            @Override
            public void close() throws IOException {
                streamA.flush();
                streamB.flush();
            }
        });
    }

    private Logger_BiStreamChannel() {
    }

    public Logger_BiStreamChannel(ByteConsumer consumerA, ByteConsumer consumerB) {
        super(b -> {
            consumerA.consume(b);
            consumerB.consume(b);
        });
    }

}
