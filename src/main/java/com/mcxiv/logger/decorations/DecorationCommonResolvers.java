package com.mcxiv.logger.decorations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import static com.mcxiv.logger.decorations.Decoration.*;

public class DecorationCommonResolvers {


    public static Decorate CommonFormattingResolver(Matcher m, String content, Decorate decorate) {
        decorate = CenterFormattingResolver(m, content, decorate);
        decorate = WordWarpFormattingResolver(m, content, decorate);
        decorate = SplittingFormattingResolver(m, content, decorate);
        return decorate;
    }

    public static Decorate CenterFormattingResolver(Matcher m, String content, Decorate decorate) {
        if ((m = re_centerFormatting.matcher(content)).find()) {
            int len = Integer.parseInt(m.group(1));
            final Decorate new_d = decorate;
            return s -> new_d.decorate(Decoration.center(len, s));
        }
        return decorate;
    }

    public static Decorate WordWarpFormattingResolver(Matcher m, String content, Decorate decorate) {
        if ((m = re_wordWrap.matcher(content)).find()) {

            int spc = Integer.parseInt(m.group(1));
            final Decorate new_d = decorate;

            return s -> {
                StringBuilder builder = new StringBuilder();

                int key = 0, lastkey = -1;
                while (key + spc < s.length() && (key = s.lastIndexOf(" ", key + spc)) != -1) {
                    builder.append(new_d.decorate(s.substring(lastkey + 1, key))).append("\n");
                    lastkey = key;
                }

                return builder.toString();
            };
        }
        return decorate;
    }

    public static Decorate SplittingFormattingResolver(Matcher m, String content, Decorate decorate) {
        if ((m = re_splitter.matcher(content)).find()) {

            char c = m.group(1).charAt(0);

            final Decorate new_d = decorate;

            return s -> {
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
        }
        return decorate;
    }

    public static Decorate WordRepeaterFormattingResolver(Matcher m, String content, Decorate decorate) {
        if ((m = re_wordRepeater.matcher(content)).find()) {
            int len = Integer.parseInt(m.group(1));
            final Decorate new_d = decorate;
            return s -> new_d.decorate(Decoration.center(len, s));
        }
        return decorate;
    }

    public static class TimeResolver {
        String content;
        Supplier<String> supplier;

        public TimeResolver(String content) {
            this.content = content;
            testTime();
        }

        public void testTime() {
            if (content.contains("T")) {
                supplier = () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
                return;
            }

            Matcher m = re_timeFormat.matcher(content);
            if (m.find()) {
                String form = m.group(1).replace(';', ':');
                content = content.replace(m.group(), "");
                supplier = () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern(form));
                return;
            }

            supplier = null;
        }

        public Decorate TimeFormattingResolver(Decorate decorate) {
            if (supplier != null)
                return s -> supplier.get() + decorate.decorate(s);
            else return decorate;
        }
    }

    public static class FormattingCodeSplitter {
        String prepre = "";
        String pre = "";
        String content = "";
        String suf = "";
        String sufsuf = "";

        public FormattingCodeSplitter(String code) {
            Matcher m;
            if ((m = re_prepre.matcher(code)).find()) prepre = m.group(1);
            if ((m = re_pre.matcher(code)).find()) pre = m.group(1);
            if ((m = re_content.matcher(code)).find()) content = m.group(1);
            if ((m = re_suf.matcher(code)).find()) suf = m.group(1);
            if ((m = re_sufsuf.matcher(code)).find()) sufsuf = m.group(1);
        }


    }

}
