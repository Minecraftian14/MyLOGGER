package com.mcxiv.logger.util;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.tools.LogLevel;

public abstract class LevelDependent<T> {

    public abstract T provide();
    public abstract T provideEmpty();
    
    public T vital() {
        return LogLevel.VITAL.accepted() ? provide() : provideEmpty();
    }

    public T error() {
        return LogLevel.ERROR.accepted() ? provide() : provideEmpty();
    }

    public T warn() {
        return LogLevel.WARN.accepted() ? provide() : provideEmpty();
    }

    public T notice() {
        return LogLevel.NOTICE.accepted() ? provide() : provideEmpty();
    }

    public T debug() {
        return LogLevel.DEBUG.accepted() ? provide() : provideEmpty();
    }

    public T general() {
        return LogLevel.ALL.accepted() ? provide() : provideEmpty();
    }
    
}
