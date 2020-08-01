package main.decoratedLogger.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import static main.utlities.ConsoleColors.ANSI.map;

public class Log {

    static HashMap<String, HashSet<String>> class_to_method = new HashMap<>();
    static HashMap<String, ArrayList<String>> method_to_log = new HashMap<>();

    static HashMap<String, Decoration> decorations_map = new HashMap<>();

    public static void print_with(String reference, String... message_array) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String method_name = extractLastKey(element.getMethodName());
        String class_name = extractLastKey(element.getClassName());
        if (reference.equals("")) reference = method_name;

        method_to_log.putIfAbsent(method_name, new ArrayList<>());
        decorations_map.putIfAbsent(reference, Decoration.getRandomDecoration());
        method_to_log.get(method_name).add(decorations_map.get(reference).decorate_web(replaceTabs(message_array)));
        System.out.print(decorations_map.get(reference).decorate_console(message_array));

        class_to_method.putIfAbsent(class_name, new HashSet<>());
        class_to_method.get(class_name).add(method_name);
    }

    public static void print(String... message_array) {
        StackTraceElement element = Thread.currentThread().getStackTrace()[2];
        String method_name = extractLastKey(element.getMethodName());
        String class_name = extractLastKey(element.getClassName());

        method_to_log.putIfAbsent(method_name, new ArrayList<>());
        decorations_map.putIfAbsent(method_name, Decoration.getRandomDecoration());
        method_to_log.get(method_name).add(decorations_map.get(method_name).decorate_web(replaceTabs(message_array)));
        System.out.print(decorations_map.get(method_name).decorate_console(message_array));

        class_to_method.putIfAbsent(class_name, new HashSet<>());
        class_to_method.get(class_name).add(method_name);
    }

    private static String[] replaceTabs(String[] messages) {
        String[] out = messages.clone();
        for (int i = 0; i < messages.length; i++)
            out[i] = out[i].replaceAll("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");

        return out;
    }

    public static void put(String name, Decoration decoration) {
        decorations_map.put(name, decoration);
    }

    public static void writeToDisk(String path) {
        if (path.equals("")) path = System.getProperty("user.dir") + File.separator + "logs_" + new Date().getTime();
        new File(path).mkdirs();

        try {
            File file = new File(path + File.separator + "class_to_method.data");
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(class_to_method);
            oos.close();
            fos.close();

            File file2 = new File(path + File.separator + "method_to_log.data");
            file2.createNewFile();
            fos = new FileOutputStream(file2);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(method_to_log);
            System.out.println(map.get("BB") + "Written files to: " + map.get("u") + path + map.get("x"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String extractLastKey(String input) {
        String out = input;
        if (input.contains(".")) {
            out = new StringBuffer(input).reverse().toString();
            out = new StringBuffer(out.substring(0, out.indexOf("."))).reverse().toString();
        }
        if (input.contains("<"))
            out = out.substring(out.indexOf("<") + 1);
        if (out.contains(">"))
            out = out.substring(0, out.indexOf(">"));
        if (out.matches("lambda\\$(.*)"))
            out = out.substring(out.indexOf("$") + 1);
        if (out.matches("(.*)\\$(\\d+)"))
            out = out.substring(0, out.indexOf("$"));
        while (out.contains("$")) {
//            System.out.println(out);
            out = out.substring(0, out.indexOf("$")) + "_" + out.substring(out.indexOf("$") + 1);
        }
//        System.out.println(out + "\n");
        return out;
    }

}
