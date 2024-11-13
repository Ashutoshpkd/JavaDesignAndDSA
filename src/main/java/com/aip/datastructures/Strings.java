package com.aip.datastructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Strings {
    public static void main(String[] args) {

    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();

        for (String s : strs) {
            char[] chars = new char[26];

            for (char c : s.toCharArray()) {
                chars[c - 'a']++;
            }

            String key = String.valueOf(chars);

            if (!anagrams.containsKey(key)) {
                List<String> group = new ArrayList<>();
                group.add(s);
                anagrams.put(key, group);
            } else {
                anagrams.get(key).add(s);
            }
        }

        return anagrams.values().stream().toList();
    }
}
