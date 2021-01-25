package com.mcxiv.logger.processor;

enum Files {

    ALOG("ALog", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.formatted.FLog;\n" +
            "import com.mcxiv.logger.util.ByteConsumer;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "import java.io.OutputStream;\n" +
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
            "}\n"),
    LOGGER_ANNOTATION_RETRIEVER("Logger_AnnotationRetriever", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.decorations.Decoration;\n" +
            "import com.mcxiv.logger.util.ByteConsumer;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "import java.io.OutputStream;\n" +
            "import java.util.HashMap;\n" +
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
            "    static HashMap<String, Decoration> decorations = new HashMap<>();\n" +
            "\n" +
            "    private static Decoration getDecoration() {\n" +
            "\n" +
            "        StackTraceElement element = Thread.currentThread().getStackTrace()[3];\n" +
            "\n" +
            "        String key = element.getClassName()+\":\" + element.getMethodName();\n" +
            "\n" +
            "        Decoration decoration = ProcessedDecorations.getFor(key);\n" +
            "\n" +
            "        if (decoration!=null) return decoration;\n" +
            "\n" +
            "        decoration = Decoration.getRandomDecoration();\n" +
            "        ProcessedDecorations.putNew(key, decoration);\n" +
            "\n" +
            "        return decoration;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void prt(String... msg) {\n" +
            "        Decoration decoration = getDecoration();\n" +
            "        writer.consume(decoration.decorate(msg));\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void prt(Object... obj) {\n" +
            "        Decoration decoration = getDecoration();\n" +
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
            "        Decoration decoration = Decoration.getDecoration(format);\n" +
            "        return msg -> writer.consume(decoration.decorate(msg));\n" +
            "    }\n" +
            "}\n"),
    LOGGER_LEVEL_DEPENDENCY_ADDER("Logger_LevelDependencyAdder", "" +
            "package com.mcxiv.logger.processor;\n" +
            "\n" +
            "import com.mcxiv.logger.formatted.FLog;\n" +
            "import com.mcxiv.logger.util.StringsConsumer;\n" +
            "\n" +
            "abstract class Logger_LevelDependencyAdder extends ALog {\n" +
            "\n" +
            "    public Logger_LevelDependencyAdder() {\n" +
            "        super();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public FLog provide() {\n" +
            "        return this;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public FLog provideEmpty() {\n" +
            "        return EMPTY_VESSEL;\n" +
            "    }\n" +
            "\n" +
            "    private static final FLog EMPTY_VESSEL = new FLog() {\n" +
            "        @Override\n" +
            "        public FLog provide() {\n" +
            "            return null;\n" +
            "        }\n" +
            "        @Override\n" +
            "        public FLog provideEmpty() {\n" +
            "            return null;\n" +
            "        }\n" +
            "        @Override\n" +
            "        public void prt(String... msg) {\n" +
            "        }\n" +
            "        @Override\n" +
            "        public void prt(Object... obj) {\n" +
            "        }\n" +
            "        @Override\n" +
            "        public void raw(String raw) {\n" +
            "        }\n" +
            "        @Override\n" +
            "        public StringsConsumer prtf(String... format) {\n" +
            "            return null;\n" +
            "        }\n" +
            "    };\n" +
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
