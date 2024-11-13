package com.aip.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordDict {
    public static void main(String[] args) {
        String[] dict = new String[]  {"to", "banana", "toe", "dogs", "ababcd", "elephant"};
        System.out.println(wordDict(dict, "ot"));
        Arrays.copyOf(dict, dict.length);
    }

    private static List<String> wordDict(String[] dict, String word) {
        char[] wordFreq = new char[26];
        List<String> res = new ArrayList<>();

        for (char ch : word.toCharArray()) wordFreq[ch - 'a']++;

        String wordKey = String.valueOf(wordFreq);

        for (String dictW : dict) {
            char[] dictWordFreq = new char[26];

            for (char ch : dictW.toCharArray()) dictWordFreq[ch - 'a']++;

            if (String.valueOf(dictWordFreq).equals(wordKey)) {
                res.add(dictW);
            }
        }

        return res;
    }
}
