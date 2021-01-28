package com.mcxiv.logger.packets;

import com.mcxiv.logger.util.StringsConsumer;

import java.util.function.Supplier;

public abstract class LambdaPacket {

    public abstract void prt(StringsSupplier supplier);

    public abstract void prt(ObjectsSupplier supplier);

    public abstract void raw(StringSupplier supplier);

    public abstract StringsConsumer prtf(StringsSupplier supplier);

    public interface StringSupplier extends Supplier<String> {

    }

    public interface StringsSupplier extends Supplier<String[]> {

    }

    public interface ObjectsSupplier extends Supplier<Object[]> {

    }


    public static LambdaPacket EMPTY_VESSEL = new LambdaPacket() {
        @Override
        public void prt(StringsSupplier supplier) {

        }

        @Override
        public void prt(ObjectsSupplier supplier) {

        }

        @Override
        public void raw(StringSupplier supplier) {

        }

        @Override
        public StringsConsumer prtf(StringsSupplier supplier) {
            return null;
        }
    };

}
