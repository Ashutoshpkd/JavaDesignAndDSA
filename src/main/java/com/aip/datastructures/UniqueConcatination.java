package com.aip.datastructures;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UniqueConcatination {
    public int maxLength(List<String> arr) {
        List<String> sanitizedArr = Set.copyOf(arr)
                .stream()
                .filter(this::allUnique)
                .toList();

        return getMaxConcatinationLength(sanitizedArr, 0, "");
    }

    private int getMaxConcatinationLength(List<String> arr, int idx, String res) {
        if (idx >= arr.size()) return res.length();

        int maxLength = 1;

        maxLength = Math.max(maxLength, getMaxConcatinationLength(arr, idx+1, res));

        if (!containsDuplicates(res, arr.get(idx))) {
            maxLength = Math.max(maxLength, getMaxConcatinationLength(arr, idx+1, res + arr.get(idx)));
        }

        return maxLength;
    }

    private boolean containsDuplicates(String s1, String s2) {
        Set<String> set1 = s1.chars().boxed().map(Character::toString).collect(Collectors.toSet());
        Set<String> set2 = s2.chars().boxed().map(Character::toString).collect(Collectors.toSet());

        return set1.stream()
                .anyMatch(set2::contains);
    }

    private boolean allUnique(String s) {
        int[] chars = new int[26];

        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
            if (chars[c - 'a'] > 1) return false;
        }
        return true;
    }
}
