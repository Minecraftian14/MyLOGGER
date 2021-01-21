package com.mcxiv.logger.tools;

import com.mcxiv.logger.decorations.ConsoleDecoration;
import com.mcxiv.logger.decorations.Decoration;

import java.util.function.Function;

public class Environment {

    private static Function<String[], Decoration> decorator = ConsoleDecoration::new;

    public static Decoration getDecoration(String...codes) {
        return decorator.apply(codes);
    }

}
