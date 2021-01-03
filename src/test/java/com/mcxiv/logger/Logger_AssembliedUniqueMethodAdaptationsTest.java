package com.mcxiv.logger;

import org.junit.Test;

import java.io.IOException;

public class Logger_AssembliedUniqueMethodAdaptationsTest {

    @Test
    public void simple() {
        Logger_AssembliedUniqueMethodAdaptations l = new Logger_AssembliedUniqueMethodAdaptations();

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
        Logger_AssembliedUniqueMethodAdaptations l = new Logger_AssembliedUniqueMethodAdaptations(b -> builder.append((char) b));

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
        Logger_MethodCollection l = new Logger_AssembliedUniqueMethodAdaptations();

        l.g("Hello");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            l.s("" + i);
        }

    }

}
