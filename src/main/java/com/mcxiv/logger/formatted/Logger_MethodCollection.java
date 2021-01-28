package com.mcxiv.logger.formatted;

import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.util.Printer;
import com.mcxiv.logger.util.StringsConsumer;

interface Logger_MethodCollection extends Printer {

    void setDecorationType(String name);

    String getDecorationType();

    Packet newPacket();

}
