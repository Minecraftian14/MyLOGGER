package main.htmlfileLogger.log;

import main.generalLogger.LOGGER;
import main.utlities.Format;
import main.utlities.Html;
import main.htmlfileLogger.web.GenCSS;
import main.htmlfileLogger.web.GenHTML;
import main.htmlfileLogger.web.GenTabsJS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FileLogger {

    static HashMap<String, LogProperties> properties = new HashMap<>();

    static HashMap<String, String> log_history = new HashMap<>();

    public static void put(String key, Format live_log, Format web_log) {
        LogProperties lp = new LogProperties();
        lp.live_log = live_log;
        lp.web_log = web_log;
        properties.put(key, lp);
    }

    public static void print(String... messages) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String methodName = extractLastKey(ste.getMethodName());

        if (properties.containsKey(methodName)) {
            LogProperties lp = properties.get(methodName);

            System.out.print(lp.live_log.format(messages));

            String msg = lp.web_log.format(messages);
            if (lp.reference == null)
                lp.reference = ste;
            if (log_history.containsKey(methodName))
                msg = log_history.get(methodName) + msg;
            log_history.put(methodName, msg);
        }
        properties.getOrDefault(methodName, new LogProperties());
    }

    public static void println(String... messages) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[2];
        String methodName = extractLastKey(ste.getMethodName());
//        System.out.println(ste.getMethodName() + " > " + methodName);

        if (properties.containsKey(methodName)) {
            LogProperties lp = properties.get(methodName);

            System.out.print(lp.live_log.format(messages.clone()));
            System.out.println();

            if (messages.length != 0) messages[messages.length - 1] += "<br />";
            else messages = new String[]{"<br />"};

            String msg = lp.web_log.format(messages);
            if (lp.reference == null)
                lp.reference = ste;
            if (log_history.containsKey(methodName))
                msg = log_history.get(methodName) + msg;
            log_history.put(methodName, msg);
        }
        properties.getOrDefault(methodName, new LogProperties());
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

    public static void writeToDisk(String path) {
        if (path.equals("")) path = System.getProperty("user.dir") + "\\Html_Test";
        new File(path).mkdirs();

        HashMap<String, ArrayList<String>> class_method_map = new HashMap<>();
        properties.forEach((s, l) -> {
            if (l.reference == null) LOGGER.error("Null reference for ", s);
            else {
                class_method_map.putIfAbsent(extractLastKey(l.reference.getClassName()), new ArrayList<>());
                class_method_map.get(extractLastKey(l.reference.getClassName())).add(s);
            }
        });

        int no_of_tabs = class_method_map.size();
        int[] no_of_sub_tabs = new int[no_of_tabs];

        for (int i = 0; i < no_of_tabs; i++)
            no_of_sub_tabs[i] = new ArrayList<>(class_method_map.values()).get(i).size();

        String[] classes = class_method_map.keySet().toArray(new String[0]);

        ArrayList<String[]> mets_und_cls = new ArrayList<>();
        class_method_map.forEach((c, m) -> mets_und_cls.add(m.toArray(new String[0])));

        ArrayList<String[]> contents = new ArrayList<>();

        for (int i = 0; i < mets_und_cls.size(); i++) {
            String[] slice = mets_und_cls.get(i);
            String[] copy = new String[slice.length];
            for (int j = 0; j < slice.length; j++) copy[j] = log_history.get(slice[j]);
            contents.add(copy);
        }

        String out_html = GenHTML.getIndexForTabs(no_of_tabs, no_of_sub_tabs, classes, mets_und_cls.toArray(new String[0][0]), contents.toArray(new String[0][0]));
        String out_js = GenTabsJS.getTabsJS(no_of_tabs, no_of_sub_tabs, classes);
        String out_css = GenCSS.getStyleForTabs(no_of_tabs, no_of_sub_tabs, classes);

//        System.out.println(out_html);
//        System.out.println(out_js);
//        System.out.println(out_css);

        try {
            new FileOutputStream(new File(path + "\\index.html")).write(out_html.getBytes());
            new FileOutputStream(new File(path + "\\script.js")).write(out_js.getBytes());
            new FileOutputStream(new File(path + "\\style.css")).write(out_css.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class LogProperties {
        StackTraceElement reference = null;
        Format live_log = new Format();
        Format web_log = new Format();
    }

    public static void main(String[] args) {

        put("main", new Format(s -> s + s), new Format(s -> new Html().BOLD(s).toString()));
        put("call", new Format(s -> s + s + s), new Format(s -> new Html().ITAL(s).toString()));
        print("Heldlo");
        print("Hello");
        print("Helvlo");
        print("Helwlo");
        call();
        writeToDisk("");
    }

    public static void call() {
        print("Helqlo");
        print("Heello");
        print("Helrlo");
        print("Hetllo");
    }

}
