package com.aip.design.minstack;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> values;
    private Stack<Integer> minStack;
    public MinStack() {
        this.minStack = new Stack<>();
        this.values = new Stack<>();
    }

    public void push(int val) {
        values.push(val);
        int minVal = val;

        if (!minStack.isEmpty() && minStack.peek() < minVal) {
            minVal = minStack.peek();
        }
        minStack.push(minVal);
    }

    public void pop() {
        values.pop();
        minStack.pop();
    }

    public int top() {
        return values.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
