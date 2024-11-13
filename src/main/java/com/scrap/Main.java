package com.scrap;

class Parent {
    static void staticMethod() {
        System.out.println("Static method in Parent");
    }

    public String sayHello() {
        return "Hello from parent";
    }
}

class Child extends Parent {
    // This does not override the static method in Parent
    static void staticMethod() {
        System.out.println("Static method in Child");
    }

    public String sayHello() {
        return "Hello from Child";
    }
}

public class Main {
    public static void main(String[] args) {
        // Calling static method of the Parent class
        Parent.staticMethod(); // Output: Static method in Parent

        // Calling static method of the Child class
        Child.staticMethod(); // Output: Static method in Child

        // Accessing static method through a reference of the Parent class
        Parent reference = new Child();
        reference.staticMethod(); // Output: Static method in Parent
        System.out.println(reference.sayHello());

        new ChildClass();
    }
}

