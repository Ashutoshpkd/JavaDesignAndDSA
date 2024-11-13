package com.aip.design.abstractClasses;

public abstract class Print {
    private String name;
    private String arg;

    public Print(String name, String arg) {
        this.name = name;
        this.arg = arg;
    }

    public abstract void print();
}
