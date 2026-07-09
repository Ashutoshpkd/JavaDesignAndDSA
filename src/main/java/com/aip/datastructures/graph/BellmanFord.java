package com.aip.datastructures.graph;

import java.util.Arrays;
import java.util.List;

public class BellmanFord {

    public int getShortestPath(List<NodeInfo> nodeInfoList, int n, int src, int dest) {
        int[] minDist = new int[n];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        minDist[src] = 0;

        for (int i=1; i<n; i++) {
            for (NodeInfo nodeInfo : nodeInfoList) {
                int u = nodeInfo.e1;
                int v = nodeInfo.e2;
                int w = nodeInfo.w;

                if (minDist[u] != Integer.MAX_VALUE && minDist[u] + w < minDist[v]) {
                    minDist[v] = minDist[u] + w;
                }
            }
        }

        if (isNegativeCyclePresent(nodeInfoList, minDist)) {
            throw new RuntimeException("Shortest path cannot be determined");
        }

        return minDist[dest];
    }

    private boolean isNegativeCyclePresent(List<NodeInfo> nodeInfoList, int[] minDist) {
        boolean isNegativeCycleDetected = false;
        for (NodeInfo nodeInfo : nodeInfoList) {
            int u = nodeInfo.e1;
            int v = nodeInfo.e2;
            int w = nodeInfo.w;

            if (minDist[u] != Integer.MAX_VALUE && minDist[u] + w < minDist[v]) {
                isNegativeCycleDetected = true;
                break;
            }
        }

        return isNegativeCycleDetected;
    }
}

class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int initScore = 0, cpAge = 0;
        int res = Integer.MIN_VALUE;

        for (int i=0; i<scores.length; i++) {
            initScore = scores[i];
            cpAge = ages[i];
            int currMax = getMaxScore(scores, ages, initScore, cpAge, i);
            res = Math.max(res, currMax);
        }

        return res;
    }

    private int getMaxScore(int[] scores, int[] ages, int score, int age, int idx) {
        int maxScore = score;

        for (int i=0; i<scores.length; i++) {
            if (i == idx) continue;

            if (age != ages[i]) {
                if (age > ages[i] && score < scores[i]) continue;

                if (age < ages[i] && score > scores[i]) continue;
            }

            maxScore += scores[i];
        }

        return maxScore;
    }
}
