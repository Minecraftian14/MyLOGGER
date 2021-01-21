package com.mcxiv.logger.formatted;

import com.mcxiv.logger.Log;
import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.tools.LogLevel;
import org.junit.Test;

public class Logger_LevelDependencyAdderTest {

    public void print(FLog log) {
        log.vital().prt("Vital", "Hello", "World\n");
        log.error().prt("Error", "Hello", "World\n");
        log.warn().prt("Warn", "Hello", "World\n");
        log.notice().prt("Notice", "Hello", "World\n");
        log.debug().prt("Debug", "Hello", "World\n");
        log.general().prt("General", "Hello", "World\n");
        log.prt("\n");
    }

    @Test
    public void Test() {
        FLog log = FLog.getNew();

        print(log);

        LogLevel.setLevel(LogLevel.WARN);
        print(log);

        LogLevel.NOTICE.activate();
        print(log);

    }

    @Test
    @Format({"\n:: :@ff4$Bbu: ::", ":: :@ff9#FF1493b%-18s: ::", ":: :@ffd#82En%-27s: ::", "::    :~@e#4B0082%-47s: ::\n"})
    public void test_NICEFormatting() {
        FLog log = FLog.getNew();

        log.general().prt("1", "Useless Sentences", "Some boring text ahead...",
                "So here we have some totally boring text just",
                "lying around here for you to read. Though feel",
                "totally comfortable if you wish to skip ahead."
        );

        LogLevel.NOTICE.activate();

        log.notice().prt("2", "Senseless Art", "Valuable Shit",
                "The main theory behind Senseless Art is the",
                "ability to use simple sentences to create a",
                "feeling of improtance and value describing",
                "things which can't even be compared to shit.",
                "If one has such wonderful creativity and a",
                "sense of how to use such to your advantage,",
                "consider, the person a master of this uniquely",
                "special art form."
        );
    }

}