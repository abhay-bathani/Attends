package com.example.project1;

public class Student {
    private int id;
    private String rollNumber;
    private String name;

    public Student(int id, String rollNumber, String name) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public String getName() {
        return name;
    }
}
