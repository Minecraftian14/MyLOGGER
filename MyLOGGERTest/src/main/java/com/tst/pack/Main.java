package com.tst.pack;

import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.formatted.FLog;
//import com.mcxiv.logger.processor.ALog;

public class Main {

    @Format(":$B:")
    public static void main(String[] args) {

        FLog log = FLog.getNew();
        log.prt("Yo");

//        log = ALog.getNew();
        log.prt("Yo");


    }

}
