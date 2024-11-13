package com.interview.multithreading.evenOdd;

public class Main {
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();
        PrintEven printEven = new PrintEven(dataStore);
        PrintOdd printOdd = new PrintOdd(dataStore);

        Thread odd = new Thread(printOdd);
        Thread even = new Thread(printEven);

        odd.start();
        even.start();
    }
}

class PrintEven implements Runnable {

    private DataStore dataStore;

    public PrintEven(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void run() {
        while (dataStore.value < DataStore.MAX) {
            synchronized (dataStore) {
                try {
                    if (dataStore.value % 2 == 0) {
                        System.out.println("Even: " + dataStore.value);
                        dataStore.value++;
                        Thread.sleep(500);
                        dataStore.notify();
                    } else {
                        dataStore.wait();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

class PrintOdd implements Runnable {

    private DataStore dataStore;

    public PrintOdd(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public void run() {
        while (dataStore.value < DataStore.MAX) {
            synchronized (dataStore) {
                try {
                    if (dataStore.value % 2 != 0) {
                        System.out.println("Odd: " + dataStore.value);
                        dataStore.value++;
                        Thread.sleep(500);
                        dataStore.notify();
                    } else {
                        dataStore.wait();
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

class DataStore {
    public static final int MAX = 20;

    public int value = 0;
}
