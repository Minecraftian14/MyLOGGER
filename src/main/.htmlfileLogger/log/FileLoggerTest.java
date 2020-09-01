package main.htmlfileLogger.log;

import main.utlities.Format;
import main.utlities.Html;
import main.utlities.Html.Wrap;

import static main.utlities.Format.NullFor;

public class FileLoggerTest {

    public static void main(String[] args) throws InterruptedException {
        FileLogger.put("main", new Format(NullFor), new Format(NullFor, s -> Wrap.BOLD(Wrap.COLO(s, 255, 0, 0))));
        FileLogger.put("a_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 0)));
        FileLogger.put("b_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 255)));
        FileLogger.put("c_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 0)));
        FileLogger.put("d_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 255)));
        FileLogger.put("e_func", new Format(NullFor), new Format(NullFor, Wrap::ITAL));
        FileLogger.put("f_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 0, 255)));
        FileLogger.put("g_func", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 0, 255)));
        FileLogger.put("h_func", new Format(NullFor), new Format(NullFor, Wrap::BOLD));
        FileLogger.put("i_func", new Format(NullFor), new Format(NullFor, Wrap::UNDE));

        FileLogger.put("a_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 0)));
        FileLogger.put("b_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 255)));
        FileLogger.put("c_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 0)));
        FileLogger.put("d_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 255)));
        FileLogger.put("e_math", new Format(NullFor), new Format(NullFor, Wrap::ITAL));
        FileLogger.put("f_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 0, 255)));
        FileLogger.put("g_math", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 0, 255)));
        FileLogger.put("h_math", new Format(NullFor), new Format(NullFor, Wrap::BOLD));
        FileLogger.put("i_math", new Format(NullFor), new Format(NullFor, Wrap::UNDE));

        FileLogger.put("a_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 0)));
        FileLogger.put("b_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 255, 255)));
        FileLogger.put("c_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 0)));
        FileLogger.put("d_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 255, 255)));
        FileLogger.put("e_seth", new Format(NullFor), new Format(NullFor, Wrap::ITAL));
        FileLogger.put("f_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 255, 0, 255)));
        FileLogger.put("g_seth", new Format(NullFor), new Format(NullFor, s -> Wrap.COLO(s, 0, 0, 255)));
        FileLogger.put("h_seth", new Format(NullFor), new Format(NullFor, Wrap::BOLD));
        FileLogger.put("i_seth", new Format(NullFor), new Format(NullFor, Wrap::UNDE));

        FileLogger.put("init", new Format(NullFor), new Format(NullFor, Wrap::BOLD));

        FileLogger.println("Initializing a new " + new Html().ABBR("FileLoggerTest", "FLT").toString() + " with the name ", "test");
        FileLoggerTest test = new FileLoggerTest();

        FileLogger.println("Calling the function c from ", "main.");
        test.c_func();


        new anotherEde().c_math();
        new anotherEde2().c_seth();

        FileLogger.writeToDisk("");

    }

    public FileLoggerTest() {
        FileLogger.println("Calling the function a and b from ", "constructor.");
        a_func();
        b_func();
    }

    public void a_func() {
        FileLogger.println("Calling the function d from ", "a_func.");
        d_func();

    }

    public void b_func() {
        FileLogger.println("Calling the function f from ", "b_func.");
        f_func();

    }

    public void c_func() {
        FileLogger.println("Calling the function g from ", "c_func.");
        g_func();

    }

    public void d_func() {
        FileLogger.println("Calling the function e from ", "d_func.");
        e_func();

    }

    public void e_func() {
        FileLogger.println("Calling the function h from ", "e_func.");
        h_func();

    }

    public void f_func() {
        FileLogger.println("Calling the function e from ", "f_func.");
        e_func();

    }

    public void g_func() {
        FileLogger.println("Calling the function d from ", "g_func.");
        d_func();

    }

    public void h_func() {
        FileLogger.println("Calling the function i from ", "h_func.");
        i_func();

    }

    public void i_func() {
        FileLogger.println("Function i called.", new Html().ABBR("I dont know why i am here.", "idkwiah").toString());

    }


    public static class anotherEde2 {
        public anotherEde2() {
            FileLogger.println("Calling the sethtion a and b from ", "constructor.");
            a_seth();
            b_seth();
        }

        public void a_seth() {
            FileLogger.println("Calling the sethtion d from ", "a_seth.");
            d_seth();

        }

        public void b_seth() {
            FileLogger.println("Calling the sethtion f from ", "b_seth.");
            f_seth();

        }

        public void c_seth() {
            FileLogger.println("Calling the sethtion g from ", "c_seth.");
            g_seth();

        }

        public void d_seth() {
            FileLogger.println("Calling the sethtion e from ", "d_seth.");
            e_seth();

        }

        public void e_seth() {
            FileLogger.println("Calling the sethtion h from ", "e_seth.");
            h_seth();

        }

        public void f_seth() {
            FileLogger.println("Calling the sethtion e from ", "f_seth.");
            e_seth();

        }

        public void g_seth() {
            FileLogger.println("Calling the sethtion d from ", "g_seth.");
            d_seth();

        }

        public void h_seth() {
            FileLogger.println("Calling the sethtion i from ", "h_seth.");
            i_seth();

        }

        public void i_seth() {
            FileLogger.println("Function i called.", new Html().ABBR("I dont know why i am here.", "idkwiah").toString());

        }
    }


}


class anotherEde {
    public anotherEde() {
        FileLogger.println("Calling the mathtion a and b from ", "constructor.");
        a_math();
        b_math();
    }

    public void a_math() {
        FileLogger.println("Calling the mathtion d from ", "a_math.");
        d_math();

    }

    public void b_math() {
        FileLogger.println("Calling the mathtion f from ", "b_math.");
        f_math();

    }

    public void c_math() {
        FileLogger.println("Calling the mathtion g from ", "c_math.");
        g_math();

    }

    public void d_math() {
        FileLogger.println("Calling the mathtion e from ", "d_math.");
        e_math();

    }

    public void e_math() {
        FileLogger.println("Calling the mathtion h from ", "e_math.");
        h_math();

    }

    public void f_math() {
        FileLogger.println("Calling the mathtion e from ", "f_math.");
        e_math();

    }

    public void g_math() {
        FileLogger.println("Calling the mathtion d from ", "g_math.");
        d_math();

    }

    public void h_math() {
        FileLogger.println("Calling the mathtion i from ", "h_math.");
        i_math();

    }

    public void i_math() {
        FileLogger.println("Function i called.", new Html().ABBR("I dont know why i am here.", "idkwiah").toString());

    }
}

