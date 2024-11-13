package com.interview.arrays;

import java.util.*;

public class ElementsWithGivenSum {

    public List<List<Integer>> pairsWithGivenSum(int[] nums, int k) {
        Map<Integer, Pair> elemInfo = new HashMap<>();
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i=0; i<nums.length; i++) {
            if (elemInfo.containsKey(k - nums[i])) {
                Pair p = elemInfo.get(k - nums[i]);
                int cnt = p.freq;

                while (cnt > 0) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(nums[i]);
                    pair.add(p.elem);
                    pairs.add(pair);
                    cnt--;
                }
            }
            Pair prevPair = elemInfo.getOrDefault(nums[i], new Pair(0, nums[i]));
            prevPair.freq += 1;
            elemInfo.put(nums[i], prevPair);
        }
        System.out.println(elemInfo);

        return pairs;
    }

    public List<List<Integer>> pairsWithGivenSumSimple(int[] nums, int k) {
        Map<Integer, Integer> elemInfo = new HashMap<>();
        List<List<Integer>> pairs = new ArrayList<>();

        for (int n : nums) {
            if (elemInfo.containsKey(k - n)) {
                int counter = elemInfo.get(k - n);

                while (counter > 0) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(k - n);
                    pair.add(n);
                    pairs.add(pair);
                    counter--;
                }
            }
            elemInfo.put(n, elemInfo.getOrDefault(n, 0) + 1);
        }

        return pairs;
    }

    public List<List<Integer>> tripletsWithGivenSum(int[] nums, int k) {
        List<List<Integer>> triplets = new ArrayList<>();
        Arrays.sort(nums);

        for (int i=0; i<nums.length - 2; i++) {
            int first = nums[i];
            int second = nums[i + 1];

            for (int j=i+2; j<nums.length; j++) {
                if (first + second + nums[j] == k) {
                    List<Integer> triplet = List.of(first, second, nums[j]);
                    triplets.add(triplet);
                }
            }
        }

        return triplets;
    }

    public static List<List<Integer>> findTripletsWithSum(int[] array, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(array);

        for (int i = 0; i < array.length - 2; i++) {
            int left = i + 1;
            int right = array.length - 1;

            while (left < right) {
                int currentSum = array[i] + array[left] + array[right];

                if (currentSum == targetSum) {
                    List<Integer> triplet = Arrays.asList(array[i], array[left], array[right]);
                    result.add(triplet);
                    left++;
                    right--;
                } else if (currentSum < targetSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public int minimumCostPath(int[][] grid)
    {
        // Code here
        return minHelper(grid, 0, 0);
    }

    private int minHelper(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length) return Integer.MAX_VALUE;

        if (r == grid.length - 1 && c == grid[0].length - 1) return grid[r][c];

        int rowMin = grid[r][c] + Math.min(minHelper(grid, r + 1, c), minHelper(grid, r - 1, c));
        int colMin = grid[r][c] + Math.min(minHelper(grid, r, c + 1), minHelper(grid, r, c - 1));

        return Math.min(rowMin, colMin);
    }

    private static class Pair {
        int freq;
        int elem;

        public Pair(int freq, int elem) {
            this.freq = freq;
            this.elem = elem;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null) return false;
            if (getClass() != o.getClass()) return false;

            Pair p = (Pair) o;

            return this.elem == p.elem;
        }

        public int hashCode() {
            return String.valueOf(elem).hashCode();
        }

        public String toString() {
            return "[Freq = " + freq + ", Elem = " + elem + "]";
        }
    }
}
