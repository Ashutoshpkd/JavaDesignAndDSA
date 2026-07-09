package com.aip.datastructures.dp;

import java.util.HashMap;
import java.util.Map;

public class DP {

    private static final int MODULO = 1000000007;

    public long ways(int num) {
        Map<String, Long> cache = new HashMap<>();
        return dfs(num, 0, 0, cache);
    }

    private long dfs(int nums, int idx, int sum, Map<String, Long> cache) {
        String key = idx + "," + sum;
        if (idx >= nums && sum % 2 != 0) return 1;
        if (idx >= nums) return 0;
        if (cache.containsKey(key)) return cache.get(key);

        long ways = 0;
        ways += (dfs(nums, idx + 1, sum + 1, cache) % MODULO)
                + (dfs(nums, idx + 1, sum + 2, cache) % MODULO);
        cache.put(key, ways);
        return ways;
    }
}
