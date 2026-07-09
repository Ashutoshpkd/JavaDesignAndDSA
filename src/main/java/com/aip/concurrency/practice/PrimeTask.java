package com.aip.concurrency.practice;

import java.util.concurrent.atomic.AtomicLong;

public class PrimeTask implements Runnable {

    private long start;
    private long end;
    private AtomicLong numberOfPrimes;

    public PrimeTask(long start, long end, AtomicLong numberOfPrimes) {
        this.end = end;
        this.start = start;
        this.numberOfPrimes = numberOfPrimes;
    }

    @Override
    public void run() {
        long millis = System.currentTimeMillis();
        long numPrimes = 0;

        for (long num=start; num<=end; num++) {
            if (isPrime(num)) {
                numPrimes++;
            }
        }

        numberOfPrimes.getAndAdd(numPrimes);
        long elapsedMillis = System.currentTimeMillis() - millis;

        System.out.println("[" + Thread.currentThread().getName() + "] found [" + numPrimes + "]" +
                " primes for the range [" + start + " - " + end + "]. Thread took [" + (elapsedMillis / 1000) + "] seconds.");
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num % 2 == 0) return false;

        for (int i=3; i<=Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }
}
