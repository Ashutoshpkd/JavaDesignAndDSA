package com.interview.trees;

public class ArrayTree {
    private static int[] tree = new int[] {11, 9, 13, 8, 10, 12, 14};

    public static void main(String[] args) {
        System.out.println(search(13));
    }

    public static int search(int key) {
       return searchHelper(1, key);
    }

    private static int searchHelper(int rootIdx, int key) {
        if (rootIdx <= 0 || rootIdx > tree.length) return -1;
        if (tree[rootIdx - 1] == key) return rootIdx;

        int leftSearch = -1;

        if (2 * rootIdx <= tree.length) {
            leftSearch = searchHelper(2 * rootIdx, key);
        }

        if (leftSearch != -1) return leftSearch;

        int rightSearch = -1;
        if ((2 * rootIdx) + 1 <= tree.length) {
            rightSearch = searchHelper((2 * rootIdx) + 1, key);
        }
        return rightSearch;
    }
}
