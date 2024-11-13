package com.aip.book.searilazation;

import java.io.Externalizable;
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 21L;

    private int age;
    private String company;
    private String role;

    private String email;
    private String firstName;
    private String lastName;
    private int salary;

    public Employee() {
        System.out.println("NoArgs constructor called - Employee");
    }

    public Employee(int age, String company, String role, String email, String firstName, String lastName, int salary) {
        this.age = age;
        this.company = company;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", company='" + company + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
