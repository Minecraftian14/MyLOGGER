package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.formatted.fixed.FileLog;
import com.mcxiv.logger.plotting.Plot;
import com.mcxiv.logger.tables.Table;
import com.mcxiv.logger.tools.LogLevel;
import org.junit.Test;
import sun.rmi.runtime.Log;

public class ULogTest {

    @Test
    @Format({":$Btn:", ":$Ytt n:"})
    public void simple() {

        FLog log = ULog.forNew()
                .add(FLog.getNew())
                .add(FileLog.getNew("new.txt"))
                .create();

        log.prt("A","B");

//        Plot.BarGraph.box()
//                .values(0,10,i -> i).create();

        new Thread(() -> log.prtf(":n:").consume("Yo")).start();

        Plot.BarGraph.simple()
                .title("Simple Test")
                .XLabel(0, 20, i -> "Title Name " + i)
                .values(0, 20, i -> (int) (100 * Math.random()))
                .setBarType(Box.B_T)
                .charHeight(30)
                .create(log);

        Plot.BarGraph.box()
                .title("Simple box Test")
                .XLabel(0, 20, i -> "Title Name " + i)
                .values(0, 20, i -> (int) (100 * Math.random()))
                .setBarType(Box.B_T)
                .charHeight(30)
                .create(log);
    }

    @Test
    public void tableTest() {

        FLog log = ULog.forNew()
                .add(FLog.getNew())
                .add(FileLog.getNew("new.txt"))
                .create();

        int len = 10;

        int[] Ace = new int[len];
        int[] Mice = new int[len];
        int[] Oce = new int[len];

        for (int i = 0; i < len; i++) {
            Ace[i] = (int) (Math.random() * 10);
            Mice[i] = (int) (Math.random() * 10);
            Oce[i] = Ace[i] * Mice[i];
        }


        Table.stripped()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.prtf(":n:").consume("Yo");
            }
        }).start();

        Table.box()
//                .setLogLevel(LogLevel.DEBUG)
                .title("%%s")
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("!", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

        Table.empty()
                .title("Hello")
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("!", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

    }
}