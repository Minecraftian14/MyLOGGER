package com.mcxiv.logger.tables;

public interface Table {

    static TableAdaptor stripped() {
        return new StrippedTable();
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

    Table title(String title);

    Table head(String... msg);

    Table row(String... msg);

    Table iter(int a, int b, Iterator... its);

    Table iter(int a, int b, int c, Iterator... its);

    <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its);

    Table format(String... codes);

    Table formatTitle(String code);

    Table formatHead(String... codes);

    String create();

    interface Iterator {
        Object consume(int i);
    }

    interface GroupIterator<T> {
        Object consume(int groupIndex, T[] group);
    }

}
