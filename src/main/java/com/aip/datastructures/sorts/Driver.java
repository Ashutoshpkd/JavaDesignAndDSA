package com.aip.datastructures.sorts;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        Sort quickSort = new QuickSort();
        System.out.println(Arrays.toString(quickSort.sort(new int[] {5,4,3,0,1, 1, 1, 1, 0, 2, 18, 4, 7, 8, 9, 9, 10})));
    }
}
