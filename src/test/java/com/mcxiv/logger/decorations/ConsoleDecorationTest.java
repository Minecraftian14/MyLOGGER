package com.mcxiv.logger.decorations;

import org.junit.Test;

public class ConsoleDecorationTest {

    @Test
    public void test1() {

        ConsoleDecoration d = new ConsoleDecoration(null, ":$B:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, ":$Gb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, ":$RBKb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, "Hello:$Gb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, "AA::Hell:$Yb:");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, "AA::Hell:$Yb:A::d");
        System.out.println(d.decorate("Hey"));

        d = new ConsoleDecoration(null, "Yo:: :$Ybu: After Word ::Bye");
        System.out.println(d.decorate("Hey"));

    }

}