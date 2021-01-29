package com.mcxiv.logger.decorations;

import com.mcxiv.logger.decorations.DecorationCommonResolvers.FormattingCodeSplitter;
import com.mcxiv.logger.decorations.DecorationCommonResolvers.TimeResolver;
import com.mcxiv.logger.tools.C;

import java.util.regex.Matcher;

public class ConsoleDecoration extends Decoration {

    public static final int NONE = 0;
    public static final int BLACK = 1;
    public static final int WHITE = 2;
    public static final int BIT_3 = 3;
    public static final int BIT_4 = 4;
    public static final int BIT_8 = 5;
    public static final int TRUE_COLOR_BIT_24 = 6;

    private static int STATE = TRUE_COLOR_BIT_24;

    public static void setColorMode(int mode) {
        if (mode < 0 || mode > TRUE_COLOR_BIT_24)
            throw new IllegalArgumentException("Only modes accepted are integers from 0 to 6 inclusive.");
        STATE = mode;
    }

    public ConsoleDecoration(Decorations.Tag tag, String... codes) {
        super(tag);

        decorates = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];


            //


            // Splitting up the formatting code to separate parts.
            FormattingCodeSplitter sp = new FormattingCodeSplitter(code);

            // And some basic initialisation.
            StringBuilder format = new StringBuilder();
            Matcher m;


            //


            if (tag != null) {
                String brush = "";
                if (sp.content.contains("P")) brush += "[" + tag.packageName + "]";
                if (sp.content.contains("C")) brush += "[" + tag.className + "]";
                if (sp.content.contains("M")) brush += "[" + tag.executableName + "]";
                sp.prepre = brush + " " + sp.prepre;
            }
            format.append(sp.prepre);


            //


            // Parsing Color type to code
            String colorcd = "";

            // Pickin' a color from 16x2 color set, half for fonts, half for bgs
            // Ran twice as to catch "possible" 2 inputs, one for font, one for bg
            if ((m = re_Ccolor.matcher(sp.content)).find()) {
                colorcd += C.map.get(m.group(1));
                sp.content = sp.content.replace(m.group(), "");

            }
            if ((m = re_Ccolor.matcher(sp.content)).find()) {
                colorcd += C.map.get(m.group(1));
                sp.content = sp.content.replace(m.group(), "");
            }

            // Font Color
            if ((m = re_6color.matcher(sp.content)).find()) {
                colorcd += C.hex.split6d(m.group(1), ConsoleDecoration::hexToFont);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3color.matcher(sp.content)).find()) {
                colorcd += C.hex.split3d(m.group(1), ConsoleDecoration::hexToFont);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1color.matcher(sp.content)).find()) {
                colorcd += C.hex.split1d(m.group(1), ConsoleDecoration::hexToFont);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_Scolor.matcher(sp.content)).find()) {
                colorcd += C.hex.split6d(m.group(1), ConsoleDecoration::hexToFont);
                sp.content = sp.content.replace(m.group(), "");
            }

            // Background Color
            if ((m = re_6Bcolor.matcher(sp.content)).find()) {
                colorcd += C.hex.split6d(m.group(1), ConsoleDecoration::hexToBack);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3Bcolor.matcher(sp.content)).find()) {
                colorcd += C.hex.split3d(m.group(1), ConsoleDecoration::hexToBack);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1Bcolor.matcher(sp.content)).find()) {
                colorcd += C.hex.split1d(m.group(1), ConsoleDecoration::hexToBack);
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_SBcolor.matcher(sp.content)).find()) {
                colorcd += C.hex.split6d(m.group(1), ConsoleDecoration::hexToBack);
                sp.content = sp.content.replace(m.group(), "");
            }


            //


            // Parsing Time Formatting
            TimeResolver timeResolver = new TimeResolver(sp.content);
            sp.content = timeResolver.content;


            //


            // Parsing other formatting chars

            if (sp.content.contains("rev")) format.append(C.FR);
            if (sp.content.contains("frm")) format.append(C.FFr);
            if (sp.content.contains("cir")) format.append(C.FCr);

