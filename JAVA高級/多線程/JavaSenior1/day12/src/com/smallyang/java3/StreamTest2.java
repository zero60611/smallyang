package com.smallyang.java3;

import com.smallyang.java2.Employee;
import com.smallyang.java2.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 測試Stream的終止操作
 *
 *
 * @author USER
 * @date 2024-10-04 上午 05:31
 */
public class StreamTest2 {
    //1-匹配與查找
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
//    allMatch(Predicate p) 檢查是否匹配所有元素。練習:是否所有的員工年齡都大於XX歲
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 10);
        System.out.println(allMatch);

        System.out.println();
//    anyMatch(Predicate p) 檢查是否至少匹配一個元素。練習:是否存在員工的工資大於XX元
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 20000);
        System.out.println(anyMatch);
//    noneMatch(Predicate p) 檢查是否沒有匹配的元素。練習：是否存在員工姓X
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().contains("廖"));
        System.out.println(noneMatch);
//    findFirs返回第一個元素元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);
//    findAny 返回當前流任意元素元素
        Optional<Employee> any = employees.stream().findAny();
        System.out.println(any);
        // stream因為是順序流，他findAny都會從第一個去取，因此要改用parallelStream去取

        Optional<Employee> any1 = employees.parallelStream().findAny();
        System.out.println(any1);
    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

        //    count 返中元素的總個數。數。
        long count = employees.stream().filter(e -> e.getSalary() > 10000).count();
        System.out.println(count);
//    max(Comparator c) 返回的最大值
//    練習: 返回最高工資。
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        Optional<Double> max = salaryStream.max(Double::compareTo);
        System.out.println(max);
//    min(Comparator c) 返回的最大值小值
//    練習: 返回最低工資的員工。
//        employees.stream().min((e1,e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
        Optional<Employee> min = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(min);

        //    forEach(Consumer c) 內部跌代
        employees.stream().forEach(System.out::println);
        // 集合的遍歷操作，兩個是不一樣的
        employees.forEach(System.out::println);
    }

    // 2-歸約 reduce 加總之類的
    @Test
    public void test3() {
        // reduce (T identity, BinaryOperator)  可以將流中元素反覆結合起來，得到一個值。返回T
        // 練習1:計算1~10自然數的和
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // reduce參數是BinaryOperator ，BinaryOperator 繼承BiFunction，
        //  BiFunction 傳兩個T，返回一個T: R apply(T t, U u);
        Integer reduce = list.stream().reduce(100, Integer::sum);
        System.out.println(reduce);

        // reduce(BinaryOperator b) 可以將流中元素反覆結合起來，得到一個值，返回Optional<T>
//        Optional<Integer> reduce1 = list.stream().reduce(Integer::sum);
//        reduce1.ifPresent(System.out::println);
        List<Employee> employees = EmployeeData.getEmployees();
//        Optional<Double> sumMoney = employees.stream().map(Employee::getSalary).reduce(Double::sum);
        Optional<Double> sumMoney = employees.stream().map(Employee::getSalary).reduce((d1, d2) -> d1 + d2);
        System.out.println(sumMoney.get());

    }

    // 3-收集
    @Test
    public void test4() {
        // collect(Collector c) 將流轉換為其他形式。接收一個Collectr接口的實現用於給Stream中元素
        // 做彙總的方法
        //  練習 查找員工薪水大於XX，返回一個List或set
        // 有序
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);


        // 無序
        System.out.println();
        Set<Employee> collect = employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toSet());
        collect.forEach(System.out::println);

        ArrayList<Employee> collect1 = employees.stream().filter(e -> e.getSalary() > 10000).collect(Collectors.toCollection(ArrayList::new));


        String namejoining = employees.stream().filter(e -> e.getSalary() > 10000).map(e->e.getName()).collect(Collectors.joining(","));
        System.out.println(namejoining);

    }
}
