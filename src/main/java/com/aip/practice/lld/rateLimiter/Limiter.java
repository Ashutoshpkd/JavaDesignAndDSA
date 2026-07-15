package com.aip.practice.lld.rateLimiter;

public interface Limiter {

    RateLimitingResponse allow(String clientId);
}
