package com.aip.design.enums;

public enum Names {
    EREN(1),
    ARMIN(2),
    ERWIN(3),
    LEVI(4);

    private int id;

    public int getId() {
        return this.id;
    }

    private Names(int id) {
        System.out.println("Constructor = " + this.toString());
    }
}
