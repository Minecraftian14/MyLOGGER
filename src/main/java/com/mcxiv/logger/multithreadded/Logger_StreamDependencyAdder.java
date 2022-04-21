package com.mcxiv.logger.multithreadded;

import com.mcxiv.logger.formatted.FLog;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class Logger_StreamDependencyAdder extends Logger_LevelDependencyAdder {

    ExecutorService executorService;
    FLog logger;

    public Logger_StreamDependencyAdder(FLog logger) {
        this(Executors.newSingleThreadExecutor(), logger);
    }

    public Logger_StreamDependencyAdder(ExecutorService executorService, FLog logger) {
        this.executorService = executorService;
        this.logger = logger;
    }

}
