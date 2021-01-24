package com.mcxiv.logger.plotting;

import com.mcxiv.logger.boxUtilities.Box;
import com.mcxiv.logger.decorations.ConsoleDecoration;
import com.mcxiv.logger.formatted.FLog;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//                .YLabel(0, 5, i -> ""+i)
public class PlotTest {

    @Test
    public void SuperSimpleTest() {
        FLog log = FLog.getNew();

        int[] age = new int[]{6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94, 4, 90, 44, 86, 86, 46, 20, 91, 89, 86, 47, 8, 45, 56};

        log.raw(Plot.BarGraph.box()
                .values(age)
                .create()
        );
    }

    @Test
    public void SimpleTest1() {
        FLog log = FLog.getNew();

        int[] age = new int[]{6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94, 4, 90, 44, 86, 86, 46, 20, 91, 89, 86, 47, 8, 45, 56};

        log.raw(Plot.BarGraph.box()
                .title("Visitor's Age Survey")
                .XLabel(0, age.length, i -> (age[i] > 18 ? "Adult " : "Minor ") + age[i])
                .YLabel(0, 10, i -> i * 15)
                .scale(0.08)
                .values(age)
                .create()
        );
    }

    @Test
    public void SimpleTest2() {
        FLog log = FLog.getNew();

        int[] age = new int[]{6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94, 4, 90, 44, 86, 86, 46, 20, 91, 89, 86, 47, 8, 45, 56};

        log.raw(Plot.BarGraph.box()
                .values(age)
                .charHeight(15)
                .setBarType(Box.B_T)
                .create()
        );
    }

    @Test
    public void SimpleTest3() {
        FLog log = FLog.getNew();

        int[] age = new int[]{6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94};//,6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94,6, 47, 74, 70, 42, 22, 11, 30, 18, 32, 94};

        log.raw(Plot.BarGraph.box()
//                .title("Yo")
                        .values(age)
                        .setBarType("Hello")
                        .create()
        );
    }

    @Test
    public void simple() {
        FLog log = FLog.getNew();

        log.raw(Plot.BarGraph.simple()
                        .title("Simple Test")
//                        .XLabel(0, 20, i -> "Title Name " + i)
                        .values(0, 20, i -> (int) (100 * Math.random()))
//                .XLabel("1","2","3","4","5","6","4","5","6","4","5","6")
//                .bar(10, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60)
                        .charHeight(13)
                        .setBarType(Box.B_T)
                        .create()
        );
    }

    @Test
    public void box() {
        FLog log = FLog.getNew();

        log.raw(Plot.BarGraph.box()
                        .title("Simple Test")
//                .XLabel(0, 20, i -> "Title Name " + i)
                        .YLabel(0, 4, i -> "M.Height " + i)
                        .values(0, 20, i -> (int) (100 * Math.random()))
//                .setBarType("(*)")
                        .create()
        );
    }


    @Test
    public void image() throws IOException {

        BufferedImage i = ImageIO.read(new File("src/test/resources/NICE.png"));

        FLog log = FLog.getNew();
        ConsoleDecoration.setColorMode(ConsoleDecoration.TRUE_COLOR_BIT_24);


        log.raw(Plot.image(i.getWidth(), i.getHeight(), i.getWidth()/2, i.getHeight()/2,
                (x, y) -> (i.getRGB(x, y) >> 16) & 0xFF,
                (x, y) -> (i.getRGB(x, y) >> 8) & 0xFF,
                (x, y) -> i.getRGB(x, y) & 0xFF));

    }
}