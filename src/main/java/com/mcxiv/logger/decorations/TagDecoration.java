package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.C;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TagDecoration extends Decoration {

    static Pattern re_Scolor = Pattern.compile("([\\[][A-Fa-f0-9]{6,8}[]])");

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
        map.put("BM", "[FF88]FF");
        map.put("BC", "[88FFFF]");
        map.put("BKBG", "");
        map.put("WBG", "");
        map.put("RBG", "");
        map.put("GBG", "");
        map.put("BBG", "");
        map.put("YBG", "");
        map.put("MBG", "");
        map.put("CBG", "");
        map.put("BKBBG", "");
        map.put("WBBG", "");
        map.put("RBBG", "");
        map.put("GBBG", "");
        map.put("BBBG", "");
        map.put("YBBG", "");
        map.put("MBBG", "");
        map.put("CBBG", "");
        map.put("FB", "");
        map.put("FU", "");
        map.put("FR", "");
        map.put("RS", "[FFFFFF]");

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

                } else if ((m = re_Scolor.matcher(content)).find()) {
                    colorcd += m.group(1);
                    content = content.replace(m.group(), "");

                }

            }


            //


            // Parsing other formatting chars

            format.append(colorcd).append(pre).append("%s").append(suf).append("[FFFFFF]").append(sufsuf);
            for (int j = 0; j < content.length(); j++)
                if (content.charAt(j) == 'n') format.append('\n');

            final String form = format.toString();
            decorates[i] = s -> String.format(form, s);

        }
    }
}
