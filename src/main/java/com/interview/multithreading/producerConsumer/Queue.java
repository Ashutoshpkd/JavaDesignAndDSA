package com.interview.multithreading.producerConsumer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Queue {

    private boolean hasMore;
    private Deque<Integer> dataStore;
    private final int MAX_CAPACITY = 10;

    public Queue() {
        this.hasMore = false;
        this.dataStore = new LinkedList<>();
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public void produce(int data) {
        synchronized (this) {
            try {
                if (dataStore.size() > MAX_CAPACITY) {
                    this.wait();
                    Thread.sleep(1000);
                }
                dataStore.add(data);
                System.out.println("Data produced: " + data);
                this.notify();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void consume() {
        synchronized (this) {
            try {
                if (this.dataStore.size() == 0) {
                    this.wait();
                }
                Integer data = dataStore.pollFirst();
                System.out.println("Consumed data: " + data);
                this.notify();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int size() {
        return this.dataStore.size();
    }
}
