package com.scrap;

public class ParentClass {
    {
        System.out.println("Ashutosh init");
    }

    public ParentClass() {
        System.out.println("Parent class");
    }

    {
        System.out.println("Ashutosh init 2");
    }

    static {
        System.out.println("Ashutosh Static");
    }
}

class ChildClass extends ParentClass {
    {
        System.out.println("Ashutosh child init");
    }

    public ChildClass() {
        System.out.println("Child class");
    }

    {
        System.out.println("Ashutosh init child 2");
    }

    static {
        System.out.println("Ashutosh Child Static");
    }
}
