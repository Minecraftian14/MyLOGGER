package com.mcxiv.logger.decorations;

import com.mcxiv.logger.decorations.DecorationCommonResolvers.FormattingCodeSplitter;
import com.mcxiv.logger.decorations.DecorationCommonResolvers.TimeResolver;

import java.util.function.Consumer;
import java.util.regex.Matcher;

public class HTMLDecoration extends Decoration {

    public static final String CSS_FORMATTING_REQUIRED_FOR_HTML
            = "" +
              "<style>\n" +
              "    *{\n" +
              "        font-family: monospace;\n" +
              "    }\n" +
              "    .rev_1114 {\n" +
              "    }\n" +
              "    .frm_1114 {\n" +
              "        border: 1px solid;\n" +
              "    }\n" +
              "    .cir_1114 {\n" +
              "        border: 1px solid;\n" +
              "        border-radius: 100%;\n" +
              "    }\n" +
              "    .bs_1114 {\n" +
              "        animation: blinker 1s linear infinite;\n" +
              "    }\n" +
              "    .bf_1114 {\n" +
              "        animation: blinker 250ms linear infinite;\n" +
              "    }\n" +
              "    @keyframes blinker {\n" +
              "        50% {\n" +
              "            opacity: 0;\n" +
              "        }\n" +
              "    }\n" +
              "    .tu_1114 {\n" +
              "        border-bottom: 3px solid;\n" +
              "    }\n" +
              "    .b_1114 {\n" +
              "        font-weight: bold;\n" +
              "    }\n" +
              "    .f_1114 {\n" +
              "        /* TODO: Faint's not working. */\n" +
              "        font-weight: lighter;\n" +
              "    }\n" +
              "    .i_1114 {\n" +
              "        font-style: italic;\n" +
              "    }\n" +
              "    .u_1114 {\n" +
              "        text-decoration: underline;\n" +
              "    }\n" +
              "    .o_1114 {\n" +
              "        text-decoration: overline;\n" +
              "    }\n" +
              "</style>".replace(" ", "");

    public HTMLDecoration(Decorations.Tag tag, String... codes) {
        super(tag);

        decorates = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];
//            code = code.replaceAll("\n", "<br/>");


            //


            // Splitting up the formatting code to separate parts.
            FormattingCodeSplitter sp = new FormattingCodeSplitter(code);
            sp.prepre = sp.prepre.replace(" ", "&nbsp;").replace("\n", "<br/>");
            sp.sufsuf = sp.sufsuf.replace(" ", "&nbsp;").replace("\n", "<br/>");
            sp.pre = sp.pre.replace(" ", "&nbsp;").replace("\n", "<br/>");
            sp.suf = sp.suf.replace(" ", "&nbsp;").replace("\n", "<br/>");

            // And some basic initialisation.
            StringBuilder format = new StringBuilder();
            Matcher m;


            //


            if (tag != null) {
                String brush = "";
                String subcont = sp.content.replaceAll("<.*>", "");
                if (subcont.contains("P")) brush += "[" + tag.packageName + "]";
                if (subcont.contains("C")) brush += "[" + tag.className + "]";
                if (subcont.contains("M")) brush += "[" + tag.executableName + "]";
                if (!brush.equals("")) sp.prepre = brush + " " + sp.prepre;
            }
            format.append(sp.prepre);


            //


            // Parsing Color type to code
            StringBuilder style = new StringBuilder();

            // Pickin' a color from 16x2 color set, half for fonts, half for bgs
            // Ran twice as to catch "possible" 2 inputs, one for font, one for bg
            if ((m = re_Ccolor.matcher(sp.content)).find()) {
                String s = TagDecoration.map.get(m.group(1));
                s = s.substring(1, s.length() - 1);
                if (!s.startsWith("@")) s = "#" + s;
                style.append(String.format("color: %s;", s));
                sp.content = sp.content.replace(m.group(), "");

            }
            if ((m = re_Ccolor.matcher(sp.content)).find()) {
                String s = TagDecoration.map.get(m.group(1));
                s = s.substring(1, s.length() - 1);
                if (!s.startsWith("@")) s = "#" + s;
                style.append(String.format("color: %s;", s));
                sp.content = sp.content.replace(m.group(), "");
            }

