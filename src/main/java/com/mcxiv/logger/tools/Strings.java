package com.mcxiv.logger.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

    static Pattern re_stsf = Pattern.compile("^[%]([ ]*)([-_]?)([#]?)([\\d]*)([ ]*)([sfd])$");
    // new String[]{"pre pended space", "left/center align", "word-wrap", "char count spacing", "post pending space", "string/float/int"};

    public static String format(String format, String txt) {
        Matcher m = Strings.re_stsf.matcher(format);

        here:
        if (m.find()) {

            if (!m.group(1).isEmpty()) // pre pended space
                for (int i = 0; i < Integer.parseInt(m.group(1)); i++)
                    txt = " " + txt;

            if (!m.group(5).isEmpty()) // post pending space
                for (int i = 0; i < Integer.parseInt(m.group(1)); i++)
                    txt += " ";

            if (m.group(4).isEmpty()) break here;  // char count spacing

            int len = Integer.parseInt(m.group(4)) - txt.length();

            if (m.group(2).equals("-"))
                for (int i = 0; i < len; i++)
                    txt += " ";

            else if (m.group(2).equals("_")) {
                for (int i = 0; i < len / 2; i++)
                    txt += " ";
                for (int i = 0; i < len / 2; i++)
                    txt = " " + txt;
            }


        }


        return "";
    }

}
