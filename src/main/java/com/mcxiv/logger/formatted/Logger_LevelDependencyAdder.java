package com.mcxiv.logger.formatted;

import com.mcxiv.logger.util.StringsConsumer;

abstract class Logger_LevelDependencyAdder extends FLog {

    public Logger_LevelDependencyAdder() {
        super();
    }

    @Override
    public FLog provide() {
        return this;
    }

    @Override
    public FLog provideEmpty() {
        return EMPTY_VESSEL;
    }


}
