package com.interview.multithreading.producerConsumer;

public class Producer implements Runnable {

    private Queue queue;
    private final int MAX_CAPACITY = 10;

    public Producer(Queue q) {
        this.queue = q;
    }

    @Override
    public void run() {
        queue.setHasMore(true);
        for (int i=0; i<30; i++) {
            queue.produce(i);
        }
        queue.setHasMore(false);
    }
}
