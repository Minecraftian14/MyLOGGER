package com.mcxiv.logger.tables;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.util.GroupIterator;
import com.mcxiv.logger.util.Iterator;
import com.mcxiv.logger.util.LevelDependent;

import java.util.Arrays;

public abstract class TableAdaptor extends LevelDependent<TableAdaptor> implements Table {

    static final TableAdaptor EMPTY_VESSEL = new TableAdaptor() {
        @Override
        public Table title(String title) {
            return this;
        }

        @Override
        public Table head(String... msg) {
            return this;
        }

        @Override
        public Table row(String... msg) {
            return this;
        }

        @Override
        public Table format(String... codes) {
            return this;
        }

        @Override
        public Table formatTitle(String code) {
            return this;
        }

        @Override
        public Table formatHead(String... codes) {
            return this;
        }

        @Override
        public int getWidth() {
            return 0;
        }

        @Override
        public void create(FLog mainLog) {
        }
    };

//    @Override
//    public Table iter(int a, int b, Iterator... its) {
//        return null;
//    }

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

    @SafeVarargs
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
    public TableAdaptor provide() {
        return this;
    }

    @Override
    public TableAdaptor provideEmpty() {
        return EMPTY_VESSEL;
    }


}
