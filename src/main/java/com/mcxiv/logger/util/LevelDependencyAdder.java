package com.mcxiv.logger.util;

import com.mcxiv.logger.packets.LambdaPacket;
import com.mcxiv.logger.tools.LogLevel;

public interface LevelDependencyAdder {

    LambdaPacket provide();

    default LambdaPacket provideEmpty() {
        return LambdaPacket.EMPTY_VESSEL;
    }

    default LambdaPacket vital() {
        return LogLevel.VITAL.accepted() ? provide() : provideEmpty();
    }

    default LambdaPacket error() {
        return LogLevel.ERROR.accepted() ? provide() : provideEmpty();
    }

    default LambdaPacket warn() {
        return LogLevel.WARN.accepted() ? provide() : provideEmpty();
    }

    default LambdaPacket notice() {
        return LogLevel.NOTICE.accepted() ? provide() : provideEmpty();
    }

    default LambdaPacket debug() {
        return LogLevel.DEBUG.accepted() ? provide() : provideEmpty();
    }

    default LambdaPacket general() {
        return LogLevel.ALL.accepted() ? provide() : provideEmpty();
    }

}
