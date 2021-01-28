package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.formatted.FLog;

import java.util.ArrayList;

public class ULogBuilder {

    ULogBuilder() {
    }

    private ArrayList<FLog> loggersList = new ArrayList<>();

    public ULogBuilder add(FLog log) {
        loggersList.add(log);
        return this;
    }

    public FLog create() {
        FLog[] loggers = new FLog[loggersList.size()];
        for (int i = 0; i < loggers.length; i++) loggers[i] = loggersList.get(i);
        return new Logger_MethodImplierBody(loggers);
    }

}
