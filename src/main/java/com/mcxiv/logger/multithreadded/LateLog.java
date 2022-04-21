package com.mcxiv.logger.multithreadded;

import com.mcxiv.logger.formatted.FLog;

import java.util.concurrent.ExecutorService;

public abstract class LateLog extends FLog {

    public static FLog getNew(FLog base) {
        return new Logger_MethodImplierBody(base);
    }

    public static FLog getNew(ExecutorService executorService, FLog logger) {
        return new Logger_MethodImplierBody(executorService, logger);
    }
}
