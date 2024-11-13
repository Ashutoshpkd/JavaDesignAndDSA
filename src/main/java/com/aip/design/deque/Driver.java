package com.aip.design.deque;

public class Driver {
    public static void main(String[] args) {
        MyDeque deque = new MyDequeArrayImpl();
        deque.offerFirst(1);
        deque.offerLast(2);

        System.out.println(deque.peekFirst());
        System.out.println(deque.peekLast());

        for (int i=1; i<=60; i++) {
            deque.offerLast(i);
        }

        deque.printBucket();
        System.out.println(deque.pollLast());
        System.out.println(deque.peekLast());

        System.out.println("---------------------------------------------");

        MyDeque newDeque = new MyDequeArrayImpl(3);
        newDeque.offerLast(1);
        newDeque.offerLast(2);
        newDeque.offerLast(3);

        System.out.println(newDeque.peekLast());
        System.out.println(newDeque.peekFirst());
    }
}
