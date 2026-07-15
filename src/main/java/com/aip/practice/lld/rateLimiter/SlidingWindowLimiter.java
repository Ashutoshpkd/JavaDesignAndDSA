package com.aip.practice.lld.rateLimiter;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowLimiter implements Limiter {

    private int capacity;
    private long periodInMillis;
    private Map<String, WindowBucket> buckets;

    public SlidingWindowLimiter(int capacity, long periodInMillis) {
        this.capacity = capacity;
        this.periodInMillis = periodInMillis;
        this.buckets = new HashMap<>();
    }

    @Override
    public RateLimitingResponse allow(String clientId) {
        Long now = System.currentTimeMillis();
        WindowBucket bucket = getOrCreateBucket(clientId);
        bucket.removeExpiredLogs(now);

        if (bucket.getLogs().size() < capacity) {
            bucket.getLogs().offer(now);
            return new RateLimitingResponse(true, capacity - bucket.getLogs().size());
        }
        Long oldest = bucket.getLogs().peek();
        Long retryAfter = periodInMillis - (now - oldest);
        return new RateLimitingResponse(false, 0, retryAfter);
    }

    private WindowBucket getOrCreateBucket(String clientId) {
        this.buckets.putIfAbsent(clientId, new WindowBucket(periodInMillis));
        return this.buckets.get(clientId);
    }
}
