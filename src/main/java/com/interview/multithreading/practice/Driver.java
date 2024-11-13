package com.interview.multithreading.practice;

public class Driver {
    public static void main(String[] args) {
        PrintNumber pn = new PrintNumber(20);
        PrintEven pe = new PrintEven(pn);
        PrintOdd po = new PrintOdd(pn);

        Thread even = new Thread(pe);
        Thread odd = new Thread(po);

        even.setName("even-thread");
        odd.setName("odd-thread");

        even.start();
        odd.start();
    }
}

class PrintEven implements Runnable {
    private PrintNumber pn;

    public PrintEven(PrintNumber pn) {
        this.pn = pn;
    }

    @Override
    public void run() {
        this.pn.printEven();
    }
}

class PrintOdd implements Runnable {
    private PrintNumber pn;

    public PrintOdd(PrintNumber pn) {
        this.pn = pn;
    }

    @Override
    public void run() {
        this.pn.printOdd();
    }
}