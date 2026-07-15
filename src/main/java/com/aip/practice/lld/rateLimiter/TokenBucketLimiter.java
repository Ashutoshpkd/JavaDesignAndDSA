package com.aip.practice.lld.rateLimiter;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketLimiter implements Limiter {
    private final int capacity;
    private final int refillRateSeconds;
    private final Map<String, TokenBucket> buckets;

    public TokenBucketLimiter(int capacity, int refillRateSeconds) {
        this.capacity = capacity;
        this.refillRateSeconds = refillRateSeconds;
        this.buckets = new HashMap<>();
    }

    @Override
    public RateLimitingResponse allow(String clientId) {
        long now = System.currentTimeMillis();
        buckets.putIfAbsent(clientId, new TokenBucket(capacity, now));
        TokenBucket bucket = buckets.get(clientId);

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
