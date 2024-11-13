package com.interview.doublyLL;

public class Driver {
    public static void main(String[] args) {
        SlidingWindowMaximum sl = new SlidingWindowMaximum();
        System.out.println(sl.slidingWindowMaximum(new int[] {1,3,-1,-3,5,3,6,7}, 3));
    }
}
