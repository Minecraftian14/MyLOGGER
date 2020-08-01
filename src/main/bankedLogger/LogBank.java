package main.bankedLogger;

import main.utlities.ConsoleColors;

import java.util.HashMap;

public class LogBank {

    private static HashMap<String, Log_Control> loggers_list = null;
    private static final Log_Control default_log = new Log_Control(ConsoleColors.GREEN, ConsoleColors.BLACK_BACKGROUND);

    public static Log_Control LOG(String Channel) {
        if (loggers_list == null) loggers_list = new HashMap<>();
        return loggers_list.getOrDefault(Channel, default_log);
    }

    public static Log_Control LOG() {
        if (loggers_list == null) loggers_list = new HashMap<>();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; i < stackTrace.length && i < 10; i++)
            if (loggers_list.containsKey(stackTrace[i].getMethodName()))
                return loggers_list.get(stackTrace[i].getMethodName());
        return default_log;
    }

    public static void addNewControl(String Channel, Log_Control control) {
        if (loggers_list == null) loggers_list = new HashMap<>();
        loggers_list.put(Channel, control);
    }

    public static void main(String[] args) {
        meta();
    }

    public static void meta() {
        cet();
    }

    public static void cet() {
        for (StackTraceElement e : Thread.currentThread().getStackTrace()) {
            System.out.println(e);
            System.out.println(e.getMethodName());
            System.out.println(e.getClassName());
            System.out.println(e.getLineNumber());
            System.out.println();
        }
    }

}

