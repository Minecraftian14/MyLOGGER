package com.mcxiv.logger.formatted;


import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.decorations.TagDecoration;
import com.mcxiv.logger.tools.C;
import org.junit.Test;

public class Logger_AnnotationCompilerTest {

    @Test
    @Format(":#FF69B4:")
    public void test_SimpleFormatting() {
        FLog log = FLog.getNew();
        log.prt("This color is pink");
    }

    @Test
    @Format(":: :#F6BT:")
    public void test_FormattingWithDefaultTime() {
        FLog log = FLog.getNew();
        log.prt("Some Task Report!");
    }

    @Test
    @Format({"::  :@e%-5s:", ":< ss;SSS >:", ":: :@e%-7s:", ":: :%4s:  ::", ":@e%5sn: ::"})
    public void test_FormattingWithCustomTime() throws InterruptedException {
        FLog log = FLog.getNew();

        log.prtf(":: :@cb: ::", "::  :@db:  ::", ":: :@cb: ::", ":: :@db: ::", ":: :@cbn: ::")
                .consume("S.No.", "Time", "Status", "Reach", "Vard");

        for (int i = 0; i < 10; i++) {
            long tn = System.currentTimeMillis();

            boolean fact = Math.random() > 0.5;
            Thread.sleep(1000 + (fact ? 1 : -1));

            tn = System.currentTimeMillis() - tn;

            log.prt(i, "", (fact?C.G:C.R)+(tn > 0 && fact), tn, tn-100);
        }

    }

    @Test
    @Format({":Tn:", ":<hh;mm>n:", ":< mm;ss >n:"})
    public void timeFormats() {
        FLog log = FLog.getNew();

        log.prt("");
        log.prt("","");
        log.prt("","","");


    }

    @Test
    @Format({":#FF1493b: ::", "::- :#82En:", "::  :$M:"})
    public void test_ABitComplicatedFormatting() {
        FLog log = FLog.getNew();
        log.prt("Text Space One", "some more text", "And well, even more text...");
    }

    @Test
    @Format({"\n:: :@ff4$Bbu: ::", ":: :@ff9#FF1493b%-18s: ::", ":: :@ffd#82En%-27s: ::", "::    :~@e#4B0082%-47s: ::\n"})
    public void test_NICEFormatting() {

        FLog log = FLog.getNew();

        log.prt("1", "Useless Sentences", "Some boring text ahead...",
                "So here we have some totally boring text just",
                "lying around here for you to read. Though feel",
                "totally comfortable if you wish to skip ahead."
        );

        log.prt("2", "Senseless Art", "Valuable Shit",
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
        FLog log = new Logger_AnnotationCompiler();
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }

    @Test
    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:"})
    public void test2() {
        FLog log = new Logger_AnnotationCompiler();
        Decoration.setDecoration(TagDecoration::new);
//        Decoration.setDecoration(TagDecoration.class);
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }

    @Test
    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:"})
    public void testCustomFormat() {
        FLog log = new Logger_AnnotationCompiler();
        log.prtf(":$B:", ":: :$GBG$R: ::", ":#ff00ff:").consume("Hey!", "So", "How", "Do", "You", "Do", "?");
        log.prt();
    }
}