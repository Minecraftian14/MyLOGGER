package com.mcxiv.logger.formatted;


import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.decorations.TagDecoration;
import com.mcxiv.logger.tools.C;
import org.junit.Test;

public class Logger_AnnotationCompilerTest {



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
        log.setDecorator(TagDecoration::new);
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }

    public static void main(String[] args) {
        new Logger_AnnotationCompilerTest();
    }

    @Format({":$B:", ":: :$GBG$R: ::", ":#ff00ff:"})
    public Logger_AnnotationCompilerTest() {
        FLog log = new Logger_AnnotationCompiler();
//        log.setDecorator(TagDecoration::new);
        log.prt("Hey!", "So", "How", "Do", "You", "Do", "?");
    }
}