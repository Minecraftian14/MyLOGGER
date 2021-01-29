package com.mcxiv.logger.processor;

enum Files {

    ALOG("ALog", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.decorations.Decorations;\n" +
            "import com.mcxiv.logger.formatted.FLog;\n" +
            "import com.mcxiv.logger.util.ByteConsumer;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "import java.io.OutputStream;\n" +
            "import java.util.HashMap;\n" +
            "\n" +
            "public abstract class ALog extends FLog {\n" +
            "\n" +
            "    public static FLog getNew() {\n" +
            "        return new Logger_AnnotationRetriever();\n" +
            "    }\n" +
            "\n" +
            "    public static FLog getNew(OutputStream stream) {\n" +
            "        return new Logger_AnnotationRetriever(stream);\n" +
            "    }\n" +
            "\n" +
            "    public static FLog getNew(ByteConsumer consumer) {\n" +
            "        return new Logger_AnnotationRetriever(consumer);\n" +
            "    }\n" +
            "\n" +
            "    public static FLog getNew(StringsConsumer consumer) {\n" +
            "        return new Logger_AnnotationRetriever(consumer);\n" +
            "    }\n" +
            "\n" +
            "    static HashMap<String, String[]> map = new HashMap<>();\n" +
            "\n" +
            "    {\n" +
            "        map.put(\"classAdress\", new String[]{\"format\"});\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void setDecorationType(String name) {\n" +
            "        super.setDecorationType(name);\n" +
            "        map.forEach((k, f) ->\n" +
            "                Decorations.put(\n" +
            "                        new Decorations.Tag(\n" +
            "                                k.substring(0, k.indexOf(\":\")),\n" +
            "                                k.substring(k.indexOf(\":\") + 1),\n" +
            "                                getDecorationType()\n" +
            "                        ),\n" +
            "                        Decorations.getSpecific(null, getDecorationType(), f)\n" +
            "                )\n" +
            "        );\n" +
            "    }\n" +
            "}\n"),
    LOGGER_ANNOTATION_RETRIEVER("Logger_AnnotationRetriever", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.decorations.Decoration;\n" +
            "import com.mcxiv.logger.decorations.Decorations;\n" +
            "import com.mcxiv.logger.packets.Packet;\n" +
            "import com.mcxiv.logger.util.ByteConsumer;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "import java.io.OutputStream;\n" +
            "\n" +
            "class Logger_AnnotationRetriever extends Logger_StreamDependencyAdder {\n" +
            "\n" +
            "    public Logger_AnnotationRetriever(OutputStream stream) {\n" +
            "        super(stream);\n" +
            "    }\n" +
            "\n" +
            "    public Logger_AnnotationRetriever() {\n" +
            "        super();\n" +
            "    }\n" +
            "\n" +
            "    public Logger_AnnotationRetriever(ByteConsumer consumer) {\n" +
            "        super(consumer);\n" +
            "    }\n" +
            "\n" +
            "    public Logger_AnnotationRetriever(StringsConsumer consumer) {\n" +
            "        super(consumer);\n" +
            "    }\n" +
            "\n" +
            "    \n" +
            "    \n" +
            "    @Override\n" +
            "    public void prt(String... msg) {\n" +
            "        Decoration decoration = Decorations.get(decorator_name);\n" +
            "        writer.consume(decoration.decorate(msg));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void prt(Object... obj) {\n" +
            "        Decoration decoration = Decorations.get(decorator_name);\n" +
            "        String[] stf = new String[obj.length];\n" +
            "        for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();\n" +
            "        writer.consume(decoration.decorate(stf));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void raw(String raw) {\n" +
            "        writer.consume(raw);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public StringsConsumer prtf(String... format) {\n" +
            "        Decoration decoration = Decorations.get(decorator_name);\n" +
            "        return msg -> writer.consume(decoration.decorate(msg));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public Packet newPacket() {\n" +
            "        return new OurPacket();\n" +
            "    }\n" +
            "\n" +
            "    private class OurPacket extends Packet {\n" +
            "\n" +
            "        @Override\n" +
            "        public void prt(String... msg) {\n" +
            "            Decoration decoration = Decorations.get(Decorations.CONSOLE);\n" +
            "            builder.append(decoration.decorate(msg));\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void prt(Object... obj) {\n" +
            "            Decoration decoration = Decorations.get(Decorations.CONSOLE);\n" +
            "            String[] stf = new String[obj.length];\n" +
            "            for (int i = 0; i < stf.length; i++) stf[i] = obj[i].toString();\n" +
            "            builder.append(decoration.decorate(stf));\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void raw(String raw) {\n" +
            "            prtf(\"\").consume(raw);\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public StringsConsumer prtf(String... format) {\n" +
            "            Decoration decoration = Decorations.getSpecific(null, Decorations.CONSOLE, format);\n" +
            "            return msg -> builder.append(decoration.decorate(msg));\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void consume() {\n" +
            "            Logger_AnnotationRetriever.this.raw(builder.toString());\n" +
            "//            builder.setLength(0);\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "}\n"),
    LOGGER_LEVEL_DEPENDENCY_ADDER("Logger_LevelDependencyAdder", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.packets.LambdaPacket;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "abstract class Logger_LevelDependencyAdder extends ALog {\n" +
            "\n" +
            "    public Logger_LevelDependencyAdder() {\n" +
            "        super();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public LambdaPacket provide() {\n" +
            "        return packet;\n" +
            "    }\n" +
            "\n" +
            "    protected LambdaPacket packet = new LambdaPacket() {\n" +
            "        @Override\n" +
            "        public void prt(StringsSupplier supplier) {\n" +
            "            Logger_LevelDependencyAdder.this.prt(supplier.get());\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void raw(StringSupplier supplier) {\n" +
            "            Logger_LevelDependencyAdder.this.raw(supplier.get());\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public void prt(ObjectsSupplier supplier) {\n" +
            "            Logger_LevelDependencyAdder.this.prt(supplier.get());\n" +
            "        }\n" +
            "\n" +
            "        @Override\n" +
            "        public StringsConsumer prtf(StringsSupplier supplier) {\n" +
            "            return Logger_LevelDependencyAdder.this.prtf(supplier.get());\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "}\n"),
    LOGGER_STREAM_DEPENDENCY_ADDER("Logger_StreamDependencyAdder", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.util.ByteConsumer;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "import java.io.OutputStream;\n" +
            "import java.io.PrintStream;\n" +
            "\n" +
            "abstract class Logger_StreamDependencyAdder extends Logger_LevelDependencyAdder {\n" +
            "\n" +
            "    protected StringsConsumer writer;\n" +
            "\n" +
            "    public Logger_StreamDependencyAdder(OutputStream stream) {\n" +
            "        super();\n" +
            "        final PrintStream sw = new PrintStream(stream);\n" +
            "        writer = st -> {\n" +
            "            for (String s : st) sw.print(s);\n" +
            "        };\n" +
            "    }\n" +
            "\n" +
            "    public Logger_StreamDependencyAdder() {\n" +
            "        super();\n" +
            "        writer = st -> {\n" +
            "            for (String s : st) System.out.print(s);\n" +
            "        };\n" +
            "    }\n" +
            "\n" +
            "    public Logger_StreamDependencyAdder(ByteConsumer consumer) {\n" +
            "        super();\n" +
            "        writer = sts -> {\n" +
            "            for (String st : sts) for (byte b : st.getBytes()) consumer.consume(b);\n" +
            "        };\n" +
            "    }\n" +
            "\n" +
            "    public Logger_StreamDependencyAdder(StringsConsumer consumer) {\n" +
            "        super();\n" +
            "        writer = consumer;\n" +
            "    }\n" +
            "\n" +
            "}\n");

    String name;
    String val;

    Files(String name, String val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public String getVal() {
        return val;
    }
}
