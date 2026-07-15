package com.aip.practice.lld.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TokenBucketLimiter implements Limiter {
    private final int capacity;
    private final int refillRateSeconds;
    private final ConcurrentMap<String, TokenBucket> buckets;

    public TokenBucketLimiter(int capacity, int refillRateSeconds) {
        if (capacity <= 0 || refillRateSeconds <= 0) {
            throw new IllegalArgumentException("Capacity and refill rate must be positive");
        }
        this.capacity = capacity;
        this.refillRateSeconds = refillRateSeconds;
        this.buckets = new ConcurrentHashMap<>();
    }

    @Override
    public RateLimitingResponse allow(String clientId) {
        TokenBucket bucket = buckets.computeIfAbsent(
                clientId,
                id -> new TokenBucket(capacity, System.currentTimeMillis())
        );

        synchronized (bucket) {
            long now = Math.max(System.currentTimeMillis(), bucket.getLastReqMillis());
            long lastReqMillis = bucket.getLastReqMillis();
            long elapsedTime = now - lastReqMillis;
            double capacityToIncrease = (elapsedTime * refillRateSeconds) / 1000.0;

            double newCapacity = Math.min(bucket.getCurrentCapacity() + capacityToIncrease, capacity);
            bucket.setCurrentCapacity(newCapacity);
            bucket.setLastReqMillis(now);

            if (newCapacity >= 1) {
                bucket.setCurrentCapacity(bucket.getCurrentCapacity() - 1);
                return new RateLimitingResponse(true, (int) Math.floor(bucket.getCurrentCapacity()),
                        null);
            }

            double remainingCapacity = 1 - newCapacity;
            long retryAfterMillis = (long) Math.ceil(((long) 1000 * remainingCapacity) / refillRateSeconds);

            return new RateLimitingResponse(false, 0, retryAfterMillis);
        }
    }
}
