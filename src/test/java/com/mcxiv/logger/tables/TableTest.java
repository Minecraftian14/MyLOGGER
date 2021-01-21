package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.tools.LogLevel;
import org.junit.Test;

public class TableTest {

    @Test
    public void SimpleTest() {

        int len = 10;

        int[] Ace = new int[len];
        int[] Mice = new int[len];
        int[] Oce = new int[len];

        for (int i = 0; i < len; i++) {
            Ace[i] = (int) (Math.random() * 10);
            Mice[i] = (int) (Math.random() * 10);
            Oce[i] = Ace[i] * Mice[i];
        }

        System.out.print(Table.stripped()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create());

        System.out.print(Table.box()
                .title("Hello")
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("!", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create());
    }

    @Test
    public void LevelTest() {

        int len = 4;

        int[] Ace = new int[len];
        int[] Mice = new int[len];
        int[] Oce = new int[len];

        for (int i = 0; i < len; i++) {
            Ace[i] = (int) (Math.random() * 10);
            Mice[i] = (int) (Math.random() * 10);
            Oce[i] = Ace[i] * Mice[i];
        }

        FLog log = FLog.getNew();

        log.raw(Table.stripped().warn()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create());

        LogLevel.VITAL.activate();

        log.raw(Table.stripped().warn()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create());

//        LogLevel.ALL.activate();

        log.raw(Table.stripped().warn()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create());

    }

    @Test
    public void BunchTest() {

        int len = 10000;
        int gs = len / 10;
        Double[] iterations = new Double[len];
        for (int i = 0; i < len; i++) iterations[i] = Math.random();

        System.out.print(Table.stripped()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .formatTitle(":@4085eeb:")
                .formatHead(":@2565ae#fff:", ":@0f5298#fff:")
                .format(":@66d3fa:", ":@55d3fe:")
                .create());

        System.out.print(Table.box()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .create());

        System.out.print(Table.empty()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .create());

    }

    private Double avg(Double[] g) {
        double avg = 0;
        for (Double d : g) avg += d;
        return avg / g.length;
    }

}