package com.mcxiv.logger.tables;

import com.mcxiv.logger.decorations.Format;
import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.GroupIterator;
import com.mcxiv.logger.util.Iterator;

public interface Table {

    static TableAdaptor stripped() {
        return new StripesTable();
    }

    static TableAdaptor box() {
        return new BoxTable();
    }

    static TableAdaptor empty() {
        return new EmptyTable();
    }

    static String[] form(Object... obj) {
        String[] msg = new String[obj.length];
        for (int i = 0; i < obj.length; i++) msg[i] = obj[i].toString();
        return msg;
    }


    static void tabulate(FLog log, Throwable e) {

        Table table = Table.stripped().formatHead("b@ffa", "b@ffc").format("@ffc", "@ffa")
                .head("Package", "Class", "Method", "Line no.");

        for (StackTraceElement ele : e.getStackTrace()) {
            int i = 0;
            for (; i < ele.getClassName().length(); i++)
                if (Character.isUpperCase(ele.getClassName().charAt(i))) break;
            table.row(ele.getClassName().substring(0, i - 1), ele.getClassName().substring(i), ele.getMethodName(), "" + ele.getLineNumber());
        }

        String name = e.getClass().getName();
        name = name.substring(name.lastIndexOf(".") + 1);

        String msg = e.getMessage();
        msg = msg == null ? "" : msg;

        log.prtf(":: :@a00 #F b %-40s: ::",
                ":: :$B @ee0 u n %-" + (table.getWidth() - Math.max(40, name.length())-3) + "s:")
                .consume(name, msg);

        table.create(log);


//        Throwable ourCause = e.getCause();
//        if (ourCause != null)
//            elem += ourCause + "\n";

//        for (Throwable throwable : e.getSuppressed()) {
//
//        }

//        log.prt(name, msg, elem);

    }


    //
    //
    //


    //
    //
    //


    Table title(String title);

    Table head(String... msg);

    Table row(String... msg);

    Table iter(int b, Iterator... its);

    Table iter(int a, int b, Iterator... its);

    Table iter(int a, int b, int c, Iterator... its);

    <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its);

    Table format(String... codes);

    Table formatTitle(String code);

    Table formatHead(String... codes);

    int getWidth();

    void create(FLog mainLog);

}
