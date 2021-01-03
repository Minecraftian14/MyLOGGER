package com.mcxiv.logger.formatted;

import com.mcxiv.logger.decorations.ConsoleDecoration;
import com.mcxiv.logger.decorations.Decoration;

import java.util.function.Function;

abstract class Logger_DecorationManager implements Logger_MethodCollection {

    private Function<String[], Decoration> decorator;

    public Logger_DecorationManager() {
        this.decorator = defaultDecorator();
    }

    public void setDecorator(Function<String[], Decoration> decorator) {
        this.decorator = decorator;
    }

    protected Decoration yieldDecorator(String...args) {
        return decorator.apply(args);
    }

    public static Function<String[], Decoration> defaultDecorator() {
        return ConsoleDecoration::new;
    }

}
