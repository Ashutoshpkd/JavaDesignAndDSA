package com.aip.concurrency.primes;

import com.aip.datastructures.graph.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Driver {
    public static long range = 1_000_000_000L;
    public static int numberOfThreads = 10;
    public static long segmentSize = range / numberOfThreads;

    public static void main(String[] args) throws IOException {
        workerThreads();
    }



    public static void workerThreads() {
        long millis = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        AtomicLong startOfRange = new AtomicLong(1);
        AtomicLong numberOfPrimes = new AtomicLong(0);

        for (int i=0; i<numberOfThreads; i++) {
            executor.execute(new PrimeWorker(startOfRange, range, numberOfPrimes));
        }

        executor.shutdown();

        try {
            if (executor.awaitTermination(1, TimeUnit.HOURS)) {
                double elapsed = (System.currentTimeMillis() - millis) / 1000.0;

                System.out.println("\n✅ Total primes found in range [1 - " + range + "] = " + numberOfPrimes.get()
                + ". Took [" + elapsed + "] seconds");
            } else {
                System.out.println("⏱️ Timeout: Some tasks may not have finished.");
            }
        } catch (InterruptedException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public static void batchThreads() {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        AtomicLong totalPrimes = new AtomicLong(0);

        for (int i = 0; i < numberOfThreads; i++) {
            long start = i * segmentSize + 1;
            long end = (i + 1) * segmentSize;
            executor.execute(new PrimeTask(start, end, totalPrimes));
        }

        executor.shutdown();

        try {
            if (executor.awaitTermination(1, TimeUnit.HOURS)) {
                System.out.println("\n✅ Total primes found in range [1 - " + range + "] = " + totalPrimes.get());
            } else {
                System.out.println("⏱️ Timeout: Some tasks may not have finished.");
            }
        } catch (InterruptedException e) {
            System.err.println("❌ Interrupted while waiting for completion.");
            Thread.currentThread().interrupt();
        }
    }
}

