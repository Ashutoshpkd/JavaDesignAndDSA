package com.aip.math;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        RotateImage ri = new RotateImage();
        int[][] img = new int[][] { {5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16} };
        ri.rotate(img);
        System.out.println("End!");
    }
}
