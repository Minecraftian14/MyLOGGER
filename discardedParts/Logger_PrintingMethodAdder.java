package com.mcxiv.logger;

import com.mcxiv.logger.util.ByteConsumer;

import java.io.OutputStream;
import java.io.PrintStream;

abstract class Logger_PrintingMethodAdder extends Logger_StreamDependencyAdder {

    PrintStream writer;

    static final char[] t = "true".toCharArray();
    static final char[] f = "false".toCharArray();

    public Logger_PrintingMethodAdder(OutputStream stream) {
        super(stream);
        writer = new PrintStream(this.stream);
    }

    public Logger_PrintingMethodAdder() {
        super();
        writer = new PrintStream(this.stream);
    }

    public Logger_PrintingMethodAdder(ByteConsumer consumer) {
        super(consumer);
        writer = new PrintStream(this.stream);
    }

    public void print(boolean b) {
        writer.print(b ? t : f);
    }

    public void print(char c) {
        writer.print(c);
    }

    public void print(int i) {
        writer.print(i);
    }

    public void print(long l) {
        writer.print(l);
    }

    public void print(float f) {
        writer.print(f);
    }

    public void print(double d) {
        writer.print(d);
    }

    public void print(char[] s) {
        writer.print(s);
    }

    public void print(String s) {
        if (s == null) s = "null";
        writer.print(s);
    }

    public void print(Object obj) {
        writer.print(obj);
    }

    public void println() {
        writer.println();
    }

    public void println(boolean x) {
        writer.println(x ? t : f);
    }

    public void println(char x) {
        writer.println(x);
    }

    public void println(int x) {
        writer.println(x);
    }

    public void println(long x) {
        writer.println(x);
    }

    public void println(float x) {
        writer.println(x);
    }

    public void println(double x) {
        writer.println(x);
    }

    public void println(char[] x) {
        writer.println(x);
    }

    public void println(String x) {
        writer.println(x);
    }

    public void println(Object x) {
        writer.println(x);
    }



}
