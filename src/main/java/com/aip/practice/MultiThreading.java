package com.aip.practice;

import java.util.ArrayList;
import java.util.List;

public class MultiThreading {
    public static void main(String[] args) {
        PrintEvenOdd peo = new PrintEvenOdd(new Object());
        Thread odd = new Thread(peo);
        Thread even = new Thread(peo);
        List<String> names = new ArrayList<>();
        names.add("AS");
        names.add("Eren");

        odd.setName("odd");
        even.setName("even");

        odd.start();
        even.start();


    }
}

class PrintEvenOdd implements Runnable {
    private int count = 1;
    private Object lock;

    public PrintEvenOdd(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (count <= 10) {
            if (count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                synchronized (lock) {
                    System.out.println("Name: " + Thread.currentThread().getName() + ", value: " + count);
                    count++;
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (count % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (lock) {
                    System.out.println("Name: " + Thread.currentThread().getName() + ", value: " + count);
                    count++;
                    lock.notify();
                }
            }
        }
    }
}
