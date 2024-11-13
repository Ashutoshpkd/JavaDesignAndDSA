package com.interview.arrays;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.function.BiConsumer;

public class Driver {
    public static void main(String[] args) {
        ElementsWithGivenSum withGivenSum = new ElementsWithGivenSum();
        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] grid = new int[][] {{9,4,9,9},{6,7,6,4},
                {8,3,3,7},{7,4,9,10}};

        BiConsumer<Integer, Integer> swap = (i, j) -> {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        };

        swap.accept(0, 1);
        System.out.println(Arrays.toString(nums));

//        System.out.println(withGivenSum.pairsWithGivenSum(new int[] {1, 5, 7, 1}, 6));
//        System.out.println(withGivenSum.pairsWithGivenSumSimple(new int[] {1, 5, 7, 1}, 6));
//        System.out.println(withGivenSum.tripletsWithGivenSum(nums, 15));
//        System.out.println(withGivenSum.tripletsWithGivenSum(new int[] { 5, 6, 7, 8, 9, 1, 2, 3, 4}, 15));
//        System.out.println(ElementsWithGivenSum.findTripletsWithSum(new int[] { 5, 6, 7, 8, 9, 1, 2, 3, 4}, 15));
//        System.out.println(withGivenSum.minimumCostPath(grid));
        Deque<Integer> st = new ArrayDeque<>();
        st.isEmpty();

        String str1 = "Ashutosh";
        String str2 = "Ashutosh";

        System.out.println(str1 == str2);
        str1 = "Ashu";
        System.out.println(str1 + ", " + str2);
    }
}
