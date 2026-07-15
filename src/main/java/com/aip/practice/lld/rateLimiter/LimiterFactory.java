package com.aip.practice.lld.rateLimiter;

import java.util.Map;

public class LimiterFactory {

    @SuppressWarnings("unchecked")
    public static Limiter create(Map<String, Object> config) {
        String algorithm = (String) config.get("algorithm");
        Map<String, Object> algoConfig = (Map<String, Object>) config.get("algoConfig");

        if (algorithm == null || algoConfig == null) {
            throw new IllegalArgumentException("Invalid config");
        }

        switch (algorithm) {
            case "TokenBucket":
                int refillRate = ((Number) algoConfig.get("refillRatePerSecond")).intValue();
                int tokens = ((Number) algoConfig.get("capacity")).intValue();

                return new TokenBucketLimiter(tokens, refillRate);

            case "SlidingWindow":
                int capacity = ((Number) algoConfig.get("capacity")).intValue();
                long periodInMillis = ((Number) algoConfig.get("periodInMillis")).longValue();

                return new SlidingWindowLimiter(capacity, periodInMillis);

            default:
                throw new IllegalArgumentException("Invalid algorithm");
        }
    }
}
