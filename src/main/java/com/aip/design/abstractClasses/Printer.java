package com.aip.design.abstractClasses;

import java.util.HashSet;
import java.util.Set;

public class Printer extends Print{
    private String name;

    public Printer(String name) {
        super(name, "unknown");
    }

    public int lengthOfLongestSubstring(String s) {
        int l=0, r=0, max=0;
        Set<Character> visited = new HashSet<>();
        char c = '-';

        while (r < s.length()) {
            c = s.charAt(r);

            while (l < s.length() && visited.contains(c)) {
                visited.remove(s.charAt(l));
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }

        return max;
    }

    @Override
    public void print() {
        System.out.println(name);
    }
}
