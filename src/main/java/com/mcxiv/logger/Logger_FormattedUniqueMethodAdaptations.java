package com.mcxiv.logger;

import com.mcxiv.logger.util.ByteConsumer;

import java.io.OutputStream;
import java.util.StringJoiner;

class Logger_FormattedUniqueMethodAdaptations extends Logger_SimpleFormatConsumerMachine {

    public Logger_FormattedUniqueMethodAdaptations(OutputStream stream) {
        super(stream);
    }

    public Logger_FormattedUniqueMethodAdaptations() {
        super();
    }

    public Logger_FormattedUniqueMethodAdaptations(ByteConsumer consumer) {
        super(consumer);
    }

    @Override
    public void general(String... i) {
        g.accept(String.join(" ", i));
    }

    @Override
    public void notice(String... i) {
        n.accept(String.join(" ", i));
    }

    @Override
    public void info(String... i) {
        this.i.accept(String.join(" ", i));
    }

    @Override
    public void advert(String... i) {
        a.accept(String.join(" ", i));
    }

    @Override
    public void error(String... i) {
        e.accept(String.join(" ", i));
    }

    int last_length = 0;

    @Override
    public void status(String... i) {
        StringBuilder joiner = new StringBuilder();
        for (int j = 0; j < last_length; j++) joiner.append("\b");
        for (String cs : i) joiner.append(cs).append(" ");
        s.accept(joiner.append("\b").toString());
        last_length = joiner.length() - last_length;
    }

    @Override
    public void general(Object... i) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object cs : i) joiner.add(cs.toString());
        g.accept(joiner.toString());
    }

    @Override
    public void notice(Object... i) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object cs : i) joiner.add(cs.toString());
        n.accept(joiner.toString());
    }

    @Override
    public void info(Object... i) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object cs : i) joiner.add(cs.toString());
        this.i.accept(joiner.toString());
    }

    @Override
    public void advert(Object... i) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object cs : i) joiner.add(cs.toString());
        a.accept(joiner.toString());
    }

    @Override
    public void error(Object... i) {
        StringJoiner joiner = new StringJoiner(" ");
        for (Object cs : i) joiner.add(cs.toString());
        e.accept(joiner.toString());
    }

    @Override
    public void status(Object... i) {
        StringBuilder joiner = new StringBuilder();
        for (int j = 0; j < last_length; j++) joiner.append("\b");
        for (Object cs : i) joiner.append(cs.toString()).append(" ");
        s.accept(joiner.append("\b").toString());
        last_length = joiner.length() - last_length;
    }
}
