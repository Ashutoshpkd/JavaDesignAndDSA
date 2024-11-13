package com.aip.datastructures;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.length() == 0 || t.length() > s.length()) return "";

        int need=0, have=0, l=0;
        int resL = Integer.MAX_VALUE;
        String res = "";

        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> countS = new HashMap<>();

        for (Character c : t.toCharArray()) countT.put(c, 1 + countT.getOrDefault(c, 0));
        need = countT.size();

        for (int r=0; r<s.length(); r++) {
            Character c = s.charAt(r);
            countS.put(c, 1 + countS.getOrDefault(c, 0));

            if (countT.containsKey(c) && countT.get(c) == countS.get(c)) {
                have += 1;
            }

            while (have == need) {
                if (resL > (r - l + 1)) {
                    resL = (r - l + 1);
                    res = s.substring(l, r+1);
                }
                Character sc = s.charAt(l);
                countS.put(sc, countS.get(sc) - 1);

                if (countT.containsKey(sc) && countS.get(sc) < countT.get(sc)) {
                    have -= 1;
                }
                l++;
            }
        }

        return resL == Integer.MAX_VALUE ? "" : res;
    }
}