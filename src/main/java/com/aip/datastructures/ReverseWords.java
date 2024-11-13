package com.aip.datastructures;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class ReverseWords {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        char prev = '*';

        for (int i=0; i<s.length(); i++) {
            if (prev != '*' && s.charAt(i) == ' ') {
                prev = '*';
                sb.append(prev);
            } else if (prev == '*' && s.charAt(i) == ' ') {
                continue;
            } else {
                prev = s.charAt(i);
                sb.append(prev);
            }
        }
        String[] words = sb.toString().split("\\*");
        System.out.println(sb.toString());
        System.out.println(Arrays.toString(words));
        int i=0, j= words.length-1;

        while (i < j) {
            String temp = words[i];
            words[i] = words[j];
            words[j] = temp;
            i++;
            j--;
        }
        System.out.println(String.join(" ", words));
        return String.join(" ", words);
    }

    private void swap(int[] nums, int i, int j) {
        System.out.println(Arrays.toString(nums));
        System.out.println("i = " + i + ", j = " + j);
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            System.out.println(i + ", " + j);
            swap(nums, i, j);
            i++;
            j--;
            System.out.println(i + ", " + j);
        }
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;

        System.out.println(k + " = k");
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
}
