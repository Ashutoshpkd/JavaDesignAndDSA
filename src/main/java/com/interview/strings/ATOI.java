package com.interview.strings;

public class ATOI {
    public int myAtoi(String s) {
        String ts = s.trim();

        if (ts.length() == 0) {
            return 0;
        }

        int ans = 0, i = 0;
        boolean neg = s.charAt(0) == '-';
        boolean pos = s.charAt(0) == '+';

        if (neg || pos) i++;

        while (i < ts.length() && Character.isDigit(ts.charAt(i))) {
            int digit = ts.charAt(i) - '0';

            if (ans > Integer.MAX_VALUE / 10
                    || (ans == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return neg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            ans = ans * 10 + digit;
            i++;
        }

        return neg ? -ans : ans;
    }
}
