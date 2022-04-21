package com.mcxiv.logger.multithreadded;

import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.formatted.fixed.FileLog;
import com.mcxiv.logger.tools.LogLevel;
import com.mcxiv.logger.ultimate.ULog;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LateLogTest {

    @Test
    @Format({":$Bt: ::", ":$Ytt n:"})
    public void test() throws InterruptedException {

        FLog log = LateLog.getNew(
                Executors.newFixedThreadPool(5),
                ULog.forNew()
                        .add(FLog.getNew())
                        .add(FileLog.getNew("new.txt"))
                        .create()
        );

//        FLog log = LateLog.getNew(FLog.getNew());

        for (int i = 0; i < 100; i++) {
            log.prt("Some Index xD", i);
        }

        Thread.sleep(1000);
    }
}