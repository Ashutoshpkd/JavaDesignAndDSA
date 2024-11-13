package com.interview.trees;

import java.util.*;

public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        return res;
    }

    static char nonrepeatingCharacter(String s)
    {
        //Your code here
        char firstNonRepeatingChar = '$';
        Map<Character, Integer> freq = new LinkedHashMap<>();

        for (char ch : s.toCharArray()) freq.put(ch, freq.getOrDefault(ch, 0) + 1);

        for (Map.Entry<Character, Integer> e : freq.entrySet()) {
            if (e.getValue() == 1) {
                firstNonRepeatingChar = e.getKey();
                break;
            }
        }

        return firstNonRepeatingChar;
    }
}
