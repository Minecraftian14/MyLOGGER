package com.mcxiv.logger.boxUtilities;

import static com.mcxiv.logger.boxUtilities.Box.*;

class Surround {

    public static String getDoubleBorder(String text) {
        StringBuilder out = new StringBuilder().append(TR_DC);
        for (int i = 0; i < text.length() + 2; i++, out.append(DB)) ;
        out.append(TL_DC).append("\n");

        out.append(DP).append(" ").append(text).append(" ").append(DP);

        out.append("\n").append(BR_DC);
        for (int i = 0; i < text.length() + 2; i++, out.append(DB)) ;
        out.append(BL_DC);

        return out.toString();

    }



//    public static void main(String[] args) {
//        System.out.println(getDoubleBorder("Hello World!"));
//    }

}
