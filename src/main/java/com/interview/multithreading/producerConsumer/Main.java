package com.interview.multithreading.producerConsumer;

import java.util.LinkedList;

class Producerr implements Runnable {
    private LinkedList<Integer> buffer;
    private final int MAX_CAPACITY;

    public Producerr(LinkedList<Integer> buffer, int maxCapacity) {
        this.buffer = buffer;
        this.MAX_CAPACITY = maxCapacity;
    }

    @Override
    public void run() {
        int value = 0;
        while (true) {
            synchronized (buffer) {
                while (buffer.size() == MAX_CAPACITY) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Producer produced-" + value);
                buffer.add(value++);
                buffer.notify();
                try {
                    Thread.sleep(1000); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Consumerr implements Runnable {
    private LinkedList<Integer> buffer;

    public Consumerr(LinkedList<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int consumedValue = buffer.removeFirst();
                System.out.println("Consumer consumed-" + consumedValue);
                buffer.notify();
                try {
                    Thread.sleep(1000); // Simulate some processing time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> buffer = new LinkedList<>();
        final int MAX_CAPACITY = 5;

        Producerr producer = new Producerr(buffer, MAX_CAPACITY);
        Consumerr consumer = new Consumerr(buffer);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}

