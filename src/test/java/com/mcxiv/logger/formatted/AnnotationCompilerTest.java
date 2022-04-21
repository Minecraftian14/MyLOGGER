package com.mcxiv.logger.formatted;


import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.formatted.fixed.FileLog;
import com.mcxiv.logger.ultimate.ULog;
import org.junit.Test;

public class AnnotationCompilerTest {

    @Test
    public void demoGenerator() {
        FLog log = FLog.getNew();
        log.prtf("").consume("\n\n\n");
        log.prtf("").consume("No Formatting");
        log.prtf("").consume("\n");
        log.prtf(":: :@d#0a0afabu: ::", "  :: > :@e#0a0%-30s:").consume("Example", "String Formatting");
        log.prtf("").consume("\n\n\n");
    }

    @Test
    @Format(":#FF69B4:")
    public void test_SimpleFormatting() throws InterruptedException {
        FLog log = FLog.getNew();
        log.prt("This color is pink");
    }

    @Test
    @Format(":: :#F6BTn:")
    public void test_FormattingWithDefaultTime() throws InterruptedException {
        FLog log = FLog.getNew();
        log.prt("Some Task Report!");
        Thread.sleep(3000);
        log.prt("This color is pink");
    }

    @Test
    @Format({":<ss;SSS>:", " -:: :n:"})
    public void test_FormattingWithCustomTime() throws InterruptedException {
        FLog log = FLog.getNew();

        log.prt("", "Yo!");
        Thread.sleep(999);
        log.prt("", "Yo!");
        Thread.sleep(999);
        log.prt("", "Yo!");
        Thread.sleep(999);
        log.prt("", "Yo!");
        Thread.sleep(999);

    }

    @Test
    @Format({":Tn:", ":<hh;mm>n:", ":< mm;ss >n:"})
    public void timeFormats() {
        FLog log = FLog.getNew();

        log.prt("");
        log.prt("", "");
        log.prt("", "", "");


    }

    @Test
    @Format({":#FF1493b: ::", "::- :#82En:", "::  :$M:"})
    public void test_ABitComplicatedFormatting() {
        FLog log = FLog.getNew();
        log.prt("Text Space One", "some more text", "And well, even more text...");
    }

    @Test
    @Format({":$B: ::", ":$GR: ::"})
    public void test_Repeator() {
        FLog log = FLog.getNew();
        log.prt("Text Space One", "some more text", "And well", " even more text...");
    }

    @Test
    @Format({"Player:: :PCM $B %7s:ed ::by ", ":$Gn:"})
    public void movement() {
        FLog log = FLog.getNew();

        log.prt("sprint", 45);

        log.prt("shift", 78);

        log.prt("drift", 48);
    }

    @Test
    @Format({"\n:: :@ff4$Bb u: ::", ":: :@ff9#FF1493b%-18s: ::", ":: :@ffd#82En%-27s: ::", "::    :w47w@e#4B0082%-47s: ::"})
    public void test_NICEFormatting() {

        FLog log = FileLog.getNew("html.html");
        log.setDecorationType(Decorations.HTML);

        log = ULog.forNew()
                .add(log)
                .add(FLog.getNew())
                .create();

        log.prt("1", "Useless Sentences", "Some boring text ahead...",
                "So here we have some totally boring text just " +
                "lying around here for you to read. Though feel " +
                "totally comfortable if you wish to skip ahead."
        );

        log.prt("2", "Senseless Art", "Valuable Shit",
                "The main theory behind Senseless Art is the " +
                "ability to use simple sentences to create a " +
                "feeling of improtance and value describing " +
                "things which can't even be compared to shit. " +
                "If one has such wonderful creativity and a " +
                "sense of how to use such to your advantage, " +
                "consider+ the person a master of this uniquely " +
                "special art form."
        );
    }

    @Test
    @Format({"::Par :$Y:", ":$B%10sn: points::"})
    public void test_Formatting() {
        FLog log = FLog.getNew();
        log.prt(1, 1267);
        log.prt(2, 13908);
        log.prt(3, 34);
        log.prt(4, 152576);
    }

    @Test
    @Format(":$B %*50s w49w:")
    public void test_Center() {
        FLog log = FLog.getNew();
        log.prt("The main theory behind Senseless Art is the " +
                "ability to use simple sentences to create a " +
                "feeling of improtance and value describing " +
                "things which can't even be compared to shit. " +
                "If one has such wonderful creativity and a " +
                "sense of how to use such to your advantage, " +
                "consider+ the person a master of this uniquely " +
                "special art form."
        );
    }

    @Test
    @Format("::Just a formatted message :$B:")
    public void test_PreFix() {
        FLog log = FLog.getNew();
        log.prt("A Message Here");
    }

    @Test
    @Format(":$B: Another formatted message::")
    public void test_SufFix() {
        FLog log = FLog.getNew();
        log.prt("A Message Here");
        log.raw("Yo");
        log.raw("Yo");
    }

    @Test
    @Format("Hello There:: :$G:")
    public void test_PrePreFix() {
        FLog log = FLog.getNew();
        log.prt("A Message Here");
    }

    @Test
    @Format(":$G: ::A SufSufFix here...")
    public void test_SufSufFix() {
        FLog log = FLog.getNew();
        log.prt("A Message Here");
    }

    @Test
    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:", ":$R:", ":$BR:"})
    public void test() {
        FLog log = new Logger_MethodImplierBody();
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }

    @Test
    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:"})
    public void test2() {
        FLog log = new Logger_MethodImplierBody();
//        Decoration.setDecoration(TagDecoration::new);
//        Decoration.setDecoration(TagDecoration.class);
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }

    @Test
    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:"})
    public void testCustomFormat() {
        FLog log = new Logger_MethodImplierBody();
        log.prtf(":$B:", ":: :$GBG$R: ::", ":#ff00ff:").consume("Hey!", "So", "How", "Do", "You", "Do", "?");
        log.prt();
    }
}