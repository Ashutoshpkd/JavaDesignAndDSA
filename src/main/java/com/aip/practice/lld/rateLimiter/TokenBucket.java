package com.aip.practice.lld.rateLimiter;

public class TokenBucket {
    private double currentCapacity;
    private long lastReqMillis;

    public TokenBucket(double currentCapacity, long lastReqMillis) {
        this.currentCapacity = currentCapacity;
        this.lastReqMillis = lastReqMillis;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public long getLastReqMillis() {
        return lastReqMillis;
    }

    public void setLastReqMillis(long lastReqMillis) {
        this.lastReqMillis = lastReqMillis;
    }

    public void setCurrentCapacity(double capacity) {
        this.currentCapacity = capacity;
    }
}
