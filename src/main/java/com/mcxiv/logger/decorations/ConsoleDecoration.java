package com.mcxiv.logger.decorations;

import com.mcxiv.logger.tools.C;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.regex.Matcher;

public class ConsoleDecoration extends Decoration {

    public ConsoleDecoration(String... codes) {

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
                    colorcd += C.map.get(m.group(1));
                    content = content.replace(m.group(), "");

                } else if ((m = re_6color.matcher(content)).find()) {
                    colorcd += C.getFontColor(C.hex6ToColor(m.group(1)));
                    content = content.replace(m.group(), "");

                } else if ((m = re_3color.matcher(content)).find()) {
                    colorcd += C.getFontColor(C.hex3ToColor(m.group(1)));
                    content = content.replace(m.group(), "");

                } else if ((m = re_1color.matcher(content)).find()) {
                    colorcd += C.getFontColor(C.hexToGray(Integer.parseInt(m.group(1) + m.group(1), 16)));
                    content = content.replace(m.group(), "");

                } else if ((m = re_6Bcolor.matcher(content)).find()) {
                    colorcd += C.getBackColor(C.hex6ToColor(m.group(1)));
                    content = content.replace(m.group(), "");

                } else if ((m = re_3Bcolor.matcher(content)).find()) {
                    colorcd += C.getBackColor(C.hex3ToColor(m.group(1)));
                    content = content.replace(m.group(), "");

                } else if ((m = re_1Bcolor.matcher(content)).find()) {
                    colorcd += C.getBackColor(C.hexToGray(Integer.parseInt(m.group(1) + m.group(1), 16)));
                    content = content.replace(m.group(), "");

                }

            }


            // Parsing Time Formatting

            Supplier<String> timeFormatter = timeConsumerResolver(content);

            if ((m = re_timeFormat.matcher(content)).find()) content = content.replace(m.group(), "");


            // Parsing other formatting chars

            if (content.contains("b")) format.append(C.FB);
            if (content.contains("u")) format.append(C.FU);
            if (content.contains("~")) {
                last_one_repeats = true;
                repeater_index = i;
            }

            format.append(colorcd).append(pre);

            if ((m = re_formatting.matcher(content)).find()) format.append(m.group(1));
            else format.append("%s");

            format.append(suf).append(C.RS).append(sufsuf);

            for (int j = 0; j < content.length(); j++)
                if (content.charAt(j) == 'n') format.append('\n');


            final String form = format.toString();


            if (timeFormatter != null)
                decorates[i] = s -> timeFormatter.get() + String.format(form, s);
            else
                decorates[i] = s -> String.format(form, s);


            if ((m = re_centerFormatting.matcher(content)).find()) {

                int len = Integer.parseInt(m.group(1));

                final Decorate new_d = decorates[i];

                decorates[i] = s -> new_d.decorate(Decoration.center(len, s));

            }


            if ((m = re_wordWrap.matcher(content)).find()) {

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
            }

        }
    }

    private Supplier<String> timeConsumerResolver(String content) {

        if (content.contains("T"))
            return () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        Matcher m = re_timeFormat.matcher(content);
        if (m.find()) {
            String form = m.group(1).replace(';', ':');
            return () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern(form));
        }

        return null;
    }

}
