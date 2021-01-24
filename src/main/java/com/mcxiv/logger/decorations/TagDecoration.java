package com.mcxiv.logger.decorations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.regex.Matcher;


public class TagDecoration extends Decoration {

    private static final HashMap<String, String> map = new HashMap<>();

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

    public TagDecoration(String... codes) {

        decorates = new Decorate[codes.length];

        for (int i = 0; i < codes.length; i++) {
            String code = codes[i];

            // Extracting different parts of input

            Matcher m;

            m = re_prepre.matcher(code);
            String prepre = m.find() ? m.group(1) : "";

            m = re_pre.matcher(code);
            String pre = m.find() ? m.group(1) : "";

            m = re_content.matcher(code);
            String content = m.find() ? m.group(1) : "";

            m = re_suf.matcher(code);
            String suf = m.find() ? m.group(1) : "";

            m = re_sufsuf.matcher(code);
            String sufsuf = m.find() ? m.group(1) : "";

            StringBuilder format = new StringBuilder(prepre);


            // Parsing Color type to code


            String colorcd = "";

            for (int r = 0; r < 2; r++) {

                if ((m = re_Ccolor.matcher(content)).find()) {
                    colorcd += map.get(m.group(1));
                    content = content.replace(m.group(), "");

                } else if ((m = re_6color.matcher(content)).find()) {
                    colorcd += "[" + m.group(1) + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_3color.matcher(content)).find()) {
                    String buf = m.group(1);
                    colorcd += "[" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_1color.matcher(content)).find()) {
                    String buf = m.group(1);
                    colorcd += "[" + buf + buf + buf + buf + buf + buf + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_6Bcolor.matcher(content)).find()) {
                    colorcd += "[@" + m.group(1) + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_3Bcolor.matcher(content)).find()) {
                    String buf = m.group(1);
                    colorcd += "[@" + buf.charAt(0) + buf.charAt(0) + buf.charAt(1) + buf.charAt(1) + buf.charAt(2) + buf.charAt(2) + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_1Bcolor.matcher(content)).find()) {
                    String buf = m.group(1);
                    colorcd += "[@" + buf + buf + buf + buf + buf + buf + "]";
                    content = content.replace(m.group(), "");

                } else if ((m = re_Scolor.matcher(content)).find()) {
                    colorcd += m.group(1);
                    content = content.replace(m.group(), "");

                }

            }


            // Parsing Time Formatting

            if ((m = re_timeFormat.matcher(content)).find()) {
                format.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern(m.group(1).replace(';', ':'))));
                content = content.replace(m.group(), "");
            }


            // Parsing other formatting chars

            if (content.contains("T")) format.append(LocalDateTime.now().toString());
//          if (content.contains("b")) format.append(C.FB);
            if (content.contains("u")) format.append("[UNDERLINE]");

            if (content.contains("-"))
                if (!content.contains("%") || content.indexOf("-") < content.indexOf("%"))
                    format.append("[STRIKE]");
            if (content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }

            format.append(colorcd.toUpperCase()).append(pre);

            if ((m = re_formatting.matcher(content)).find()) format.append(m.group(1));
            else format.append("%s");

            format.append(suf).append(map.get("RS")).append(sufsuf);

            for (int j = 0; j < content.length(); j++)
                if (content.charAt(j) == 'n') format.append('\n');

            final String form = format.toString();

            decorates[i] = s -> String.format(form, s);

        }
    }

}
