package com.aip.concurrency.threads;

import java.util.concurrent.Callable;

public class PrimeCallable implements Callable<Long> {

    private final long start;
    private final long end;
    private long totalPrimes;

    public PrimeCallable(long start, long end) {
        this.end = end;
        this.start = start;
    }

    public boolean isPrime(long num) {
        if (num <= 1) return false;
        if (num % 2 == 0) return false;

        for (long i=3; i<=Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }

    @Override
    public Long call() throws Exception {
        findPrimesInGivenRange();
        return totalPrimes;
    }

    private void findPrimesInGivenRange() {
        for (long num=start; num<=end; num++) {
            if (isPrime(num)) totalPrimes++;
        }
    }
}
