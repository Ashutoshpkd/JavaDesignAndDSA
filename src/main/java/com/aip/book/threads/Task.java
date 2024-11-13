package com.aip.book.threads;

import java.util.ArrayList;
import java.util.List;

public class Task implements Runnable {

    private int count = 0;
    private List<Integer> taskList;

    {
        this.taskList = new ArrayList<>();
    }

    public int getCount() {
        return this.count;
    }

    private synchronized void incrementCount() {
        for (int i=0; i<50; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            count++;
        }
    }

    @Override
    public void run() {
        incrementCount();
    }
}