            // Font Color
            if ((m = re_6color.matcher(sp.content)).find()) {
                style.append(String.format("color: #%s;", m.group(1)));
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3color.matcher(sp.content)).find()) {
                String buf = m.group(1);
                style.append("color: #" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + ";");
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1color.matcher(sp.content)).find()) {
                String buf = m.group(1);
                style.append("color: #" + buf + buf + buf + buf + buf + buf + ";");
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_Scolor.matcher(sp.content)).find()) {
                style.append(String.format("color: #%s;", m.group(1)));
                sp.content = sp.content.replace(m.group(), "");
            }

            // Background Color
            if ((m = re_6Bcolor.matcher(sp.content)).find()) {
                style.append(String.format("background-color: #%s;", m.group(1)));
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_3Bcolor.matcher(sp.content)).find()) {
                String buf = m.group(1);
                style.append("background-color: #" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + ";");
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_1Bcolor.matcher(sp.content)).find()) {
                String buf = m.group(1);
                style.append("background-color: #" + buf + buf + buf + buf + buf + buf + ";");
                sp.content = sp.content.replace(m.group(), "");

            } else if ((m = re_SBcolor.matcher(sp.content)).find()) {
                style.append(String.format("background-color: #%s;", m.group(1)));
                sp.content = sp.content.replace(m.group(), "");
            }


            //


            // Parsing Time Formatting
            TimeResolver timeResolver = new TimeResolver(sp.content);
            sp.content = timeResolver.content;


            //


            // Parsing other formatting chars

            StringBuilder clazz = new StringBuilder();
            Consumer<String> simpleHelper = s -> {
                if (sp.content.contains(s)) clazz.append(s).append("_1114 ");
            };

            simpleHelper.accept("rev");
            sp.content = sp.content.replace("rev", "");
            simpleHelper.accept("frm");
            sp.content = sp.content.replace("frm", "");
            simpleHelper.accept("cir");
            sp.content = sp.content.replace("cir", "");

            simpleHelper.accept("bs");
            sp.content = sp.content.replace("bs", "");
            simpleHelper.accept("bf");
            sp.content = sp.content.replace("bf", "");
            simpleHelper.accept("tu");
            sp.content = sp.content.replace("tu", "");

            simpleHelper.accept("b");
            simpleHelper.accept("f");
            simpleHelper.accept("i");
            simpleHelper.accept("u");
            simpleHelper.accept("o");

            if (sp.content.contains("-"))
                if (!sp.content.contains("%") || sp.content.indexOf("-") < sp.content.indexOf("%"))
                    clazz.append("minus" + "_1114 ");

            if (sp.content.contains("R")) the_whole_repeats = true;
            else if (sp.content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }


            //


            // Finalising the static effects.

            format.append("<span style='").append(style).append("' class='").append(clazz).append("'>").append(sp.pre);

            String subFormat;
            // If a specific formatting like '%25s' or '%-25s' is provided, use it, else put a simple '%s'
            if ((m = re_formatting.matcher(sp.content)).find()) subFormat = m.group(1);
            else subFormat = "%s";

            format.append("%s");

            format.append(sp.suf).append("</span>").append(sp.sufsuf);

            // Apply as many tab-spaces as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 't') format.insert(0, "&emsp;");

            // Apply as many new-lines as specified.
            for (int j = 0; j < sp.content.length(); j++)
                if (sp.content.charAt(j) == 'n') format.append("<br/>");


            //


            // Final 'form' to which the input is entered.
            final String form = format.toString();

            decorates[i] = s -> String.format(form, String.format(subFormat, s).replaceAll(" ", "&nbsp;"));


            // Applying Time if defined/specified.
            decorates[i] = timeResolver.TimeFormattingResolver(decorates[i]);


            // Applying Basic post Formatting
            decorates[i] = DecorationCommonResolvers.CommonFormattingResolver(m, sp.content, decorates[i], "&nbsp;", "<br/>");

        }
    }
}