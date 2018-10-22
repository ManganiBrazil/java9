package com.mangani.model;

public class Person {

    private final String name;
    private final Byte age;

    public Person(String name, Byte age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Byte getAge() {
        return age;
    }
}
