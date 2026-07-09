package com.aip.datastructures.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StoneGame {
    public int lastStoneWeightII(int[] stones) {
        Set<Integer> visited = new HashSet<>();
        int max = Arrays.stream(stones).max().orElse(0);
        Integer[][] cache = new Integer[stones.length][max + 1];
        return dfs(stones, 0, 0, visited, cache);
    }

    private int dfs(int[] stones, int idx, int stWt, Set<Integer> visited, Integer[][] cache) {
        if (idx >= stones.length || visited.size() == stones.length) return stWt;
        if (cache[idx][stWt] != null) return cache[idx][stWt];

        int minWt = Integer.MAX_VALUE;
        for (int i=0; i<stones.length; i++) {
            if (visited.contains(i)) continue;
            visited.add(i);
            minWt = Math.min(minWt, dfs(stones, idx + 1, Math.abs(stWt - stones[i]), visited, cache));
            visited.remove(i);
        }

        return minWt;
    }
}
