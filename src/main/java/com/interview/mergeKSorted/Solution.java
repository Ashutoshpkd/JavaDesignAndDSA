package com.interview.mergeKSorted;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution
{
    //Function to merge k sorted arrays.
    public static ArrayList<Integer> mergeKArrays(int[][] arr, int K)
    {
        // Write your code here.
        ArrayList<Integer> res = new ArrayList<>();
        int[] indexes = new int[K];
        Queue<Pair> minH = new PriorityQueue<>(Comparator.comparingInt(p -> p.val));

        for (int i = 0; i < K; i++) {
            if (indexes[i] < K) {
                minH.add(new Pair(arr[i][indexes[i]], i));
                indexes[i]++;
            }
        }
        while (!minH.isEmpty()) {
            Pair p = minH.poll();
            res.add(p.val);

            if (indexes[p.idx] < K) {
                minH.add(new Pair(arr[p.idx][indexes[p.idx]], p.idx));
                indexes[p.idx]++;
            }
        }
        return res;
}
    private static class Pair {
        int val;
        int idx;

        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }
}
