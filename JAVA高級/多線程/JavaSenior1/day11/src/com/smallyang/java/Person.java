package com.smallyang.java;

/**
 * @author USER
 * @date 2024-08-24 上午 10:55
 */
public class Person {
    private String name;
    public int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public void show(){
        System.out.println("hello , i am lonely");
    }

    private String showNation(String nation) {
        System.out.println("我的國籍是: " + nation);
        return nation;
    }

}
