package com.mcxiv.logger.util;

public interface GroupIterator<T> {
    Object consume(int groupIndex, T[] group);
}
