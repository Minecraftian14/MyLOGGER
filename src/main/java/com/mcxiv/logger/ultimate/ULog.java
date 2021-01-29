package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.formatted.FLog;

public abstract class ULog extends FLog {

    public static ULogBuilder forNew() {
        return new ULogBuilder();
    }

}
