package com.mcxiv.logger.boxUtilities;

import static com.mcxiv.logger.boxUtilities.Box.*;

class Surround {

    public static String getDoubleBorder(String text) {
        StringBuilder out = new StringBuilder().append(TL_DC);
        for (int i = 0; i < text.length() + 2; i++, out.append(DB)) ;
        out.append(TR_DC).append("\n");

        out.append(DP).append(" ").append(text).append(" ").append(DP);

        out.append("\n").append(BL_DC);
        for (int i = 0; i < text.length() + 2; i++, out.append(DB)) ;
        out.append(BR_DC);

        return out.toString();

    }

}
