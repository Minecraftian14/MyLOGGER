package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.tools.LogLevel;
import com.sun.xml.internal.ws.wsdl.parser.MemberSubmissionAddressingWSDLParserExtension;
import org.junit.Test;

import javax.lang.model.element.ExecutableElement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UnknownFormatConversionException;

public class TableTest {

    static FLog log = FLog.getNew();

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

        Table.stripped()
                .title("Yo")
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .row("", "A", "B", "A x B")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .row("", "Only", "Even", "Values")
                .iter(1, len, 2, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

        Table.box()
                .title("Hello")
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

        Table.stripped()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

        LogLevel.VITAL.activate();

        Table.stripped()
                .setLogLevel(LogLevel.DEBUG)
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

//        LogLevel.ALL.activate();

        Table.stripped()
                .head("S.No.", "Number 1", "Number 2", "Answer")
                .iter(0, len, i -> i + 1, i -> Ace[i], i -> Mice[i], i -> Oce[i])
                .create(log);

    }

    @Test
    public void BunchTest() {

        int len = 10000;
        int gs = len / 10;
        Double[] iterations = new Double[len];
        for (int i = 0; i < len; i++) iterations[i] = Math.random();

        Table.stripped()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .formatTitle("@4085eeb")
                .formatHead("@2565ae#fff", "@0f5298#fff")
                .format("@66d3fa", "@55d3fe")
                .create(log);

        Table.box()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .create(log);

        Table.empty()
                .title("Average of Random Numbers")
                .head("S.No.", "Range", "Average")
                .bunch(iterations, len / 10,
                        (gi, g) -> gi,
                        (gi, g) -> (gi * len / 10) + "-" + ((gi + 1) * len / 10),
                        (gi, g) -> String.format("%.3f",  avg(g) )
                )
                .create(log);

    }

    private Double avg(Double[] g) {
        double avg = 0;
        for (Double d : g) avg += d;
        return avg / g.length;
    }


    @Test
    public void TabulationTest() {

        FLog log = FLog.getNew();

        try {

            throw new InternalFrameInternalFrameTitlePaneInternalFrameTitlePaneIconifyButtonPainter();
//            throw new SQLIntegrityConstraintViolationException("Yo");
//            int k = 1/0;
//            log.prt(String.format("%*s", "Hey"));

        } catch (Throwable e) {

            Table.tabulate(log, e);

//            e.printStackTrace();

        }

    }

    private class InternalFrameInternalFrameTitlePaneInternalFrameTitlePaneIconifyButtonPainter extends Throwable {
    }
}