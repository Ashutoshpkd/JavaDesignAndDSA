package com.aip.design.stacks;

import java.util.Stack;

public class ExpressionConvertor {

    public ExpressionConvertor() {
    }

    public String convertInfixToPostfix(String exp) {
        Stack<Character> st = new Stack<>();
        StringBuilder res = new StringBuilder();

        for (char ch : exp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                res.append(ch);
            } else if (ch == '('){
                st.push(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    res.append(st.pop());
                }
                st.pop();
            } else {
                while (!st.isEmpty() && operatorPrecedence(st.peek()) <= operatorPrecedence(ch)) {
                    res.append(st.pop());
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()) res.append(st.pop());

        return res.toString();
    }

    public String convertInfixToPreFix(String exp) {
        Stack<Character> st = new Stack<>();
        StringBuilder sb = new StringBuilder();
        String reversedExp = reverseInfix(exp);

        System.out.println(reversedExp);

        for (char ch : reversedExp.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                sb.append(ch);
            } else if (ch == ')') {
                while (!st.isEmpty() && st.peek() != '(') {
                    sb.append(st.pop());
                }
                st.pop();
            } else if (ch == '(') {
                st.push(ch);
            } else {
                while (shouldPop(st, ch)) {
                    sb.append(st.pop());
                }
                st.push(ch);
            }
        }

        while (!st.isEmpty()) sb.append(st.pop());
        return sb.reverse().toString();
    }

    private boolean shouldPop(Stack<Character> st, char ch) {
        return !st.isEmpty() && (ch == '^'
                ? (operatorPrecedence(st.peek()) <= operatorPrecedence(ch))
                : (operatorPrecedence(st.peek()) < operatorPrecedence(ch)));
    }

    private String reverseInfix(String exp) {
        char[] expression = exp.toCharArray();
        int l=0, r=expression.length - 1;

        while (l < r) {
                swap(expression, l, r);
                r--;
                l++;
        }

        for (int i=0; i<expression.length; i++) {
            if (expression[i] == ')') {
                expression[i] = '(';
            } else if (expression[i] == '(') {
                expression[i] = ')';
            }
        }

        return String.valueOf(expression);
    }

    private void swap(char[] expression, int l, int r) {
        char ex = expression[l];
        expression[l] = expression[r];
        expression[r] = ex;
    }

    private int operatorPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;

            default:
                return Integer.MAX_VALUE;
        }
    }
}
