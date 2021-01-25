package com.mcxiv.logger.processor;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.StringsConsumer;

abstract class Logger_LevelDependencyAdder extends ALog {

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

    private static final FLog EMPTY_VESSEL = new FLog() {
        @Override
        public FLog provide() {
            return null;
        }
        @Override
        public FLog provideEmpty() {
            return null;
        }
        @Override
        public void prt(String... msg) {
        }
        @Override
        public void prt(Object... obj) {
        }
        @Override
        public void raw(String raw) {
        }
        @Override
        public StringsConsumer prtf(String... format) {
            return null;
        }
    };
}
