package com.mcxiv.logger.util;

import com.mcxiv.logger.tools.C;
import org.junit.Test;

import java.io.IOException;
import java.util.function.BiConsumer;

public class CTest {

    @Test
    public void justPrintTheShit() {

        System.out.println("Normal\tBold\tUnderlined\tReversed");

        BiConsumer<String, String> print = (c, t) -> System.out.println(c + t + C.RS + "\t" + C.FB + c + t + C.RS + "\t" + C.FU + c + t + C.RS + "\t" + C.FR + c + t + C.RS);

        System.out.println("Simple");

        print.accept(C.BK, "Hello");
        print.accept(C.W, "Hello");
        print.accept(C.R, "Hello");
        print.accept(C.G, "Hello");
        print.accept(C.B, "Hello");
        print.accept(C.Y, "Hello");
        print.accept(C.M, "Hello");
        print.accept(C.C, "Hello");

        System.out.println("Bright");

        print.accept(C.BBK, "Hello");
        print.accept(C.BW, "Hello");
        print.accept(C.BR, "Hello");
        print.accept(C.BG, "Hello");
        print.accept(C.BB, "Hello");
        print.accept(C.BY, "Hello");
        print.accept(C.BM, "Hello");
        print.accept(C.BC, "Hello");

        System.out.println("Simple Background");

        print.accept(C.BKBG, "Hello");
        print.accept(C.WBG, "Hello");
        print.accept(C.RBG, "Hello");
        print.accept(C.GBG, "Hello");
        print.accept(C.BBG, "Hello");
        print.accept(C.YBG, "Hello");
        print.accept(C.MBG, "Hello");
        print.accept(C.CBG, "Hello");

        System.out.println("Bright Background");

        print.accept(C.BKBBG, "Hello");
        print.accept(C.WBBG, "Hello");
        print.accept(C.RBBG, "Hello");
        print.accept(C.GBBG, "Hello");
        print.accept(C.BBBG, "Hello");
        print.accept(C.YBBG, "Hello");
        print.accept(C.MBBG, "Hello");
        print.accept(C.CBBG, "Hello");

    }

    // Works only in terminal output
    @Test
    public void checkingOutCMF() throws IOException {

        System.out.println("\u001B[123m");

        System.out.write("Hello\n".getBytes());
//        System.out.print(C.SAVE_CURSOR_POSITION);
        System.out.write("World".getBytes());
        System.out.write(C.CURSOR_LEFT(3).getBytes());

//        System.out.print(C.LOAD_CURSOR_POSITION);
        System.out.write("HHHH".getBytes());

//        System.out.print(C.CLEAR_LINE_FROM_CURSOR_TO_START);
//        System.out.print(C.CLEAR_SCREEN_ENTIRE);

    }

    @Test
    public void moreColours() {

        // 16 colours (instead of 8) which basically has the same ol'colours but also with a darker version
        for (int i = 0; i < 16; i++)
            System.out.print(C.getFontColor(i) + "▉" + C.RS);

        System.out.println();
        for (int i = 0; i < 16; i++)
            System.out.println(C.getFontColor(i) + " " + i + " =\t▉▉" + C.RS);

        for (int i = 16; i < 256; i++) {
            System.out.print(C.getFontColor(i) + "▉" + C.RESET);
            if ((i + 3) % 6 == 0) {
                System.out.print("\t\t");
                for (int j = i - 5; j <= i; j++)
                    System.out.print(C.getFontColor(j) + " " + j + " = ▉  " + C.RESET);
                System.out.println();
            }
        }

    }

    @Test
    public void colourTranslation() {

        int col = C.hexTo216(128, 128, 128);

        System.out.println(C.getFontColor(col) + "Example" + C.RS);
        System.out.println(C.getBackColor(col) + "Example" + C.RS);

    }

    @Test
    public void lengthTest() {

        String val = C.B + C.RBG + "abcdef" + C.RS;

        System.out.println(val);
        System.out.println(val.length());
        System.out.println(C.length(val));
    }


    @Test
    public void newHexConvTest() {
        System.out.println(C.hex.font.to3Bit(255, 70, 70) + "Hello");
        System.out.println(C.hex.font.to4Bit(255, 70, 70) + "Hello");
        System.out.println(C.hex.font.to8Bit(255, 70, 70) + "Hello");
        System.out.println(C.hex.font.to24Bit(255, 70, 70) + "Hello");

        System.out.println(C.hex.font.to8Bit(255, 255, 255) + "Hello" + C.RS);

        System.out.println(C.hex.split6d("#5f67e4ff", (a, b, c) -> a + " " + b + " " + c));
        ;
        System.out.println(C.hex.split6d("5f67e4ff", (a, b, c) -> a + " " + b + " " + c));
        ;
        System.out.println(C.hex.split6d("#5f67e4", C.hex.font::to24Bit) + "Hello");
    }


    @Test
    public void ExtraterriostrialCodeTest() {
//        System.out.println("\u001b[52;5;45m Hello\nWorld");
        System.out.println(" \u001b[51m  Hello  \u001b[0m\n  \u001b[51m   World \n  0 ");
        System.out.println(" \u001b[52m  Hello  \u001b[0m\n  \u001b[52m   World");

        for (int i = 0; i < 255; i++)
            System.out.printf("%3d \u001b[%dm Hello %s\n", i, i, C.RS);
    }

    @Test
    public void Um() {
        System.out.println("Hello");
        System.out.println("\u001b[1AWorld");
        // output -> World         in terminal
    }
}