package com.aip.design.stacks;
import java.util.Queue;

import java.util.ArrayDeque;

public class StackUsingQueue {
    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public StackUsingQueue() {
        this.q1 = new ArrayDeque<>();
        this.q2 = new ArrayDeque<>();
    }

    public boolean push(int val) {
        q2.add(val);

        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }

        Queue<Integer> refQ = q1;
        q1 = q2;
        q2 = refQ;

        return true;
    }

    public boolean isEmpty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public int top() {
        if (q1.isEmpty()) throw new RuntimeException("Empty stack");
        return q1.peek();
    }

    public int pop() {
        if (q1.isEmpty()) throw new RuntimeException("Empty stack");
        return q1.poll();
    }
}
