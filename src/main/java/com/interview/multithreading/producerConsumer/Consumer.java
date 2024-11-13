package com.interview.multithreading.producerConsumer;

public class Consumer implements Runnable {

    private Queue queue;

    public Consumer(Queue q) {
        this.queue = q;
    }

    @Override
    public void run() {
        while (queue.size() > 0 || queue.isHasMore()) {
            queue.consume();
        }
    }
}
