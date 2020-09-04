package main.generalLogger;

import main.utlities.ConsoleColors;

import java.awt.*;

public class LOGGER {

    public static boolean isGeneralAllowed = true;
    public static boolean isErrorAllowed = true;
    public static boolean isInfoAllowed = true;
    public static boolean isNoticeAllowed = true;
    public static boolean isStatusAllowed = true;
    public static boolean isAudioAllowed = false;

    public static String generalColor = ConsoleColors.BLUE_BRIGHT;
    public static String errorColor = ConsoleColors.RED_UNDERLINED;
    public static String infoColor = ConsoleColors.GREEN_BOLD;
    public static String noticeColor = ConsoleColors.YELLOW_UNDERLINED;
    public static String statusColor = ConsoleColors.PURPLE_BOLD_BRIGHT;
    public static String spacer = " ";

    public static void general(String... messages) {
        if (isGeneralAllowed)
            for (String message : messages)
                print(message, generalColor);
        printEnd();
    }

    public static void general(Object... messages) {
        if (isGeneralAllowed)
            for (Object message : messages)
                print(message.toString(), generalColor);
        printEnd();
    }

    public static void error(String... messages) {
        if (isErrorAllowed)
            for (String message : messages)
                print(message, errorColor);
        printEnd();
        beep();
    }

    public static void error(Object... messages) {
        if (isErrorAllowed)
            for (Object message : messages)
                print(message.toString(), errorColor);
        printEnd();
        beep();
    }

    public static void info(String... messages) {
        if (isInfoAllowed)
            for (String message : messages)
                print(message, infoColor);
        printEnd();
    }

    public static void info(Object... messages) {
        if (isInfoAllowed)
            for (Object message : messages)
                print(message.toString(), infoColor);
        printEnd();
    }

    public static void notice(String... messages) {
        if (isNoticeAllowed)
            for (String message : messages)
                print(message, noticeColor);
        printEnd();
    }

    public static void notice(Object... messages) {
        if (isNoticeAllowed)
            for (Object message : messages)
                print(message.toString(), noticeColor);
        printEnd();
    }

    public static int status(int numlen, String... messages) {
        StringBuilder buff = new StringBuilder();
        if (isStatusAllowed)
            for (String message : messages)
                buff.append(message);
        print(buff.toString(), statusColor, numlen);
        return buff.length();
    }

    public static int status(int numlen, Object... messages) {
        StringBuilder buff = new StringBuilder();
        if (isStatusAllowed)
            for (Object message : messages)
                buff.append(message.toString());
        print(buff.toString(), statusColor, numlen);
        return buff.length();
    }

    public static void beep() {
        if (isAudioAllowed)
            Toolkit.getDefaultToolkit().beep();
    }

    public static void print(String message, String color) {
        System.out.print(color + message + ConsoleColors.RESET + spacer);
    }

    public static void printEnd() {
        System.out.println();
        System.out.flush();
    }

    public static void print(String message, String color, int numlen) {
        for (int i = 0; i < numlen; i++)
            System.out.print('\b');
        System.out.print(color + message + ConsoleColors.RESET);
        System.out.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        isAudioAllowed = true;
        general("Hello There, Welcome To My LOGGER!");
        error("Hello There, Welcome To My LOGGER!");
        info("Hello There, Welcome To My LOGGER!");
        notice("Hello There, Welcome To My LOGGER!");
        status(100, "Hello");
        Thread.sleep(1000);
        status(100, "World");
        // beep();
    }

}
