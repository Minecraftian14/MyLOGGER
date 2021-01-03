package com.mcxiv.logger;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.ByteConsumer;

import java.io.OutputStream;

public class LogBuilder {

    ByteConsumer consumer = null;
    OutputStream stream = null;

    public LogBuilder() {
    }

    public LogBuilder(ByteConsumer consumer) {
        this.consumer = consumer;
    }

    public LogBuilder(OutputStream stream) {
        this.stream = stream;
    }

    public Log getDILogger() {
        if(consumer != null) return new Logger_AssembliedUniqueMethodAdaptations(consumer);
        if(stream != null) return new Logger_AssembliedUniqueMethodAdaptations(stream);
        return new Logger_AssembliedUniqueMethodAdaptations();
    }

    public Log getHeadedDILogger() {
        if(consumer != null) return new Logger_HeadedAssembliedUniqueMethodAdaptations(consumer);
        if(stream != null) return new Logger_HeadedAssembliedUniqueMethodAdaptations(stream);
        return new Logger_HeadedAssembliedUniqueMethodAdaptations();
    }

    public Log getHeadedLogger() {
        if(consumer != null) return new Logger_DoubleFormattedUniqueMethodAdaptations(consumer);
        if(stream != null) return new Logger_DoubleFormattedUniqueMethodAdaptations(stream);
        return new Logger_DoubleFormattedUniqueMethodAdaptations();
    }

    public Log getSimpleLogger() {
        if(consumer != null) return new Logger_FormattedUniqueMethodAdaptations(consumer);
        if(stream != null) return new Logger_FormattedUniqueMethodAdaptations(stream);
        return new Logger_FormattedUniqueMethodAdaptations();
    }

    public FLog getFormattedLogger() {
        if(consumer != null) return FLog.getNew(consumer);
        if(stream != null) return FLog.getNew(stream);
        return FLog.getNew();
    }

}
