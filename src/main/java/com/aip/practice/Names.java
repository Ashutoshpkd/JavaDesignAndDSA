package com.aip.practice;

public enum Names {
    ASHUTOSH("Ashutosh"),
    EREN("Eren");

    String name;

    Names(String name) {
        this.name = name;
    }
}

class Driver {
    public static void main(String[] args) {
        System.out.println(Names.EREN);
        System.out.println(Names.values());
    }
}