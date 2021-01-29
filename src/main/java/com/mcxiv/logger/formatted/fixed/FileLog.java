package com.mcxiv.logger.formatted.fixed;

import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.formatted.FLog;

import java.io.File;

public abstract class FileLog extends FLog {

    public FileLog() {
        decorator_name = Decorations.RAW;
    }

    public static FLog getNew(String file) {
        return new Logger_LogFileWriter(file);
    }

    public static FLog getNew(File file) {
        return new Logger_LogFileWriter(file);
    }

    @Override
    public void setDecorationType(String name) {
    }

}
