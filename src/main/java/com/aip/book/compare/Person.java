package com.aip.book.compare;

import com.aip.book.searilazation.Employee;
import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
    private int age;
    private String name;
    private String jobTitle;

    public Person(int age, String name, String jobTitle) {
        this.age = age;
        this.name = name;
        this.jobTitle = jobTitle;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        if (this.age - o.age != 0) return this.age - o.age;
        else return this.name.compareTo(o.name);
    }
}
