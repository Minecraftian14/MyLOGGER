package com.mcxiv.logger.ultimate;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.LambdaPacket;
import com.mcxiv.logger.util.StringsConsumer;

abstract class Logger_LevelDependencyAdder extends FLog {

    public Logger_LevelDependencyAdder() {
        super();
    }

    @Override
    public LambdaPacket provide() {
        return packet;
    }

    protected LambdaPacket packet = new LambdaPacket() {
        @Override
        public void prt(StringsSupplier supplier) {
            Logger_LevelDependencyAdder.this.prt(supplier.get());
        }

        @Override
        public void prt(ObjectsSupplier supplier) {
            Logger_LevelDependencyAdder.this.prt(supplier.get());
        }

        @Override
        public void raw(StringSupplier supplier) {
            Logger_LevelDependencyAdder.this.raw(supplier.get());
        }

        @Override
        public StringsConsumer prtf(StringsSupplier supplier) {
            return Logger_LevelDependencyAdder.this.prtf(supplier.get());
        }
    };
}
