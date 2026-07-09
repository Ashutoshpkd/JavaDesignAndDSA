package com.aip.concurrency.threads;

import java.util.concurrent.atomic.AtomicLong;

public class PrimeWorker implements Runnable {

    private AtomicLong currentNumber;
    private final long range;
    private AtomicLong totalPrimes;

    public PrimeWorker(long range, AtomicLong currentNumber, AtomicLong totalPrimes) {
        this.range = range;
        this.currentNumber = currentNumber;
        this.totalPrimes = totalPrimes;
    }

    @Override
    public void run() {
        countPrimes();
    }

    private void countPrimes() {
        while (true) {
            long currNum = currentNumber.getAndIncrement();

            if (currNum > range) break;
            if (isPrime(currNum)) totalPrimes.getAndIncrement();
        }
    }

    private boolean isPrime(long num) {
        if (num % 2 == 0) return false;
        long sqrt = (long) Math.sqrt(num);

        for (int i=3; i<=sqrt; i += 2) if (num % i == 0) return false;

        return true;
    }
}
