package com.mcxiv.logger;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Logger_HeadedAssembliedUniqueMethodAdaptationsTest {

    @Test
    public void simple() {
        Log l = new Logger_HeadedAssembliedUniqueMethodAdaptations();

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
        Log l = new Logger_HeadedAssembliedUniqueMethodAdaptations(b -> builder.append((char) b));

        l.g("Hello", "Hello", "Hello", "Hello");
        l.n("Hello", "Hello", "Hello", "Hello");
        l.i("Hello", "Hello", "Hello", "Hello");
        l.a("Hello", "Hello", "Hello", "Hello");
        l.e("Hello", "Hello", "Hello", "Hello");
        l.s("Hello", "Hello", "Hello", "Hello");

        System.out.println(builder);
    }

    @Test
    public void status() throws InterruptedException, IOException {
        Log l = new Logger_HeadedAssembliedUniqueMethodAdaptations();

        l.g("Hello");
        for (int i = 1; i <= 10; i++) {
            Thread.sleep(1000);
            l.s("" + i);
        }

    }

}