package com.scrap;

public class Person {
    private String name;

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static class Builder {
        private Person person;
        public Builder name(String name) {
            person = new Person();
            person.setName(name);
            return this;
        }

        public Person build() {
            return person;
        }
    }
}
