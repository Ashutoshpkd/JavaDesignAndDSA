package com.aip.concurrency.locks.occ;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        Runnable reader = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(
                    Thread.currentThread().getName() + 
                    " balance: " + account.getBalance()
                );
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Runnable writer = () -> {
            account.deposit(500);
            System.out.println("Deposit done");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.deposit(500);
            System.out.println("Deposit done");
        };

        new Thread(reader, "Reader-1").start();
        new Thread(reader, "Reader-2").start();
        new Thread(writer, "Writer").start();
    }
}
