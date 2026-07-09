package com.aip.concurrency.primes;

import java.util.concurrent.atomic.AtomicLong;

public class PrimeWorker implements Runnable {

    private final AtomicLong current;
    private final long end;
    private final AtomicLong totalPrimes;

    public PrimeWorker(AtomicLong current, long end, AtomicLong totalPrimes) {
        this.current = current;
        this.end = end;
        this.totalPrimes = totalPrimes;
    }

    @Override
    public void run() {
        countPrime();
    }

    private void countPrime() {
        long localCount = 0;
        long startTime = System.currentTimeMillis();

        while (true) {
            long num = current.getAndIncrement();
            if (num > end) break;

            if (isPrime(num)) {
                localCount++;
            }
        }

        totalPrimes.addAndGet(localCount);

        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("[" + Thread.currentThread().getName() + "] counted [" + localCount +
                "] primes. Took [" + (elapsed / 1000.0) + "] seconds.");
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
