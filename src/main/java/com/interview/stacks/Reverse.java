package com.interview.stacks;

import java.util.Stack;

public class Reverse {
    public static void main(String[] args) {
        String[] tests = {"(skeeg(for)skeeg)", "((ng)ipm(ca))"};
        for (String test : tests) {
            System.out.println(reverse(test));
        }
    }

    public static String reverse(String s) {
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                while (st.peek() != '(') res.append(st.pop());
                st.pop();
                for (char revC : res.toString().toCharArray()) st.push(revC);
                res = new StringBuilder();
            } else {
                st.push(ch);
            }
        }

        while (!st.isEmpty()) {
            res.append(st.pop());
        }
        return res.reverse().toString();
    }
}
