package com.aip.concurrency.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    private static final long TOTAL = 1_00_000_000;
    private static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        fairWorker();
        callableWorker();
    }

    private static void fairWorker() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        AtomicLong currentNumber = new AtomicLong(3L);
        AtomicLong totalPrimes = new AtomicLong(1L);
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i=0; i<THREAD_COUNT; i++) {
            threadPool.execute(new PrimeWorker(TOTAL, currentNumber, totalPrimes));
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("Worker completed execution in [ " + ((System.currentTimeMillis() - startTime) / 1000)
                + " ] seconds");
    }

    private static void callableWorker() throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<Long>> futures = new ArrayList<>();
        List<Long> primes = new ArrayList<>();

        for (int idx=0; idx<THREAD_COUNT; idx++) {
            long chunkSize = (TOTAL / THREAD_COUNT);

            long start =  chunkSize * idx;
            long end = start + chunkSize;

            futures.add(threadPool.submit(new PrimeCallable(start, end)));
        }

        for (Future<Long> future : futures) primes.add(future.get());


        System.out.println("Worker completed execution in [ " + ((System.currentTimeMillis() - startTime) / 1000)
        + " ] seconds");
        threadPool.shutdown();
    }
}
