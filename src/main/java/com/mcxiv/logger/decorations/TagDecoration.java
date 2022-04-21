package com.mcxiv.logger.decorations;

import java.util.HashMap;
import java.util.regex.Matcher;


public class TagDecoration extends Decoration {

    static final HashMap<String, String> map = new HashMap<>();

    static {
        map.put("BK", "[000000]");
        map.put("W", "[FFFFFF]");
        map.put("R", "[FF0000]");
        map.put("G", "[00FF00]");
        map.put("B", "[0000FF]");
        map.put("Y", "[FFFF00]");
        map.put("M", "[FF00FF]");
        map.put("C", "[00FFFF]");
        map.put("BBK", "[888888]");
        map.put("BW", "[F0F0F0]");
        map.put("BR", "[FF8888]");
        map.put("BG", "[88FF88]");
        map.put("BB", "[8888FF]");
        map.put("BY", "[FFFF88]");
        map.put("BM", "[FF88FF]");
        map.put("BC", "[88FFFF]");

        map.put("BKBG", "[@000000]");
        map.put("WBG", "[@FFFFFF]");
        map.put("RBG", "[@FF0000]");
        map.put("GBG", "[@00FF00]");
        map.put("BBG", "[@0000FF]");
        map.put("YBG", "[@FFFF00]");
        map.put("MBG", "[@FF00FF]");
        map.put("CBG", "[@00FFFF]");
        map.put("BKBBG", "[@888888]");
        map.put("WBBG", "[@F0F0F0]");
        map.put("RBBG", "[@FF8888]");
        map.put("GBBG", "[@88FF88]");
        map.put("BBBG", "[@8888FF]");
        map.put("YBBG", "[@FFFF88]");
        map.put("MBBG", "[@FF88FF]");
        map.put("CBBG", "[@88FFFF]");

        map.put("FB", "");
        map.put("FU", "[UNDERLINE]");
        map.put("FR", "");
        map.put("RS", "[RESET]");

    }

    public TagDecoration(Decorations.Tag tag, String... codes) {
        super(tag);

        decorates = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];


            //


            // Splitting up the formatting code to separate parts.
            DecorationCommonResolvers.FormattingCodeSplitter sp = new DecorationCommonResolvers.FormattingCodeSplitter(code);

            // And some basic initialisation.
            StringBuilder format = new StringBuilder(sp.prepre);
            Matcher m;


            //


            // Parsing Color type to code
            String colorcd = "";

            // Pickin' a color from 16x2 color set, half for fonts, half for bgs
            // Ran twice as to catch "possible" 2 inputs, one for font, one for bg
            if ((m = re_Ccolor.matcher(sp.content)).find()) {
                colorcd += map.get(m.group(1));
                sp.content = sp.content.replace(m.group(), "");

            }

            // Font Color
            if ((m = re_6color.matcher(sp.content)).find()) {
                colorcd += "[" + m.group(1) + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3color.matcher(sp.content)).find()) {
                String buf = m.group(1);
                colorcd += "[" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1color.matcher(sp.content)).find()) {
                String buf = m.group(1);
                colorcd += "[" + buf + buf + buf + buf + buf + buf + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_Scolor.matcher(sp.content)).find()) {
                colorcd += m.group(1);
                sp.content = sp.content.replace(m.group(), "");
            }

            // Background Color
            if ((m = re_6Bcolor.matcher(sp.content)).find()) {
                colorcd += "[@" + m.group(1) + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3Bcolor.matcher(sp.content)).find()) {
                String buf = m.group(1);
                colorcd += "[@" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1Bcolor.matcher(sp.content)).find()) {
                String buf = m.group(1);
                colorcd += "[@" + buf + buf + buf + buf + buf + buf + "]";
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_SBcolor.matcher(sp.content)).find()) {
                colorcd += m.group(1);
                sp.content = sp.content.replace(m.group(), "");
            }


            //


            // Parsing Time Formatting
            DecorationCommonResolvers.TimeResolver timeResolver = new DecorationCommonResolvers.TimeResolver(sp.content);
            sp.content = timeResolver.content;


            //


            // Parsing other formatting chars

            if (sp.content.contains("u")) format.append("[UNDERLINE]");

            if (sp.content.contains("-"))
                if (!sp.content.contains("%") || sp.content.indexOf("-") < sp.content.indexOf("%"))
                    format.append("[STRIKE]");

            if (sp.content.contains("R")) the_whole_repeats = true;
            else if (sp.content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }


            //


            // Finalising the static effects.

            format.append(colorcd.toUpperCase()).append(sp.pre);

            // If a specific formatting like '%25s' or '%-25s' is provided, use it, else put a simple '%s'
            if ((m = re_formatting.matcher(sp.content)).find()) format.append(m.group(1));
            else format.append("%s");

            format.append(sp.suf).append(map.get("RS")).append(sp.sufsuf);

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
            decorates[i] = DecorationCommonResolvers.CommonFormattingResolver(m, sp.content, decorates[i], " ", "\n");

        }
    }

}
