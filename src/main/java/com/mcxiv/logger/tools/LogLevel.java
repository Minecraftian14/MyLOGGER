package com.mcxiv.logger.tools;

public enum LogLevel {
    OFF,
    VITAL,
    ERROR,
    WARN,
    NOTICE,
    DEBUG,
    ALL;

    static LogLevel level = ALL;

    LogLevel() {
    }

    public boolean accepted() {
        return level.ordinal() >= ordinal();
    }

    public static void setLevel(LogLevel lvl) {
        level = lvl;
    }

    public void activate() {
        level = this;
    }

    public static interface LogLevelAdaptor<T> {
        T vital();

        T error();

        T warn();

        T notice();

        T debug();

        T general();
    }

}
