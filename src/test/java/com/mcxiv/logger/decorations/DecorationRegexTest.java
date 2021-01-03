package com.mcxiv.logger.decorations;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class DecorationRegexTest {

    public static void _testPart(Pattern pat, String st, String rs) {
        Matcher m = pat.matcher(st);
        if (m.find()) assertEquals(m.group(1), rs);
        else fail();

        System.out.println(m.group(1));
        System.out.println(m.group(1));
        System.out.println(m.group(1));
        System.out.println(m.group(1));
    }

    @Test
    public void testParts() {

        String sample = "pp::p:c:s::ss";

        _testPart(Decoration.re_prepre, sample, "pp");
        _testPart(Decoration.re_pre, sample, "p");
        _testPart(Decoration.re_content, sample, "c");
        _testPart(Decoration.re_suf, sample, "s");
        _testPart(Decoration.re_sufsuf, sample, "ss");

    }

    @Test
    public void testCol() {

        Matcher m;

        assertTrue((m = Decoration.re_1color.matcher("#a")).find());
        assertEquals(m.group(1), "a");

        assertTrue((m = Decoration.re_1color.matcher("#abc")).find());
        assertEquals(m.group(1), "a");

        assertTrue((m = Decoration.re_3color.matcher("#abc")).find());
        assertEquals(m.group(1), "abc");

        assertTrue((m = Decoration.re_3color.matcher("#abcdef")).find());
        assertEquals(m.group(1), "abc");

        assertTrue((m = Decoration.re_6color.matcher("#abcdef")).find());
        assertEquals(m.group(1), "abcdef");


        assertTrue((m = Decoration.re_Ccolor.matcher("$B")).find());
        assertEquals(m.group(1), "B");

    }

}