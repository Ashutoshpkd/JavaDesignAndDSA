package com.interview.heap;

import java.util.*;

/*
* Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents one score from a student with IDi, calculate each student’s top five average.
* Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. Sortss result by IDj in increasing order.
* A student’s top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.
* Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
* Output: [[1,87],[2,88]]
*
*
*
*
*
*  */
public class HighFive {
    public static List<List<Integer>> highFive(int[][] data) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Queue<Integer>> topFive = new TreeMap<>();

        for (int i=0; i<data.length; i++) {
            int[] subData = data[i];

            if (topFive.containsKey(subData[0])) {
                if (topFive.get(subData[0]).size() < 5) {
                    topFive.get(subData[0]).add(subData[1]);
                } else {
                    if (topFive.get(subData[0]).peek() < subData[1]) {
                        topFive.get(subData[0]).poll();
                        topFive.get(subData[0]).add(subData[1]);
                    }
                }
            } else {
                Queue<Integer> pq = new PriorityQueue<>();
                pq.add(subData[1]);
                topFive.put(subData[0], pq);
            }
        }

        topFive.forEach((key, value) -> {
            List<Integer> d = new ArrayList<>();
            d.add(key);
            int average = (int) Math.floor(value.stream().mapToDouble(i -> i).average().orElse(0));
            d.add(average);
            res.add(d);
        });

        return res;
    }

}
