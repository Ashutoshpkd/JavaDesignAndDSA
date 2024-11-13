package com.aip.design.stacks;

import java.util.Stack;

public class QueueUsingStack {
    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public QueueUsingStack() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void add(int val) {
        s1.push(val);
    }

    public int peek() {
        if (s1.isEmpty() && s2.isEmpty()) throw new RuntimeException("Empty Queue");
        if (s2.isEmpty()) fillS2();

        return s2.peek();
    }

    public int poll() {
        if (s1.isEmpty() && s2.isEmpty()) throw new RuntimeException("Empty Queue");
        if (s2.isEmpty()) fillS2();
        return s2.pop();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    private void fillS2() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
    }
}
