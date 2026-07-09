package com.aip.concurrency.locks.occ;

import java.util.concurrent.locks.StampedLock;

public class BankAccount {

    private static final int MAX_RETRIES = 3;

    private double balance;
    private final StampedLock lock = new StampedLock();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // RETRY-BASED OPTIMISTIC READ
    public double getBalance() {
        for (int i = 0; i < MAX_RETRIES; i++) {
            long stamp = lock.tryOptimisticRead();
            double currentBalance = balance;

            if (lock.validate(stamp)) {
                return currentBalance; // success
            }
            // else retry
        }

        // Fallback to pessimistic read lock
        long stamp = lock.readLock();
        try {
            return balance;
        } finally {
            lock.unlockRead(stamp);
        }
    }

    // WRITE LOCK
    public void deposit(double amount) {
        long stamp = lock.writeLock();
        try {
            balance += amount;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public void withdraw(double amount) {
        long stamp = lock.writeLock();
        try {
            if (balance < amount) {
                throw new IllegalStateException("Insufficient balance");
            }
            balance -= amount;
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}
