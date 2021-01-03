package com.mcxiv.logger;

import org.junit.Test;

import java.io.IOException;

public class Logger_Formatted_UniqueMethodAdaptationsTest {

    @Test
    public void simple() {
        Logger_FormattedUniqueMethodAdaptations l = new Logger_FormattedUniqueMethodAdaptations();

        l.g("Hello", "Hello", "Hello", "Hello");
        l.n("Hello", "Hello", "Hello", "Hello");
        l.i("Hello", "Hello", "Hello", "Hello");
        l.a("Hello", "Hello", "Hello", "Hello");
        l.e("Hello", "Hello", "Hello", "Hello");
        l.s("Hello", "Hello", "Hello", "Hello");
    }

    @Test
    public void simple2() {
        StringBuilder builder = new StringBuilder();
        Logger_FormattedUniqueMethodAdaptations l = new Logger_FormattedUniqueMethodAdaptations(b -> builder.append((char) b));

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
        Logger_FormattedUniqueMethodAdaptations l = new Logger_FormattedUniqueMethodAdaptations();

        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            l.s("" + i);
        }
    }

}