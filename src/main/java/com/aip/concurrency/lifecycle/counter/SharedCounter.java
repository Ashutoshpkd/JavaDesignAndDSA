package com.aip.concurrency.lifecycle.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedCounter {
    private int count = 0;
    private AtomicInteger counter = new AtomicInteger(0);


    public void increment() {
        count++;
        counter.getAndIncrement();
        Runtime.getRuntime().availableProcessors();
    }

    public int getCount() {
        return count;
    }

    public int getCounter() {
        return counter.get();
    }
}
