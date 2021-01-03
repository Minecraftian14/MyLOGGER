package com.mcxiv.logger.decorations;

import org.junit.Test;

public class ConsoleDecorationTest {

    @Test
    public void test1() {

        ConsoleDecoration d = new ConsoleDecoration(":$B:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(":$Gb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(":$RBKb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration("Hello:$Gb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration("AA::Hell:$Yb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration("AA::Hell:$Yb:A::d");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration("Yo:: :$Ybu: After Word ::Bye");
        System.out.println(d.decorate("Hey"));

    }

}