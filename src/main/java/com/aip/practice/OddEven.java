package com.aip.practice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEven {
    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers(10);
        OddThread oddThread = new OddThread(printNumbers);
        EvenThread evenThread = new EvenThread(printNumbers);
        int[] nums = new int[]{1, 2, 3};

//        Thread t1 = new Thread(oddThread);
//        Thread t2 = new Thread(evenThread);
//        t1.setName("odd");
//        t2.setName("even");

//        t1.start();
//        t2.start();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(oddThread);
        executor.execute(evenThread);
        Map<Integer, Integer> freq = new HashMap<>();
        List[] bucket = new List[nums.length];

        for (int n : nums) freq.put(n, freq.getOrDefault(n, 0) + 1);


        executor.shutdown();
    }
}

class EvenThread implements Runnable {
    private PrintNumbers pn;

    public EvenThread(PrintNumbers pn) {
        this.pn = pn;
    }

    @Override
    public void run() {
        try {
            pn.printEvenNumber();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class OddThread implements Runnable {

    private PrintNumbers pn;

    public OddThread(PrintNumbers pn) {
        this.pn = pn;
    }

    @Override
    public void run() {
        try {
            pn.printOddNumber();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class PrintNumbers {
    private int num;
    private int currentNumber = 1;

    public PrintNumbers(int num) {
        this.num = num;
    }

    public synchronized void printEvenNumber() throws InterruptedException {
        while (currentNumber <= num) {
            if (currentNumber % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + currentNumber);
                currentNumber++;
                notify();
            } else {
                wait();
            }
        }
    }

    public synchronized void printOddNumber() throws InterruptedException {
        while (currentNumber <= num) {
            if (currentNumber % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + currentNumber);
                currentNumber++;
                notify();
            } else {
                wait();
            }
        }
    }
}
