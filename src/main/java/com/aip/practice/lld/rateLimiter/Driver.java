package com.aip.practice.lld.rateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(
                List.of(
                        tokenBucketConfig("/search", 3, 1),
                        slidingWindowConfig("/checkout", 2, 3000L)
                ),
                tokenBucketConfig(2, 1)
        );

        runRequests(rateLimiter, "Token bucket on /search", "client-1", "/search", 4);
        Thread.sleep(1100);
        sendRequest(rateLimiter, "client-1", "/search", "after token refill");

        runRequests(rateLimiter, "Sliding window on /checkout", "client-1", "/checkout", 3);
        Thread.sleep(3100);
        sendRequest(rateLimiter, "client-1", "/checkout", "after window reset");

        runRequests(rateLimiter, "Default limiter on unconfigured endpoint", "client-1", "/profile", 3);
        runRequests(rateLimiter, "Same endpoint for another client", "client-2", "/profile", 2);
    }

    private static void runRequests(RateLimiter rateLimiter, String scenario, String clientId, String endpoint,
                                    int requestCount) {
        System.out.println();
        System.out.println("Scenario: " + scenario);
        for (int i = 1; i <= requestCount; i++) {
            sendRequest(rateLimiter, clientId, endpoint, "request " + i);
        }
    }

    private static void sendRequest(RateLimiter rateLimiter, String clientId, String endpoint, String label) {
        RateLimitingResponse response = rateLimiter.allow(clientId, endpoint);
        System.out.printf("%-20s client=%s endpoint=%s response=%s%n", label, clientId, endpoint, response);
    }

    private static Map<String, Object> tokenBucketConfig(String endpoint, int capacity, int refillRatePerSecond) {
        Map<String, Object> config = tokenBucketConfig(capacity, refillRatePerSecond);
        config.put("endpoint", endpoint);
        return config;
    }

    private static Map<String, Object> tokenBucketConfig(int capacity, int refillRatePerSecond) {
        Map<String, Object> algoConfig = new HashMap<>();
        algoConfig.put("capacity", capacity);
        algoConfig.put("refillRatePerSecond", refillRatePerSecond);

        Map<String, Object> config = new HashMap<>();
        config.put("algorithm", "TokenBucket");
        config.put("algoConfig", algoConfig);
        return config;
    }

    private static Map<String, Object> slidingWindowConfig(String endpoint, int capacity, long periodInMillis) {
        Map<String, Object> algoConfig = new HashMap<>();
        algoConfig.put("capacity", capacity);
        algoConfig.put("periodInMillis", periodInMillis);

        Map<String, Object> config = new HashMap<>();
        config.put("endpoint", endpoint);
        config.put("algorithm", "SlidingWindow");
        config.put("algoConfig", algoConfig);
        return config;
    }
}
