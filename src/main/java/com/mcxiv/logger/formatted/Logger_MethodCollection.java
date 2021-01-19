package com.mcxiv.logger.formatted;

import com.mcxiv.logger.util.StringsConsumer;

interface Logger_MethodCollection {

    void prt(String... msg);

    void prt(Object... obj);

    StringsConsumer prtf(String... format);

}
