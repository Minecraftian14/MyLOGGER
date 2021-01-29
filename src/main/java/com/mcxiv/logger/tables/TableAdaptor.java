package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.packets.LambdaPacket;
import com.mcxiv.logger.tools.LogLevel;
import com.mcxiv.logger.util.GroupIterator;
import com.mcxiv.logger.util.Iterator;
import com.mcxiv.logger.util.LevelDependencyAdder;
import com.mcxiv.logger.util.StringsConsumer;
import javafx.scene.control.Tab;

import java.util.Arrays;

public abstract class TableAdaptor implements Table {

//    @Override
//    public Table iter(int a, int b, Iterator... its) {
//        return null;
//    }

    protected LogLevel level = null;

    @Override
    public Table iter(int b, Iterator... its) {
        return iter(0, b, 1, its);
    }

    @Override
    public Table iter(int a, int b, Iterator... its) {
        return iter(a, b, 1, its);
    }

    @Override
    public Table iter(int a, int b, int c, Iterator... its) {

        for (int i = a; i < b; i += c) { // for each row

            String[] msg = new String[its.length]; // every cell

            for (int j = 0; j < msg.length; j++)
                msg[j] = its[j].consume(i).toString(); // for each cell; jth cell on ith row

            row(msg);
        }
        return this;
    }

//    @SafeVarargs
    @Override
    public final <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its) {

        for (int i = 0; i < main.length; i += groupSize) { // For every bunch <-> For each row

            String[] msg = new String[its.length]; // every cell

            for (int j = 0; j < its.length; j++) // for each cell
                msg[j] = its[j].consume(i / groupSize, Arrays.copyOfRange(main, i, i + groupSize)).toString();

            row(msg);
        }
        return this;
    }

    @Override
    public Table setLogLevel(LogLevel level) {
        this.level = level;
        return this;
    }
}
