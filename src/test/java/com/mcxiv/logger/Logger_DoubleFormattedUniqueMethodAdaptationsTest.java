package com.mcxiv.logger;

import org.junit.Test;

import java.io.IOException;

public class Logger_DoubleFormattedUniqueMethodAdaptationsTest {

    @Test
    public void simple() {
        Log l = new Logger_DoubleFormattedUniqueMethodAdaptations();

        l.g("Hello", "Hello", "Hello", "Hello");
        l.n("Hello", "Hello", "Hello", "Hello");
        l.i("Hello", "Hello", "Hello", "Hello");
        l.a("Hello", "Hello", "Hello", "Hello");
        l.e("Hello", "Hello", "Hello", "Hello");
        l.s("Hello", "Hello", "Hello", "Hello");
        l.s("World", "World", "World", "World");
    }

    @Test
    public void simple2() {
        StringBuilder builder = new StringBuilder();
        Log l = new Logger_DoubleFormattedUniqueMethodAdaptations(b -> builder.append((char) b));


        l.g("Hello", "Hello", "Hello", "Hello");
        l.n("Hello", "Hello", "Hello", "Hello");
        l.i("Hello", "Hello", "Hello", "Hello");
        l.a("Hello", "Hello", "Hello", "Hello");
        l.e("Hello", "Hello", "Hello", "Hello");
        l.s("Hello", "Hello", "Hello", "Hello");

        System.out.println(builder);
    }


    // Currently working "ONLY" in terminal
    @Test
    public void status() throws InterruptedException, IOException {
        Log l = new Logger_DoubleFormattedUniqueMethodAdaptations();

        l.g("Hello");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            l.s("" + i);
        }

    }

}