//            if (sp.content.contains("bs")) format.append(C.BlS);
//            if (sp.content.contains("bf")) format.append(C.BlS);
            if (sp.content.contains("tu")) format.append(C.FUt);

            if (sp.content.contains("b")) format.append(C.FB);
            if (sp.content.contains("f")) format.append(C.FFa);
            if (sp.content.contains("i")) format.append(C.FI);
            if (sp.content.contains("u")) format.append(C.FU);
            if (sp.content.contains("o")) format.append(C.FOv);

            if (sp.content.contains("-"))
                if (!sp.content.contains("%") || sp.content.indexOf("-") < sp.content.indexOf("%"))
                    format.append(C.FS);

            if (sp.content.contains("R")) the_whole_repeats = true;
            else if (sp.content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }


            //


            // Finalising the static effects.

            format.append(colorcd).append(sp.pre);

            // If a specific formatting like '%25s' or '%-25s' is provided, use it, else put a simple '%s'
            if ((m = re_formatting.matcher(sp.content)).find()) format.append(m.group(1));
            else format.append("%s");

            format.append(sp.suf).append(C.RS).append(sp.sufsuf);

            // Apply as many tab-spaces as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 't') format.insert(0, '\t');

            // Apply as many new-lines as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 'n') format.append('\n');


            //


            // Final 'form' to which the input is entered.
            final String form = format.toString();

            decorates[i] = s -> String.format(form, s);


            // Applying Time if defined/specified.
            decorates[i] = timeResolver.TimeFormattingResolver(decorates[i]);


            // Applying Basic post Formatting
            decorates[i] = DecorationCommonResolvers.CommonFormattingResolver(m, sp.content, decorates[i]);


        }
    }


    public static String hexToFont(int r, int g, int b) {
        switch (STATE) {
            default:
            case NONE:
                return "";

            case BLACK:
                return C.BK;

            case WHITE:
                return C.W;

            case BIT_3:
                return C.hex.font.to3Bit(r, g, b);

            case BIT_4:
                return C.hex.font.to4Bit(r, g, b);

            case BIT_8:
                return C.hex.font.to8Bit(r, g, b);

            case TRUE_COLOR_BIT_24:
                return C.hex.font.to24Bit(r, g, b);
        }
    }

    public static String hexToBack(int r, int g, int b) {
        switch (STATE) {
            default:
            case NONE:
                return "";

            case BLACK:
                return C.BKBG;

            case WHITE:
                return C.WBG;

            case BIT_3:
                return C.hex.back.to3Bit(r, g, b);

            case BIT_4:
                return C.hex.back.to4Bit(r, g, b);

            case BIT_8:
                return C.hex.back.to8Bit(r, g, b);

            case TRUE_COLOR_BIT_24:
                return C.hex.back.to24Bit(r, g, b);
        }
    }
}
// Applying center format if '%*25s' provided.
            /*            if ((m = re_centerFormatting.matcher(sp.content)).find()) {

                int len = Integer.parseInt(m.group(1));

                final Decorate new_d = decorates[i];

                decorates[i] = s -> new_d.decorate(Decoration.center(len, s));

            }*/


// Applying word Wrap
            /*if ((m = re_wordWrap.matcher(sp.content)).find()) {

                int spc = Integer.parseInt(m.group(1));

                final Decorate new_d = decorates[i];

                decorates[i] = s -> {
                    StringBuilder builder = new StringBuilder();

                    int key = 0, lastkey = -1;
                    while (key + spc < s.length() && (key = s.lastIndexOf(" ", key + spc)) != -1) {
                        builder.append(new_d.decorate(s.substring(lastkey + 1, key))).append("\n");
                        lastkey = key;
                    }

                    return builder.toString();
                };
            }*/


// Applying splitter.
            /*if ((m = re_splitter.matcher(sp.content)).find()) {

                char c = m.group(1).charAt(0);

                final Decorate new_d = decorates[i];

                decorates[i] = s -> {
                    StringBuilder builder = new StringBuilder();

                    int last = 0;
                    for (int j = 0; j < s.length(); j++) {
                        if (s.charAt(j) == c) {
                            builder.append(new_d.decorate(s.substring(last, j)));
                            last = j + 1;
                        }
                    }

                    return builder.toString();
                };
            }*/