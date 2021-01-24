package com.mcxiv.logger.plotting;

import com.mcxiv.logger.decorations.ConsoleDecoration;
import com.mcxiv.logger.formatted.FLog;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//                .YLabel(0, 5, i -> ""+i)
public class PlotTest {

    @Test
    public void simple() {
        FLog log = FLog.getNew();

        log.raw(Plot.BarGraph.simple()
                        .title("Simple Test")
//                        .XLabel(0, 20, i -> "Title Name " + i)
                        .bar(0, 20, i -> (int) (100 * Math.random()))
//                .XLabel("1","2","3","4","5","6","4","5","6","4","5","6")
//                .bar(10, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60, 20, 70, 90, 50, 60)
                        .charHeight(13)
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
                .bar(0, 20, i -> (int) (100 * Math.random()))
//                .setBarType("(*)")
                .create()
        );
    }


    @Test
    public void image() throws IOException {

        BufferedImage i = ImageIO.read(new File("src/test/resources/NICE.png"));

        FLog log = FLog.getNew();
        ConsoleDecoration.setColorMode(ConsoleDecoration.TRUE_COLOR_BIT_24);

        log.raw(Plot.image(i.getWidth(), i.getHeight(), (x, y) -> new Plot.ColorBox() {
            final Color c = new Color(i.getRGB(x, y));

            @Override
            public int R() {
                return c.getRed();
            }

            @Override
            public int G() {
                return c.getGreen();
            }

            @Override
            public int B() {
                return c.getBlue();
            }
        }));

    }
}