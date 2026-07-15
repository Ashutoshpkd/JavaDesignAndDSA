package com.aip.practice.lld.rateLimiter;

public class RateLimitingResponse {
    private final boolean isAllowed;
    private final int requestRemaining;
    private final Long retryMillis;

    public RateLimitingResponse(boolean isAllowed, int requestRemaining) {
        this(isAllowed, requestRemaining, null);
    }

    public RateLimitingResponse(boolean isAllowed, int requestRemaining, Long retryMillis) {
        this.isAllowed = isAllowed;
        this.requestRemaining = requestRemaining;
        this.retryMillis = retryMillis;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public int getRequestRemaining() {
        return requestRemaining;
    }

    public Long getRetryMillis() {
        return retryMillis;
    }

    @Override
    public String toString() {
        return "RateLimitingResponse{" +
                "isAllowed=" + isAllowed() +
                ", requestRemaining=" + getRequestRemaining() +
                ", retryMillis=" + getRetryMillis() +
                '}';
    }
}
