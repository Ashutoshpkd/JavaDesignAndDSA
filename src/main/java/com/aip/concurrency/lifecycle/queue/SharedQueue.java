package com.aip.concurrency.lifecycle.queue;

import java.util.Deque;
import java.util.LinkedList;

public class SharedQueue {
    private final Deque<Integer> buffer = new LinkedList<>();
    private final int CAPACITY;

    public SharedQueue(int capacity) {
        this.CAPACITY = capacity;
    }

    public synchronized void consume() {
        long startTime = System.currentTimeMillis();
        long remainingTime = 10000;
        try {
            while (buffer.isEmpty()) {
                if (remainingTime <= 0) {
                    throw new InterruptedException("Consumer waiting indefinitely");
                }
                System.out.println("Buffer is empty... Waiting");
                wait(remainingTime);

                long elapsedTime = System.currentTimeMillis() - startTime;
                remainingTime -= elapsedTime;
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ", interrupted due to " + e.getMessage());
            throw new RuntimeException(e);
        }

        int item = buffer.pollFirst();
        System.out.println(Thread.currentThread().getName() + ": consumed item: " + item);
        notifyAll();
    }

    public synchronized void produce(int item) {
        try {
            while (buffer.size() == CAPACITY) {
                System.out.println("Buffer is full .... Waiting");
                wait();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + ", interrupted due to " + e.getMessage());

        }

        buffer.addLast(item);
        notifyAll();
    }
}
