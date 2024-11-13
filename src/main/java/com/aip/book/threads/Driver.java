package com.aip.book.threads;

public class Driver {
    public static void main(String[] args) {
        PrintEvenOdd printEvenOdd = new PrintEvenOdd();
        Numbers numbers = new Numbers(printEvenOdd);
        Thread t1 = new Thread(numbers);
        t1.setName("Even printer");
        Thread t2 = new Thread(numbers);
        t2.setName("Odd printer");

        t1.start();
        t2.start();

//        Task increment1 = new Task();
////        Task increment2 = new Task();
//
//        Thread t1 = new Thread(increment1);
//        Thread t2 = new Thread(increment1);
//
//
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//        System.out.println(increment1.getCount() + ", Count");
    }
}
