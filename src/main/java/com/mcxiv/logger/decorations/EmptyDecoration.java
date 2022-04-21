package com.mcxiv.logger.decorations;

public class EmptyDecoration extends Decoration {

    public EmptyDecoration() {
        super(null);
        decorates = new Decorate[]{};
    }

}
