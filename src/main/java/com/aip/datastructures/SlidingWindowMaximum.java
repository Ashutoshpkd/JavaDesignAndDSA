package com.aip.datastructures;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        int idx = 0;

        for (int i=0; i<nums.length; i++) {
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) q.pollFirst();
            while (!q.isEmpty() && nums[i] > nums[q.peekLast()]) q.pollLast();
            q.offer(i);

            if (i >= k - 1) {
                res[idx++] = nums[q.peekFirst()];
            }
        }

        return res;
    }
}
