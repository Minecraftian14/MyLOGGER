package com.mcxiv.logger.processor;

import com.google.auto.service.AutoService;
import com.mcxiv.logger.decorations.Format;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("com.mcxiv.logger.decorations.Format")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class AnnotationProcessor extends AbstractProcessor {

    private static boolean ranOnce = false;

    private static final String CLASS_DATA_START = "" +
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
            "    public ALog() {\n" +
            "        setDecorationType(Decorations.CONSOLE);\n" +
            "    }\n" +
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
            "    {\n";
    private static final String CLASS_DATA_CLOSE = "" +
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
            "}\n";

    private static final String MAP_DATA_FORM = "        map.put(\"%s\", new String[]{%s});\n";

    StringBuilder builder;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        if(ranOnce) return;
        super.init(processingEnv);
        builder = new StringBuilder(CLASS_DATA_START);
//        Runtime.getRuntime().addShutdownHook(new Thread(this::flush));

        write(Files.LOGGER_STREAM_DEPENDENCY_ADDER.name, Files.LOGGER_STREAM_DEPENDENCY_ADDER.val);
        write(Files.LOGGER_LEVEL_DEPENDENCY_ADDER.name, Files.LOGGER_LEVEL_DEPENDENCY_ADDER.val);
        write(Files.LOGGER_ANNOTATION_RETRIEVER.name, Files.LOGGER_ANNOTATION_RETRIEVER.val);
    }

    private void write(String name, String data) {
        try {
            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile("com.mcxiv.logger.processor." + name);
            Writer writer = javaFileObject.openWriter();
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if(ranOnce) return false;

        Collection<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(Format.class);

        List<TypeElement> types = ElementFilter.typesIn(annotatedElements);
        List<ExecutableElement> meths = ElementFilter.methodsIn(annotatedElements);
        meths.addAll(ElementFilter.constructorsIn(annotatedElements));

        for (TypeElement type : types) {
//            String pack = ((PackageElement) type.getEnclosingElement()).getQualifiedName().toString();
            String pack = type.getQualifiedName().toString();
            if (pack != null) add(pack, type.getAnnotation(Format.class).value());
        }

        for (ExecutableElement executable : meths) {
            String type_name = ((TypeElement) executable.getEnclosingElement()).getQualifiedName().toString();
            String method_name = executable.getSimpleName().toString();//type.getSimpleName().toString();//getQualifiedName().toString();
            if (type_name != null && method_name != null)
                add(type_name + ":" + method_name, executable.getAnnotation(Format.class).value());
        }

        builder.append(CLASS_DATA_CLOSE);

        flush();
        ranOnce = true;
        return true;
    }

    private void add(String pack, String[] value) {
        builder.append(String.format(MAP_DATA_FORM, pack, toString(value)));
    }

    private static String toString(String[] value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length - 1; i++) builder.append('"').append(value[i]).append("\", ");
        return builder.append('"').append(value[value.length - 1]).append('"').toString();
    }

    private void flush() {
        try {

            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile("com.mcxiv.logger.processor.ALog");
            Writer writer = javaFileObject.openWriter();
            writer.write(builder.toString());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

    /*
package com.mcxiv.logger.processor;

import com.mcxiv.logger.decorations.Decoration;

import java.util.HashMap;

public class ProcessedDecorations {

    private static HashMap<String, Decoration> map = new HashMap<>();

    static {
        map.put("com.tst.pack.Main:main(java.lang.String[])", Decoration.getDecoration(":$B:"));

    }

    public static Decoration getFor(String nm) {
        return map.get(nm);
    }

    public static void putNew(String nm, Decoration decoration) {
        map.put(nm,decoration);
    }

}
    */