package com.aip.design.queue;

import java.util.Stack;

public class MyQueue {

    {
        System.out.println("Init Block");
    }

    static {
        System.out.println("Static Block");
    }

    private Stack<Integer> s1;
    private Stack<Integer> s2;

    public MyQueue() {
        System.out.println("Constructor");
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void add(Integer value) {
        s1.push(value);
    }

    public int poll() {
        if (s2.isEmpty()) {
            fillS2();
        }
        return s2.pop();
    }

    public int peek() {
        if (s2.isEmpty()) {
            fillS2();
        }
        return s2.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    private void fillS2() {
        while (!s1.isEmpty()) s2.push(s1.pop());
    }
}
