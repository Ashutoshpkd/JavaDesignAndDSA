package com.aip.book.threads;

public class Numbers implements Runnable {

    private PrintEvenOdd printEvenOdd;
//    private String name;

    public Numbers(PrintEvenOdd printEvenOdd) {
        this.printEvenOdd = printEvenOdd;
//        this.name = name;
    }

    @Override
    public void run() {
        System.out.println();
        printEvenOdd.startPrinting();
    }
}

class PrintEvenOdd {
    private static int MAX_COUNT = 15;

    public void startPrinting() {
        while (MAX_COUNT >= 0) {
            StringBuilder curr = new StringBuilder();

            if (MAX_COUNT % 2 == 0) {
                synchronized (this) {
                    if (MAX_COUNT % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + MAX_COUNT);
                        MAX_COUNT--;
                        try {
                            this.wait();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                synchronized (this) {
                    if (MAX_COUNT % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + MAX_COUNT);
                        MAX_COUNT--;
                        this.notify();
                    }
                }
            }
        }
    }
}
