package com.mcxiv.logger.decorations;

import com.mcxiv.logger.formatted.FLog;
import org.junit.Test;

import java.io.File;

public class RawFileDecorationTest {


    @Test
    @Format({":$B: ::", "::_:$GBG$Rn:", "::   :#ff00ff: ::", ":$R:", ":$BRn:"})
    public void Simple() {
        FLog log = FLog.getNew();
//        Decoration.setDecoration(strings -> new RawFileDecoration(new File("src/test/resources/log.txt"), strings));
        Decoration.setDecoration(RawFileDecoration::new);

        for (int i = 0; i < 16 / 16; i++)
            log.prt("Yo", "Hey", "oof", "Hey", " You!");

    }
}