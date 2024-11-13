package com.aip.design.stacks;

public class Driver {
    public static void main(String[] args) {
        StackUsingQueue st = new StackUsingQueue();
        ExpressionConvertor toPostfix = new ExpressionConvertor();
        Application application = new Application();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);

        System.out.println(st.pop());
        System.out.println(st.pop());
        st.push(5);
        st.push(4);
        System.out.println(st.pop());
        System.out.println(st.pop());

        System.out.println(st.top());
        System.out.println(st.top());
        System.out.println("-------------------------------------------");


        QueueUsingStack q = new QueueUsingStack();
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q.peek());
        System.out.println(q.poll());
        q.add(4);
        System.out.println(q.peek());

        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }

        System.out.println("-------------------------------------------");
        System.out.println("Infix to Postfix: " + toPostfix.convertInfixToPostfix("(p+q)*(m-n)"));
        System.out.println("Infix to Postfix: " + toPostfix.convertInfixToPostfix("((A+B)^C)-((D*E)/F)"));

        System.out.println("-------------------------------------------");
        System.out.println("Infix to Prefix: " + toPostfix.convertInfixToPreFix("((A+B)^C)-((D*E)/F)"));

        System.out.println("-------------------------------------------");
        application.simpleRecursion(3);
        application.simpleRecursionUsingStack(3);
    }
}
