package com.aip.concurrency.lifecycle.gpt;

public class ProducerConsumerDemo {

    public static void main(String[] args) {

        BlockingQueue queue = new BlockingQueue(3);

        // Producer Thread
        Runnable producer = () -> {
            int value = 0;
            while (true) {
                try {
                    queue.produce(value++);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Consumer Thread
        Runnable consumer = () -> {
            while (true) {
                try {
                    queue.consume();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer, "Producer-1").start();
        new Thread(producer, "Producer-2").start();
        new Thread(consumer, "Consumer-1").start();
        new Thread(consumer, "Consumer-2").start();
    }
}
