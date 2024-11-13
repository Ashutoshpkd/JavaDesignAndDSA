package com.interview.heap;

import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        int[][] dataArray = {
                {1, 91},
                {1, 92},
                {2, 93},
                {2, 97},
                {1, 60},
                {2, 77},
                {1, 65},
                {1, 87},
                {1, 100},
                {2, 100},
                {2, 76}
        };
        String s = "      -+42";
        for (char ch : s.toCharArray()) {

            System.out.println(ch + ", code = " + (int) ch + ", " + (ch == ' '));
        }

        System.out.println(HighFive.highFive(dataArray));

//        MaxHeap heap = new MaxHeap();
//        heap.insert(1);
//        heap.insert(2);
//
//        heap.printHeap();
//        System.out.println();
//        heap.insert(3);
//        heap.printHeap();
//        System.out.println();
//        heap.insert(4);
//        heap.printHeap();
//        while (heap.getSize() != 0) {
//            System.out.println(heap.poll());
//        }

        MaxHeap arrHeap = new MaxHeap(new int[] {5,6,10,4,3,9});
        arrHeap.printHeap();
        while (arrHeap.getSize() != 0) {
            System.out.println(arrHeap.poll());
        }

        HeapSort heapSort = new HeapSort();
        heapSort.sort(new int[] {5,6,10,4,3,9});
        int[] p = new int[] {5,6,10,4,3,9};
        heapSort.sortPractice(p);
        System.out.println(Arrays.toString(p));
    }
}
