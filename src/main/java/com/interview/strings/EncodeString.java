package com.interview.strings;

public class EncodeString {
    public static String compress(String str){

        int i = 0;
        int n = str.length();
        String ans = "";
        while(n > 0) {
            if(n % 2 == 0 && str.substring(i, n/2).equals(str.substring(n/2, n))){
                ans = compress(str.substring(i, n/2)) + "*" + ans;
                break;
            } else {
                ans = str.substring(n-1, n) + ans;
                n--;
            }
        }

        return ans;
    }
}
