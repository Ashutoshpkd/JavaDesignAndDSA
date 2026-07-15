package com.aip.practice.lld.rateLimiter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Driver {
    public static void main(String[] args) throws Exception {
        RateLimiter rateLimiter = new RateLimiter(
                List.of(
                        tokenBucketConfig("/search", 3, 1),
                        slidingWindowConfig("/checkout", 2, 3000L)
                ),
                tokenBucketConfig(2, 1)
        );

//        runRequests(rateLimiter, "Token bucket on /search", "client-1", "/search", 4);
//        Thread.sleep(1100);
//        sendRequest(rateLimiter, "client-1", "/search", "after token refill");
//
//        runRequests(rateLimiter, "Sliding window on /checkout", "client-1", "/checkout", 3);
//        Thread.sleep(3100);
//        sendRequest(rateLimiter, "client-1", "/checkout", "after window reset");
//
//        runRequests(rateLimiter, "Default limiter on unconfigured endpoint", "client-1", "/profile", 3);
//        runRequests(rateLimiter, "Same endpoint for another client", "client-2", "/profile", 2);

//        runConcurrentTokenBucketRequests();
        runConcurrentTokenBucketRequestsWithoutLatch();
        runConcurrentSlidingWindowRequests();
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

    private static void runConcurrentTokenBucketRequests() throws InterruptedException {
        System.out.println();
        System.out.println("Scenario: concurrent token bucket requests");

        RateLimiter rateLimiter = new RateLimiter(
                List.of(tokenBucketConfig("/parallel", 5, 1)),
                tokenBucketConfig(2, 1)
        );

        int requestCount = 20;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(requestCount);
        AtomicInteger allowedCount = new AtomicInteger();
        AtomicInteger rejectedCount = new AtomicInteger();

        for (int i = 0; i < requestCount; i++) {
            executor.submit(() -> {
                try {
                    startSignal.await();
                    RateLimitingResponse response = rateLimiter.allow("same-client", "/parallel");
                    if (response.isAllowed()) {
                        allowedCount.incrementAndGet();
                    } else {
                        rejectedCount.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        startSignal.countDown();
        doneSignal.await();
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.printf("total=%d allowed=%d rejected=%d%n",
                requestCount,
                allowedCount.get(),
                rejectedCount.get());
    }

    private static void runConcurrentTokenBucketRequestsWithoutLatch() throws Exception {
        System.out.println();
        System.out.println("Scenario: concurrent token bucket requests");

        RateLimiter rateLimiter = new RateLimiter(
                List.of(tokenBucketConfig("/parallel", 5, 1)),
                tokenBucketConfig(2, 1)
        );

        int requestCount = 20;
        ExecutorService executor = Executors.newFixedThreadPool(10);

        AtomicInteger allowedCount = new AtomicInteger();
        AtomicInteger rejectedCount = new AtomicInteger();

        List<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < requestCount; i++) {
            futures.add(executor.submit(() -> {
                RateLimitingResponse response =
                        rateLimiter.allow("same-client", "/parallel");

                if (response.isAllowed()) {
                    allowedCount.incrementAndGet();
                } else {
                    rejectedCount.incrementAndGet();
                }
            }));
        }

        // Wait for all tasks to complete
        for (Future<?> future : futures) {
            future.get();
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        System.out.printf("total=%d allowed=%d rejected=%d%n",
                requestCount,
                allowedCount.get(),
                rejectedCount.get());
    }

    private static void runConcurrentSlidingWindowRequests() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(20);
        AtomicInteger accepted = new AtomicInteger(0);
        AtomicInteger rejected = new AtomicInteger(0);

        RateLimiter rateLimiter = new RateLimiter(
                List.of(
                        tokenBucketConfig("/search", 3, 1),
                        slidingWindowConfig("/parallel-window", 5, 1000L)
                ),
                tokenBucketConfig(2, 1)
        );

        for (int i=0; i<20; i++) {
            executor.submit(() -> {
                try {
                    startSignal.await();
                    RateLimitingResponse response = rateLimiter.allow("same-user", "/parallel-window");

                    if (response.isAllowed()) {
                        accepted.incrementAndGet();
                    } else {
                        rejected.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                } finally {
                    doneSignal.countDown();
                }
            });
        }
        startSignal.countDown();
        doneSignal.await();
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        System.out.printf("total=%d allowed=%d rejected=%d%n",
                20,
                accepted.get(),
                rejected.get());
    }
}
