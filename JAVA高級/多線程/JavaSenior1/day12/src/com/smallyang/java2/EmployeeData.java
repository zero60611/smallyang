package com.smallyang.java2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 * @date 2024-09-26 上午 06:38
 */
public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(1001, "廖小羊",3, 80000000));
        list.add(new Employee(1002, "廖小暄", 30, 0));
        list.add(new Employee(1003, "AA", 5, 806600));
        list.add(new Employee(1004, "BB", 45, 6061));
        list.add(new Employee(1005, "CC", 34, 506056));
        list.add(new Employee(1006, "DD", 63, 1454));
        list.add(new Employee(1007, "EE", 52, 56050));
        return list;
    }
}
