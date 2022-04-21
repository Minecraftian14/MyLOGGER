package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.formatted.FLog;

abstract class Logger_StreamDependencyAdder extends Logger_LevelDependencyAdder {

    FLog[] loggers;

    public Logger_StreamDependencyAdder(FLog... loggers) {
        super();
        this.loggers = loggers;
    }

}
