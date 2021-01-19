package com.mcxiv.logger.formatted;

import com.mcxiv.logger.tools.LogLevel;
import com.mcxiv.logger.util.ByteConsumer;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.OutputStream;

abstract class Logger_LevelDependencyAdder extends FLog {

    public Logger_LevelDependencyAdder() {
        super();
    }

    @Override
    public FLog vital() {
        return LogLevel.VITAL.accepted() ? this : FLog.getEmptyVessel();
    }

    @Override
    public FLog error() {
        return LogLevel.ERROR.accepted() ? this : FLog.getEmptyVessel();
    }

    @Override
    public FLog warn() {
        return LogLevel.WARN.accepted() ? this : FLog.getEmptyVessel();
    }

    @Override
    public FLog notice() {
        return LogLevel.NOTICE.accepted() ? this : FLog.getEmptyVessel();
    }

    @Override
    public FLog debug() {
        return LogLevel.DEBUG.accepted() ? this : FLog.getEmptyVessel();
    }

    @Override
    public FLog general() {
        return LogLevel.ALL.accepted() ? this : FLog.getEmptyVessel();
    }
}
