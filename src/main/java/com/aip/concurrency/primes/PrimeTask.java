package com.aip.concurrency.primes;

import java.util.concurrent.atomic.AtomicLong;

public class PrimeTask implements Runnable {

    private final long start;
    private final long end;
    private final AtomicLong totalCount;

    public PrimeTask(long start, long end, AtomicLong totalCount) {
        this.start = start;
        this.end = end;
        this.totalCount = totalCount;
    }

    @Override
    public void run() {
        long localCount = 0;
        long startTime = System.currentTimeMillis();
        

        for (long num = start; num <= end; num++) {
            if (isPrime(num)) {
                localCount++;
            }
        }

        totalCount.addAndGet(localCount);

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Thread [" + Thread.currentThread().getName() +
                "] counted [" + localCount + "] primes in range [" + start + " - " + end +
                "]. Took [" + (elapsed / 1000.0) + "] seconds.");
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
