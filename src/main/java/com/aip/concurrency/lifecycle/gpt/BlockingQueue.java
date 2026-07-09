package com.aip.concurrency.lifecycle.gpt;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    // PRODUCER
    public synchronized void produce(int item) throws InterruptedException {

        // If queue is full → WAIT
        while (queue.size() == capacity) {
            System.out.println(Thread.currentThread().getName() +
                    " waiting: Queue is FULL");
            wait();   // releases lock and waits
        }

        queue.add(item);
        System.out.println(Thread.currentThread().getName() +
                " produced: " + item);

        // Notify waiting consumers
        notifyAll();
    }

    // CONSUMER
    public synchronized int consume() throws InterruptedException {

        // If queue is empty → WAIT
        while (queue.isEmpty()) {
            System.out.println(Thread.currentThread().getName() +
                    " waiting: Queue is EMPTY");
            wait();   // releases lock and waits
        }

        int item = queue.poll();
        System.out.println(Thread.currentThread().getName() +
                " consumed: " + item);

        // Notify waiting producers
        notifyAll();

        return item;
    }
}
