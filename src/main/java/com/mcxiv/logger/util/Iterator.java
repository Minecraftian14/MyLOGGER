package com.mcxiv.logger.util;

public interface Iterator {
    Object consume(int i);

    static String[] toArray(int a, int b, int c, Iterator its) {
        String[] objs = new String[(int) (Math.floor((b - a) / c))];
        for (int i = a, j = 0; i < b; i += c, j++)
            objs[j] = its.consume(i).toString();
        return objs;
    }
}
