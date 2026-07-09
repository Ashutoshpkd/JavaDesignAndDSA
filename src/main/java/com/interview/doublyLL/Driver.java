package com.interview.doublyLL;

public class Driver {
    public static void main(String[] args) {
        SlidingWindowMaximum sl = new SlidingWindowMaximum();
        Solution solution = new Solution();
//        System.out.println(sl.slidingWindowMaximum(new int[] {1,3,-1,-3,5,3,6,7}, 3));

//        System.out.println(solution.minimumPairRemoval(new int[] {5, 2, 3, 1}));

        // [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
        LFUCache lfu = new LFUCache(2);
        lfu.put(2,1);
        lfu.put(1,1);
        lfu.put(2,3);
        lfu.put(4,1);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(2));
    }
}
