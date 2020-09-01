package main.decoratedLogger;

import main.decoratedLogger.gui.LogViewer;
import main.decoratedLogger.log.Decoration;
import main.decoratedLogger.log.Log;

public class DLTest {

    public static void main(String[] args) {
//        Log.put("init", new Decoration("_[0f0]", "[0f0]"));
//
//        Log.put("func_a", new Decoration("b[f00]", "n[f00]"));
//        Log.put("func_b", new Decoration("s[ff0]", "n[ff0]"));
//        Log.put("func_c", new Decoration(".[f0f]", "n[f0f]"));
//
//        Log.put("hello", new Decoration("b[f00]"));
//
//        Log.put("sian_a", new Decoration("'[f00]", "n[f00]"));
//        Log.put("sian_b", new Decoration("h", "[ff0]n"));
//        Log.put("sian_c", new Decoration("-[f0f]", "n[f0f]"));
//
//        Log.put("meth_a", new Decoration("n[f00]", "n[f00]"));
//        Log.put("meth_b", new Decoration("n[ff0]: Hello ", "n[ff0]"));
//        Log.put("meth_c", new Decoration("n[f0f]", "n[f0f]"));

        new DLTest();
        new inner_DLTest();
        new outer_DLTest();

        Log.writeToDisk("");
        LogViewer.main(new String[]{});

    }

    public DLTest() {
        Log.print("Hello", "World");
        func_a();
    }

    public void func_a() {
        Log.print("Hello", "World");
        Log.print("hello", "hey there");
        func_b();
    }

    public void func_b() {
        Log.print("Hello", "World");
        func_c();
    }

    public void func_c() {
        Log.print("Hello", "World");
    }

    public static class inner_DLTest {


        public inner_DLTest() {
            Log.print("Hello", "World");
            meth_a();
        }

        public void meth_a() {
            Log.print("Hello", "World");
            meth_b();
        }

        public void meth_b() {
            Log.print("Hello", "World");
            meth_c();
        }

        public void meth_c() {
            Log.print("Hello", "World");
        }
    }
}

class outer_DLTest {


    public outer_DLTest() {
        Log.print("Hello", "World");
        sian_a();
    }

    public void sian_a() {
        Log.print("Hello", "World");
        sian_b();
    }

    public void sian_b() {
        Log.print("Hello", "World");
        sian_c();
    }

    public void sian_c() {
        Log.print("Hello", "World");
    }
}