package com.smallyang.java2;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用的使用
 * <p>
 * 1. 使用情境: 當要傳遞給Lambda體的操作，已經有實現的方法了，可以使用方法引用。
 * <p>
 * 2. 方法引用: 本質上就是Lambda表達式，而Lambda表達式是作為函數式接口的實例。
 * 方法引用，也是函數式接口的實例。
 * <p>
 * 3. 使用格式:    類(或對象)  ::  方法名
 * <p>
 * 4. 具體分為如下的三種情況:
 * 情況1  對象  :: 非靜態方法(又稱實例方法)
 * 情況2  類   ::  靜態方法
 * <p>
 * 情況3  類   ::  非靜態方法
 *
 *
 * 5. 方法引用使用的要求: 要求接口中的抽象方法的形參列表和返回值類型與方法引用的方法的
 * 形參列表和返回值類型相同!(針對情況1、情況2)
 * 
 * 情況3用在形參列表不同，但參數作為方法的調用者時可以拿來用，如test5 test6 test7
 * 
 * @author USER
 * @date 2024-10-01 上午 05:23
 */
public class MethodRefTest {

    // 情況一：對象::實例方法
    //Consumer中的void accept(T t)
    //PrintStream中的void println(T t)
    @Test
    public void test1() {
        Consumer<String> con1 = str -> System.out.println(str);
        con1.accept("hello");

        System.out.println("*************************");
        PrintStream ps = System.out;
        Consumer<String> con2 = ps::println;
        con2.accept("smauu");

    }

    //Supplier中的T get()
    //Employee中的String getName()
    @Test
    public void test2() {
        Employee emp = new Employee(1001, "Tom", 23, 5600);
        Supplier<String> sup1 = () -> emp.getName();
        String name = sup1.get();
        System.out.println(name);

        System.out.println("*************************");
        Supplier<String> sup2 = emp::getName;
        String s = sup2.get();
        System.out.println(s);
    }

    // 情況二:  類 :: 靜態方法
    // Comparator中的int compare(T t1, T t2)
    // Integer中的int compare(T t1, T t2)
    @Test
    public void test3() {
        Comparator<Integer> com1 = (t1, t2) -> Integer.compare(t1, t2);
        int compare = com1.compare(2, 3);
        System.out.println(compare);

        System.out.println("*************************");
        Comparator<Integer> com2 = Integer::compareTo;
        int compare1 = com2.compare(3, 2);
        System.out.println(compare1);
    }

    //Function中的R apply(T t)
    //Math中的Long round(Double d)
    @Test
    public void test4() {
        //  原來寫法
        Function<Double, Long> func = new Function<Double, Long>() {
            @Override
            public Long apply(Double aDouble) {
                return Math.round(aDouble);
            }
        };

        System.out.println("*************************");


        Function<Double, Long> func1 = d -> Math.round(d);
        Long apply = func1.apply(23.3);
        System.out.println(apply);
        System.out.println("*************************");

        Function<Double, Long> func2 = Math::round;
        Long apply1 = func2.apply(2333.3);
        System.out.println(apply1);

    }

    // 情況三:  類::實例方法   (有難度)
    // Comparator中的int compare(T t1, T t2)
    // String中的int t1.compareTo(t2)
    @Test
    public void test5() {
        Comparator<String> com1 = (s1, s2) -> s1.compareTo(s2);
        System.out.println(com1.compare("abc", "abd"));
        System.out.println("*************************");

        Comparator<String> com2 = String::compareTo;
        System.out.println(com2.compare("abd", "abm"));
    }

    //BiPredicate中的boolean test(T t1, T t2)
    // String中的boolean t1.equals(t2)
    @Test
    public void test6() {
        BiPredicate<String, String> pre1 = (s1, s2) -> s1.equals(s2);
        System.out.println(pre1.test("abc", "abc"));
        System.out.println("*************************");
        BiPredicate<String, String> pre2 = String::equals;
        System.out.println(pre2.test("abc", "abn"));
    }

    // Function中的R apply(T t)
    // Employee中的String getName();
    @Test
    public void test7() {
        Employee msl = new Employee(1002, "msl", 23, 8000);

        Function<Employee, String> func1 = e -> e.getName();
        String name = func1.apply(msl);
        System.out.println(name);
        System.out.println("*************************");
        Function<Employee, String> func2 = Employee::getName;
        String name2 = func2.apply(msl);
        System.out.println(name2);

    }

}
