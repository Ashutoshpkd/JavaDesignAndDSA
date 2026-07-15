package com.aip.practice.lld.rateLimiter;

import java.util.ArrayDeque;
import java.util.Queue;

public class WindowBucket {
    private final long periodMs;
    private Queue<Long> logs;

    public WindowBucket(long periodMs) {
        this.periodMs = periodMs;
        this.logs = new ArrayDeque<>();
    }

    public long getPeriodMs() {
        return periodMs;
    }

    public void removeExpiredLogs(Long time) {
         while (!logs.isEmpty() && time - logs.peek() > periodMs) logs.poll();
    }

    public Queue<Long> getLogs() {
        return logs;
    }
}
