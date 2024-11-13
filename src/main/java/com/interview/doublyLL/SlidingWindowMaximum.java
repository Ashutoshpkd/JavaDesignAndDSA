package com.interview.doublyLL;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximum {

    public List<Integer> slidingWindowMaximum(int[] arr, int k) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> dq = new ArrayDeque<>();

        for (int idx=0; idx<arr.length; idx++) {
            System.out.println("Index = " + idx + ", k = " + k);
            System.out.println("Before: " + dq);
            while (!dq.isEmpty() && arr[idx] > arr[dq.peekLast()]) dq.pollLast();
            if (dq.size() == k) dq.pollFirst();
            dq.offer(idx);

            if (idx >= k - 1) {
                res.add(arr[dq.peekFirst()]);
            }
            System.out.println("After: " + dq);
            System.out.println("---------Next Iteration-----------");
        }

        return res;
    }
}
