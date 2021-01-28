package com.mcxiv.logger.formatted;

import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.tools.LogLevel;
import org.junit.Test;

public class Logger_LevelDependencyAdderTest {

    @Format({"","",":n:"})
    public void print(FLog log) {
        log.vital().prt(() -> FLog.form("Vital", "Hello", "World"));
        log.error().prt(() -> FLog.form("Error", "Hello", "World"));
        log.warn().prt(() -> FLog.form("Warn", "Hello", "World"));
        log.notice().prt(() -> FLog.form("Notice", "Hello", "World"));
        log.debug().prt(() -> FLog.form("Debug", "Hello", "World"));
        log.general().prt(() -> FLog.form("General", "Hello", "World"));
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

        log.general().prt(() -> FLog.form("1", "Useless Sentences", "Some boring text ahead...",
                "So here we have some totally boring text just",
                "lying around here for you to read. Though feel",
                "totally comfortable if you wish to skip ahead."
        ));

        LogLevel.NOTICE.activate();

        log.notice().prt(() -> FLog.form("2", "Senseless Art", "Valuable Shit",
                "The main theory behind Senseless Art is the",
                "ability to use simple sentences to create a",
                "feeling of improtance and value describing",
                "things which can't even be compared to shit.",
                "If one has such wonderful creativity and a",
                "sense of how to use such to your advantage,",
                "consider, the person a master of this uniquely",
                "special art form."
        ));
    }

}