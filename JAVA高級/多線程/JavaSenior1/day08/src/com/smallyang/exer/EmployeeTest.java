package com.smallyang.exer;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author USER
 * @date 2024-05-11 上午 07:06
 */
public class EmployeeTest {

    // 問題二: 按生日日期的先後排序。
    @Test
    public void test2() {

        TreeSet<Employee> set = new TreeSet<>((o1, o2) -> {// 使用匿名函式，因為接口只有一個方法 所以可以這樣搞
            // 要不然會變成
//            new Comparator(
//                    @Override
//                      public int compare(Object o1, Object o2){
//
//                }
//            )
            // 方式一
//            if (o1 instanceof Employee && o2 instanceof Employee) {
//
//                Employee e11 = (Employee) o1;
//                Employee e22 = (Employee) o2;
//
                MyDate b1 = o1.getBirthday();
                MyDate b2 = o2.getBirthday();
//
//                // 比較年
//                int minusYear = b1.getYear() - b2.getYear();
//                if (minusYear != 0) {
//                    return minusYear;
//                }
//
//                // 比較月
//                int minusMonth = b1.getMonth() - b2.getMonth();
//                if(minusMonth != 0) {
//                    return minusMonth;


                // 方式二  建議直接用MyDate這個進行排序
                return b1.compareTo(b2);
//            }
//
//                // 比較日期
//                return b1.getDay() - b2.getDay();
//            }
//            throw new RuntimeException("傳入的數據不一致");
        });
        Employee e1 = new Employee("smallYang1", 5, new MyDate(2019, 5, 4));
        Employee e5 = new Employee("smallYang5", 45, new MyDate(1999, 5, 4));
        Employee e3 = new Employee("smallYang3", 25, new MyDate(2009, 5, 4));
        Employee e4 = new Employee("smallYang4", 35, new MyDate(2004, 5, 4));
        Employee e2 = new Employee("smallYang2", 15, new MyDate(2014, 5, 4));
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // 問題一:使用自然排序
    @Test
    public void test1() {
//        TreeSet<Employee> set = new TreeSet<Employee>();
        TreeSet<Employee> set = new TreeSet<>();
        Employee e1 = new Employee("smallYang1", 5, new MyDate(2019, 5, 4));
        Employee e5 = new Employee("smallYang5", 45, new MyDate(1999, 5, 4));
        Employee e3 = new Employee("smallYang3", 25, new MyDate(2009, 5, 4));
        Employee e4 = new Employee("smallYang4", 35, new MyDate(2004, 5, 4));
        Employee e2 = new Employee("smallYang2", 15, new MyDate(2014, 5, 4));
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator<Employee> iterator = set.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            System.out.println(employee);
        }
    }
}
