package com.aip.concurrency.lifecycle.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CounterExecutor {

    public static void main(String[] args) throws InterruptedException {
        SharedCounter counter = new SharedCounter();
        ExecutorService service = Executors.newFixedThreadPool(2);

        Runnable counter1 = () -> {
            for (int i=0; i<500; i++) counter.increment();
        };

        Runnable counter2 = () -> {
            for (int i=0; i<500; i++) counter.increment();
        };
//        service.execute(counter1);
//        service.execute(counter2);

        Thread t1 = new Thread(counter1);
        Thread t2 = new Thread(counter2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        service.shutdown();

        boolean isTerminated = service.awaitTermination(10, TimeUnit.SECONDS);

        if (isTerminated) {
            System.out.println("Counter = " + counter.getCount());
            System.out.println("Atomic = " + counter.getCounter());
        } else {
            System.out.println("Terminated forcefully");
        }
    }
}
