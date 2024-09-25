package com.smallyang.java;

/**
 * @author USER
 * @date 2024-04-22 上午 05:53
 */
public class Perosn {
    private String name;
    private int age;

    public Perosn() {
    }

    public Perosn(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Perosn{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("Person equasl method");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Perosn perosn = (Perosn) o;

        if (age != perosn.age) return false;
        return name != null ? name.equals(perosn.name) : perosn.name == null;
    }

//    @Override
//    public int hashCode() {
//        int result = name != null ? name.hashCode() : 0;
//        result = 31 * result + age;
//        return result;
//    }
}
