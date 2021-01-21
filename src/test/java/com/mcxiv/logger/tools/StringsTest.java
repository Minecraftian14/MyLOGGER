package com.mcxiv.logger.tools;

import com.mcxiv.logger.formatted.FLog;
import com.mcxiv.logger.tables.Table;
import org.junit.Test;

import java.util.regex.Matcher;

public class StringsTest {

    static FLog log = FLog.getNew();
    static String[] matchNames = new String[]{"Everything", "pre pended space", "left/center align", "word-wrap", "char count spacing", "post pending space", "string/float/int"};

    public static String format(String format, String txt) {

        Matcher m = Strings.re_stsf.matcher(format);

        boolean f = m.find();

        if (f) System.out.println(Table.stripped()
                .title("> %"+format + " " + f)
                .head("SNo", "Match Type", "Match Value")
                .iter(0, m.groupCount() + 1, i -> i, i -> matchNames[i], group -> m.group(group).replace(" ", "="))
                .formatTitle(":@4085eeb:")
                .formatHead(":@2565ae#fff:", ":@0f5298#fff:")
                .format(":@66d3fa:", ":@55d3fe:")
                .create()
        );


        return "";
    }

    @Test
    public void test() {
        format("%s", "");
        format("%23s", "");
        format("%  23s", "");
        format("%23  s", "");
        format("%#23s", "");
        format("%_23s", "");
        format("%-23s", "");
        format("%3f", "");
    }

}