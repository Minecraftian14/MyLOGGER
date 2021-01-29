package com.mcxiv.logger.util;

public interface Printer {

    void prt(String... msg);

    void prt(Object... obj);

    void raw(String raw);

    StringsConsumer prtf(String... format);

}
