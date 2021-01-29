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

   public void act(Runnable runnable) {
        if(accepted()) runnable.run();
   }

}
