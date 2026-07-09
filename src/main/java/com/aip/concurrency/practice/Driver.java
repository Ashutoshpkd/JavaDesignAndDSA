package com.aip.concurrency.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        long RANGE = 1_000_000_000L;
        int THREADS = 10;
        long SEGMENT = RANGE / THREADS;
        final AtomicLong numberOfPrimesInRange = new AtomicLong(0);

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS);
        long millis = System.currentTimeMillis();

        for (int i=0; i<THREADS; i++) {
            long start = i * SEGMENT + 1;
            long end = i * SEGMENT + SEGMENT;

            executorService.execute(new PrimeTask(start, end, numberOfPrimesInRange));
        }
        executorService.shutdown();

        long elapsedMillis = System.currentTimeMillis() - millis;

        try {
            if (executorService.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.println("[" + Thread.currentThread().getName() + "] found [" + numberOfPrimesInRange.get() + "]" +
                        " primes for the range [" + RANGE + "]. Thread took [" + (elapsedMillis / 1000) + "] seconds.");
            } else {
                System.out.println("⏱️ Timeout: Some tasks may not have finished.");
            }
        } catch (InterruptedException e) {
            System.err.println("❌ Interrupted while waiting for completion.");
            Thread.currentThread().interrupt();
        }
    }
}
