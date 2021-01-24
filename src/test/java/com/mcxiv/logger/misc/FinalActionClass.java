package com.mcxiv.logger.misc;

public class FinalActionClass {

    public static void main(String[] args) throws InterruptedException {
        FinalActionClass s = new FinalActionClass();
        s.met();
        Thread.sleep(5000);
        throw new RuntimeException();
//        s=null;
//        System.gc();
    }

    public FinalActionClass() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Um")));
    }

    public void met() {
        System.out.println("hi");
    }

}
