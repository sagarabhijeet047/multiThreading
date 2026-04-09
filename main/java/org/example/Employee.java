package org.example;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int mId;

    public Employee(int id, String name, double salary, int mId) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.mId = mId;
    }

    public Employee() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
