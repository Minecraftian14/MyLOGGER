package com.mcxiv.logger;

abstract class Logger_ShortyToMethodRemapper implements Logger_MethodCollection {

    @Override
    public void g(String... i) {
        general(i);
    }

    @Override
    public void n(String... i) {
        notice(i);
    }

    @Override
    public void i(String... i) {
        info(i);
    }

    @Override
    public void a(String... i) {
        advert(i);
    }

    @Override
    public void e(String... i) {
        error(i);
    }

    @Override
    public void s(String... i) {
        status(i);
    }

    @Override
    public void g(Object... i) {
        general(i);
    }

    @Override
    public void n(Object... i) {
        notice(i);
    }

    @Override
    public void i(Object... i) {
        info(i);
    }

    @Override
    public void a(Object... i) {
        advert(i);
    }

    @Override
    public void e(Object... i) {
        error(i);
    }

    @Override
    public void s(Object... i) {
        status(i);
    }
}
