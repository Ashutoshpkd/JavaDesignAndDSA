package com.interview.multithreading.practice;

public class PrintNumber {
    private int upperBound;

    public PrintNumber(int upperBound) {
        this.upperBound = upperBound;
    }

    public void printEven() {
        while (this.upperBound > 0) {
            synchronized (this) {
                if (this.upperBound % 2 == 0) {
                    synchronized (this) {
                        if (this.upperBound <= 0) {
                            continue;
                        } else if (this.upperBound % 2 == 0) {
                            System.out.println("Even: " + this.upperBound + ", printed by: " + Thread.currentThread().getName());
                            this.upperBound--;
                            this.sleep(1000);
                            this.notify();
                        } else {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void printOdd() {
        while (this.upperBound > 0) {
            synchronized (this) {
                if (this.upperBound % 2 != 0) {
                    synchronized (this) {
                        if (this.upperBound % 2 != 0) {
                            System.out.println("Odd: " + this.upperBound + ", printed by: " + Thread.currentThread().getName());
                            this.upperBound--;
                            this.sleep(1000);
                            this.notify();
                        } else {
                            try {
                                this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
