package com.mcxiv.logger.formatted.fixed;

import com.mcxiv.logger.decorations.Decoration;
import com.mcxiv.logger.decorations.Decorations;
import com.mcxiv.logger.decorations.RawDecoration;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.Packet;
import com.mcxiv.logger.tables.Table;
import com.mcxiv.logger.util.StringsConsumer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

class Logger_LogFileWriter extends Logger_LevelDependencyAdder {

    public static int MAX_BUFFER_SIZE = 200;

    FileWriter writer = null;
    StringBuilder buffer;

    public Logger_LogFileWriter() {
        this(LocalDateTime.now().toString().replaceAll("[^a-zA-Z0-9]", ",") + ".txt");
    }

    public Logger_LogFileWriter(String file) {
        this(new File(String.format("logs%s%s", File.separator, file)));
    }

    public Logger_LogFileWriter(File file) {
        File fp = new File("logs");
        if (!fp.exists()) fp.mkdir();

        try {
            writer = new FileWriter(file, true);
            buffer = new StringBuilder(MAX_BUFFER_SIZE);
            Runtime.getRuntime().addShutdownHook(new Thread(this::flush));
        } catch (IOException e) {
//            System.out.println(C.RBG + C.hex.font.to24Bit(255, 255, 255) + "IO Exception " + C.RS + C.Y + e.getCause() + C.RS);
            Table.tabulate(FLog.getNew(), e);
        }
    }

    private void write(String msg) {
        if (writer == null) return;
        if (buffer.length() > MAX_BUFFER_SIZE) flush();
        buffer.append(msg);
    }

    private void flush() {
        if (writer == null) return;
        try {
            writer.append(buffer.toString());
            writer.flush();
        } catch (IOException e) {
//            System.out.println(C.RBG + C.hex.font.to24Bit(255, 255, 255) + "IO Exception " + C.RS + C.Y + e.getCause() + C.RS);
            Table.tabulate(FLog.getNew(), e);
        }
        buffer.setLength(0);
    }

    @Override
    public void prt(String... msg) {
        write(Decorations.get(Decorations.RAW).decorate(msg));
    }

    @Override
    public void prt(Object... obj) {
        String[] stf = new String[obj.length];
        for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
        write(Decorations.get(Decorations.RAW).decorate(stf));
    }

    @Override
    public void raw(String raw) {
        prtf("").consume(raw);
    }

    @Override
    public StringsConsumer prtf(String... format) {
        return msg -> write(new RawDecoration(null, format).decorate(msg));
    }

    @Override
    public Packet newPacket() {
        return new OurPacket();
    }

    private class OurPacket extends Packet {

        @Override
        public void prt(String... msg) {
            Decoration decoration = Decorations.get(Decorations.RAW);
            builder.append(decoration.decorate(msg));
        }

        @Override
        public void prt(Object... obj) {
            Decoration decoration = Decorations.get(Decorations.RAW);
            String[] stf = new String[obj.length];
            for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();
            builder.append(decoration.decorate(stf));
        }

        @Override
        public void raw(String raw) {
            prtf("").consume(raw);
        }

        @Override
        public StringsConsumer prtf(String... format) {
            return msg -> builder.append(new RawDecoration(null,format).decorate(msg));
        }

        @Override
        public void consume() {
            Logger_LogFileWriter.this.raw(builder.toString());
        }
    }

}
