package com.interview.dp;

public class Driver {
    public static void main(String[] args) {
        PaintHouse ph = new PaintHouse();
        System.out.println(ph.minCost(new int[][] {{14,2,11}, {11,14,5}, {14,3,10}}));
        System.out.println(ph.minCostPractice(new int[][] {{1,2,3}, {4,5,6}, {7,8,9}}));

        MaxSubArrSumWithOneDeletion sumWithOneDeletion = new MaxSubArrSumWithOneDeletion();
        int[] arr = {1, -2, -2, 3};
        System.out.println(sumWithOneDeletion.maximumSum(arr));
        System.out.println(sumWithOneDeletion.maxSumArr(new int[] {1, -2, -2}));

        String s1 = "ASHU";
        String s2 = "ASHU";
        String s3 = new String("ASHU");

        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        Database db = Database.getInstance();
        Database db2 = Database.getInstance();

        System.out.println(db == db2);

        for (Status s : Status.values()) {
            System.out.println(s);
            System.out.println(s.toString());
            System.out.println(s.getStatus());
        }
    }
}
