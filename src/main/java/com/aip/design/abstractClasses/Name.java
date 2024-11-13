package com.aip.design.abstractClasses;

public interface Name {

    default void printName() {
        System.out.println("Ashutosh");
    }

    void randomNumber();
}
