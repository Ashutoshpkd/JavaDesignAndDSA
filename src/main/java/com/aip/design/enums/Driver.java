package com.aip.design.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Driver {
    public static void main(String[] args) {
        System.out.println(Names.LEVI.ordinal());
        System.out.println(Names.ERWIN.getId());
        Names name = Names.valueOf("EREN");
        System.out.println(name);
        char c = '(';

        switch (c) {
            case '(':
                System.out.println(c);
                break;

            case '*':
        }
    }

    public static List<Integer> partitionLabels(String s) {
        int end=0, size=0, sum=0;
        int currIdx = 0;
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> pos = new HashMap<>();

        for (int i=0; i<s.length(); i++) pos.put(s.charAt(i), i);

        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            currIdx = pos.get(c);
            size++;

            if (currIdx > end) {
                end = currIdx;
            }
            if (end == i) {
                res.add(size);
                sum += size;
                size = 0;
                continue;
            }

            if (end == s.length() - 1) {
                res.add(s.length() - sum);
                break;
            }
        }

        return res;
    }
}
