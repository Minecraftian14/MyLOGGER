package com.mcxiv.logger.plotting;

import com.mcxiv.logger.formatted.FLog;
import org.junit.Test;

//                .YLabel(0, 5, i -> ""+i)
public class PlotTest {

    @Test
    public void simple() {
        FLog log = FLog.getNew();

        log.raw(Plot.BarGraph.simple()
                .title("Simple Test")
                .XLabel("Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6")
                .bar(10, 50, 90, 30, 60, 70)
                .create()
        );
    }
}