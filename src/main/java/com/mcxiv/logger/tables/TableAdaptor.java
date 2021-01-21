package com.mcxiv.logger.tables;

import com.mcxiv.logger.util.LevelDependent;

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
        public Table iter(int a, int b, Iterator... its) {
            return this;
        }

        @Override
        public Table iter(int a, int b, int c, Iterator... its) {
            return this;
        }

        @Override
        public <T> Table bunch(T[] main, int groupSize, GroupIterator<T>... its) {
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
        public String create() {
            return "";
        }
    };

    @Override
    public TableAdaptor provide() {
        return this;
    }

    @Override
    public TableAdaptor provideEmpty() {
        return EMPTY_VESSEL;
    }


}
