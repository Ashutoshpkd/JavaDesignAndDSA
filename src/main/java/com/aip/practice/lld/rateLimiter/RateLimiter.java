package com.aip.practice.lld.rateLimiter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
{
  "endpoint": "/search",
  "algorithm": "TokenBucket",
  "algoConfig": {
    "capacity": 1000,
    "refillRatePerSecond": 10
  }
}
 */

public class RateLimiter {

    private final Map<String, Limiter> limiters;
    private final Limiter defaultLimiter;

    public RateLimiter(List<Map<String, Object>> configs, Map<String, Object> defaultConfig) {
        this.defaultLimiter = LimiterFactory.create(defaultConfig);
        Map<String, Limiter> configuredLimiters = new HashMap<>();
        for (Map<String, Object> config : configs) {
            String endpoint = (String) config.get("endpoint");

            if (endpoint == null) continue;

            Limiter limiter = LimiterFactory.create(config);
            configuredLimiters.put(endpoint, limiter);
        }
        this.limiters = Map.copyOf(configuredLimiters);
    }

    public RateLimitingResponse allow(String clientId, String endpoint) {
        Limiter limiter = limiters.get(endpoint);

        if (limiter == null) {
            limiter = defaultLimiter;
        }

        return limiter.allow(clientId);
    }
}
