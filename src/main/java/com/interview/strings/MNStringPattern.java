package com.interview.strings;

/**
 * problem statement-
 * ------------------------------------
 * Question:
 Given a Pattern containing only Ns and M’s . N represents ascending and M represents descending , Each character (M or
 N) needs to display sequence of numbers(2 numbers) explaining the ascending or descending order (for ex: 21 -
 >represents descending -> M) . The second character in the pattern takes the last digit from first character and builds the
 sequence and so on..Please look at example section below.
 There could be multiple numbers satisfying the pattern. The goal is to find out the lowest numeric value following the
 pattern.
 Constraints:
 Input can have maximum 8 characters
 Output can have Digits from 1-9 and Digits can’t repeat.
 In case of no possible output or incorrect input value (like blank /null /NON M or N character) please return -1.
 Example Section:
 Input : M
 Output: 21 (2 -> 1 shows descending and possible smallest numeric value. Even 65 or 74 can qualify, but 21 being the
 smallest numeric value is the correct answer)
 Input : MNM
 Output:2143 (M represents descending 2->1 , N represents ascending 1->4 (1 is coming from last character) , M
 represents descending 4->3(4 is coming from last character sequence) -- There would be many number qualifying the
 pattern like 3142 ,8796,6241 etc.. 2143 is the lowest numeric value for this pattern sequence.)
 */
import java.util.PriorityQueue;
import java.util.Queue;


public class MNStringPattern {
    public String findPossibleSmallestNumberMatchingPattern(String pattern) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> pq = new PriorityQueue<>();
        if (!isValidString(pattern)) {
            sb.append(-1);
            return sb.toString();
        }

        for (int i=1; i<=10; i++) pq.add(i);

        int m = 0, idx = 0;

        while (idx < pattern.length()) {
            if (pattern.charAt(idx) == 'M') {
                m = getConcecutiveM(pattern, idx, 'M');
                sb.append(getNum(m + 1, pq));
            } else {
                sb.append(getNum(1, pq));
            }
            idx++;
        }
        sb.append(pq.poll());
        return sb.toString();
    }

    private int getNum(int count, Queue<Integer> pq) {
        Queue<Integer> pq1 = new PriorityQueue<>();
        int temp = 0;
        while (count-- > 0) {
            temp = pq.poll();

            if (count > 0) {
                pq1.add(temp);
            }
        }
        pq.addAll(pq1);
        return temp;
    }

    private int getConcecutiveM(String pattern, int idx, char ch) {
        int mCount = 0;
        while (idx < pattern.length() && pattern.charAt(idx) == ch) {
            mCount++;
            idx++;
        }
        return mCount;
    }

    private boolean isValidString(String pattern) {
        if (pattern == null || pattern.length() == 0 || pattern.length() > 8) {
            return false;
        }
        for (char c : pattern.toCharArray()) {
            if (c != 'N' && c != 'M') return false;
        }

        return true;
    }
}