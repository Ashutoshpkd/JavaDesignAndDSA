package com.aip.design.setAllInO1;

public class Driver {
    public static void main(String[] args) {
        DS ds = new DS(100);

        Integer res;

        res = ds.get(3);
        System.out.println(res);

        ds.set(3, 10);

        res = ds.get(3);
        System.out.println(res);

        ds.setAll(6);

        res = ds.get(3);
        System.out.println(res);

        res = ds.get(15);
        System.out.println(res);

        ds.set(4, 7);

        res = ds.get(4);
        System.out.println(res);

        res = ds.get(3);
        System.out.println(res);

        ds.setAll(6);

        ds.set(8, 2);

        res = ds.get(3);
        System.out.println(res);
    }
}
