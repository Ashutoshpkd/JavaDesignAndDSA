package com.aip.design.detectSquares;

public class Driver {
    public static void main(String[] args) {
        DetectSquares ds = new DetectSquares();
//        [[], [[3, 10]], [[11, 2]], [[3, 2]], [[11, 10]], [[14, 8]], [[11, 2]], [[11, 10]]]
        ds.add(new int[] {3, 10});
        ds.add(new int[] {11, 2});
        ds.add(new int[] {3, 2});
        int count = ds.count(new int[] {11, 10});
        System.out.println(count);
    }
}
