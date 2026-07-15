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

    private Map<String, Limiter> limiters;
    private Limiter defaultLimiter;

    public RateLimiter(List<Map<String, Object>> configs, Map<String, Object> defaultConfig) {
        this.limiters = new HashMap<>();
        this.defaultLimiter = LimiterFactory.create(defaultConfig);
        for (Map<String, Object> config : configs) {
            String endpoint = (String) config.get("endpoint");

            if (endpoint == null) continue;

            Limiter limiter = LimiterFactory.create(config);
            this.limiters.put(endpoint, limiter);
        }
    }

    public RateLimitingResponse allow(String cliendId, String endpoint) {
        Limiter limiter = limiters.get(endpoint);

        if (limiter == null) {
            limiter = defaultLimiter;
        }

        return limiter.allow(cliendId);
    }
}
