package com.mcxiv.logger.decorations;

import com.mcxiv.logger.formatted.FLog;
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

    @Test
    public void testCenter() {
        System.out.println(Decoration.center(90, "Hello", " "));
    }

    @Test
    public void ww() {
        System.out.println(wrapString("Java is a class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA),[17] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[18] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[19][20] particularly for client-server web applications, with a reported 9 million developers.[21]" +
                                      "Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle) and released in 1995 as a core component of Sun Microsystems' Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GNU General Public License. Oracle offers its own HotSpot Java Virtual Machine, however the official reference implementation is the OpenJDK JVM which is free open source software and used by most developers and is the default JVM for almost all Linux distributions." +
                                      "As of September 2020, the latest version is Java 15, with Java 11, a currently supported long-term support (LTS) version, released on September 25, 2018. Oracle released the last zero-cost public update for the legacy version Java 8 LTS in January 2019 for commercial use, although it will otherwise still support Java 8 with public updates for personal use indefinitely. Other vendors have begun to offer zero-cost builds of OpenJDK 8 and 11 that are still receiving security and other upgrades.", "\n", 90));
    }

    @Test
    @Format(":w90w$Y:")
    public void wws() {
        FLog log = FLog.getNew();
        log.prt("Java is a class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let application developers write once, run anywhere (WORA),[17] meaning that compiled Java code can run on all platforms that support Java without the need for recompilation.[18] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages. As of 2019, Java was one of the most popular programming languages in use according to GitHub,[19][20] particularly for client-server web applications, with a reported 9 million developers.[21]" +
                "Java was originally developed by James Gosling at Sun Microsystems (which has since been acquired by Oracle) and released in 1995 as a core component of Sun Microsystems' Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GNU General Public License. Oracle offers its own HotSpot Java Virtual Machine, however the official reference implementation is the OpenJDK JVM which is free open source software and used by most developers and is the default JVM for almost all Linux distributions." +
                "As of September 2020, the latest version is Java 15, with Java 11, a currently supported long-term support (LTS) version, released on September 25, 2018. Oracle released the last zero-cost public update for the legacy version Java 8 LTS in January 2019 for commercial use, although it will otherwise still support Java 8 with public updates for personal use indefinitely. Other vendors have begun to offer zero-cost builds of OpenJDK 8 and 11 that are still receiving security and other upgrades.");
    }

    public static String wrapString(String s, String deliminator, int length) {
        StringBuilder sb = new StringBuilder(s);

        int i = 0;
        while (i + length < sb.length() && (i = sb.lastIndexOf(" ", i + length)) != -1) {
            sb.replace(i, i + 1, "\n");
        }

        return sb.toString();
    }

}