package com.aip.book.compare;

import java.util.Collections;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        List<Person> personList = List.of(new Person(24, "Ashutosh", "SDE1"),
                new Person(24, "Yash", "SDE"),
                new Person(25, "Agrawal", "QA"));

        Collections.sort(personList);

        System.out.println(personList);
    }
}
