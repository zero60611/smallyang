package com.smallyang.java;

import java.io.Serializable;

/**
 * Person 需要滿足如下的要求，方可序列化
 * 1. 需要實現接口:Serializable
 * 2. 當前類提供一個全局常量： serialVersionUID
 * 3. 除了當前Person類需要實現serializable接口外，還必須保證其內部所有屬性也必須是可序列化
 *    (預設情況下，基本數據類型可序列化)
 * 
 * @author USER
 * @date 2024-08-04 下午 03:09
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 7456413216540442L;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }

    public Person(String name, int age, Account account) {
        this.name = name;
        this.age = age;
        this.account = account;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
    private Account account;

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

}

class Account implements Serializable{
    public static final long serialVersionUID = 74564132165404L;

    public Account(double balance) {
        this.balance = balance;
    }

    private double balance;

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}