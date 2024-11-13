package com.aip.design.stacks;

import java.util.Stack;

public class Application {

    public void simpleRecursion(int n) {
        if (n > 0) {
            System.out.println("N = " + n);
            simpleRecursion(n - 1);
        }
    }

    public void simpleRecursionUsingStack(int n) {
        Stack<Integer> st = new Stack<>();
        st.push(n);

        while (!st.isEmpty()) {
            int num = st.pop();
            System.out.println("N = " + num);
            if (num - 1 > 0) st.push(num - 1);
        }
    }
}
