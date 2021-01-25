package com.mcxiv.logger.decorations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;
import java.util.regex.Matcher;

import static com.mcxiv.logger.decorations.Decoration.*;

public class DecorationCommonResolvers {


    public static Supplier<String> timeResolver(String content) {

        if (content.contains("T"))
            return () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

        Matcher m = re_timeFormat.matcher(content);
        if (m.find()) {
            String form = m.group(1).replace(';', ':');
            return () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern(form));
        }

        return null;

    }


    public static class TimeResolver {
        String content;

        public TimeResolver(String content) {
            this.content = content;
        }

        public Supplier<String> getTime() {
            if (content.contains("T"))
                return () -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);

            Matcher m = re_timeFormat.matcher(content);
            if (m.find()) {
                String form = m.group(1).replace(';', ':');
                content = content.replace(m.group(), "");
                return () -> LocalDateTime.now().format(DateTimeFormatter.ofPattern(form));
            }

            return null;
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
