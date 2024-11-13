package com.interview.multithreading.producerConsumer;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Queue queue = new Queue();

        Producer p = new Producer(queue);
        Consumer c = new Consumer(queue);

        Thread p1 = new Thread(p);
        Thread c1 = new Thread(c);

        p1.start();
        c1.start();
    }
}
