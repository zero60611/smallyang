package com.smallyang.java2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、構造器引用
*      和方法引用類似：
 *     1. 函數式接口的抽象方法的形參列表和構造器的形參列表一致。
*      2. 抽象方法的返回值類型即為構造器所屬的類的類型。
 *
 *
 *
 * 二、數組引用
 *      大家可以把數組看作是一個特殊的類，則寫法與構造器引用一致。
 *
 * @author USER
 * @date 2024-09-26 上午 06:27
 */
public class ConstructorRefTest {
    // 構造器引用
    //Supplier中的T get()
    //Employee的空參構造器： Employee()
    // 把Employee()看成一個方法，他沒參數 get()也沒參數 也是返回一個對象 一個T
    @Test
    public void test1() {
        Supplier<Employee> sup = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(sup.get());
        System.out.println("*****************************************");

        Supplier<Employee> sup1 = () -> new Employee();
        System.out.println(sup1.get());

        System.out.println("*****************************************");

        // 構造器引用
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

    }

    //Function中的R apply(T t)
    @Test
    public void test2() {
        Function<Integer, Employee> func = id -> new Employee(id);
        Employee employee = func.apply(1001);
        System.out.println(employee);
        System.out.println("*****************************************");

        //  已知會給定Integer，因此建構子會自動判斷會有一個參數建構子
        Function<Integer, Employee> func2 = Employee::new;
        System.out.println(func2.apply(1002));
        System.out.println("*****************************************");
    }

    // BiFunction中的R apply(T t, U u)
    @Test
    public void test3() {
        BiFunction<Integer, String, Employee> bi = (id, name) -> new Employee(id, name);
        System.out.println(bi.apply(1001, "Tom"));
        System.out.println("*****************************************");

        BiFunction<Integer, String, Employee> bi2 = Employee::new;
        System.out.println(bi2.apply(1002, "smallyang"));
    }

    // 數組引用
    // Function中的apply(T t)
    @Test
    public void test4() {
        Function<Integer, String[]> func1 = length -> new String[length];
        String[] arr1 = func1.apply(5);
        System.out.println(Arrays.toString(arr1));
        System.out.println("*****************************************");

        Function<Integer, String[]> func2 = String[]::new;
        String[] arr2 = func2.apply(10);
        System.out.println(Arrays.toString(arr2));
    }

}
