package com.aip.concurrency.lifecycle.queue;

public class SharedQueueExecutor {

    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue(5);

        Thread procuderOne = new Thread(() -> {
            for (int i=0; i<20; i++) {
                int randomSleepMs = (int) (3000 * Math.random());
                sharedQueue.produce(i);
                try {
                    Thread.sleep(randomSleepMs);
                } catch (InterruptedException e) {
                    System.out.println("Producer-1: Interrupted due to " + e.getMessage());
                }
            }
        });

        Thread procuderTwo = new Thread(() -> {
            for (int i=30; i<50; i++) {
                int randomSleepMs = (int) (3000 * Math.random());
                sharedQueue.produce(i);
                try {
                    Thread.sleep(randomSleepMs);
                } catch (InterruptedException e) {
                    System.out.println("Producer-2: Interrupted due to " + e.getMessage());
                }
            }
        });

        Thread consumerOne = new Thread(() -> {
            try {
                while (true) {
                    int randomSleepMs = (int) (3000 * Math.random());
                    sharedQueue.consume();
                    Thread.sleep(randomSleepMs);
                }
            } catch (InterruptedException | RuntimeException e) {
                System.out.println("Consumer-1: Interrupted due to " + e.getMessage());
            }
        });

        Thread consumerTwo = new Thread(() -> {
            try {
                while (true) {
                    int randomSleepMs = (int) (3000 * Math.random());
                    sharedQueue.consume();
                    Thread.sleep(randomSleepMs);
                }
            } catch (InterruptedException | RuntimeException e) {
                System.out.println("Consumer-2: Interrupted due to " + e.getMessage());
            }
        });

        procuderOne.start();
        consumerOne.start();
        procuderTwo.start();
        consumerTwo.start();

    }
}
