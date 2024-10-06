package com.smallyang.java3;

import com.smallyang.java2.Employee;
import com.smallyang.java2.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 測試Stream的中間操作
 *
 * @author USER
 * @date 2024-10-03 上午 10:17
 */
public class StreamAPITest1 {

    //1-篩選與切片
    @Test
    public void test1() {
        List<Employee> employeeList = EmployeeData.getEmployees();

//        filter(Predicate p)  接收 Lambda ，從排除某些元素。
        Stream<Employee> stream = employeeList.stream();
        // 練習：查詢員工薪資大於10000的員工訊息。
        stream.filter(e -> e.getSalary() > 10000).forEach(System.out::println);
        System.out.println("***********************************");
//        limit(n) 截斷流，使其元素不超過給定數量。
        // 這邊會出錯，因為forEach已經針對Stream終止操作，因此無法再回到中間操作。
        employeeList.stream().limit(3).forEach(System.out::println);

        System.out.println("***********************************");

        //        skip(n) 跳過元素，返回一個扔掉了前n個元素的流，若流中元素不足 n 個，則返回一個
//                空流，與limit(n)互補。
        employeeList.stream().skip(2).forEach(System.out::println);
        System.out.println("***********************************");
        employeeList.stream().skip(100).forEach(System.out::println);
        System.out.println("***********************************");

//        distinct() 篩選，通過流所生成元素的hashCode() 和 equals()去除重複元素。
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
        employeeList.add(new Employee(1008, "廖小羊", 3, 80000000));
//        System.out.println(employeeList);

        employeeList.stream().distinct().forEach(System.out::println);

    }

    // 2-映射
    @Test
    public void test2() {
        // map(Function f) 接收一個函數作為參數，將元素換成其他形式或提取信息，該函數
//            會被應用到每個元素，並將其映射成一個新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        System.out.println("********************************");
        // 練習1：獲取員工姓名長度大於3的員工的姓名
        List<Employee> employeeList = EmployeeData.getEmployees();
        Stream<String> stringStream = employeeList.stream().map(Employee::getName);
        stringStream.filter(name -> name.length() > 2).forEach(System.out::println);
        System.out.println();
        // 練習2：
        // 這種就像List<List<String>> 必須要for兩次才能解析每一個元素
        // 所以用flatMap去做就比較簡單了
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s -> {
            s.forEach(System.out::println);
        });
        System.out.println();

        //flatMap(Function f) 接收一個函數作為參數，將流中的每個值都換成另一個流，
//            然後把所有流連接成一個流。
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);
    }

    //  將字符串中的多個字符構成的集合轉換為對應的Stream的實例
    public static Stream<Character> fromStringToStream(String str) {//aa
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test3() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list1.add(list2);// .map
        list1.addAll(list2); // 類似.flatMap
        System.out.println(list1);
    }

    //3-排序
    @Test
    public void test4() {
        // sorted()- 自然排序
        List<Integer> list = Arrays.asList(12, 23, 66, 31, 2, 5, 11, 15, -98);
        list.stream().sorted().forEach(System.out::println);
        System.out.println();

        // 拋異常， 原因:Employee沒有實現Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);

        // sorted(Comparator com) -訂製排序
        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted((e1, e2) -> {
//            return Integer.compare(e1.getAge(), e2.getAge());
//        }).forEach(System.out::println);
//        employees.stream().sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge())).forEach(System.out::println);
//        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(System.out::println);

        // 複雜一點的
        employees.stream().sorted((e1, e2) -> {
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            if (ageValue != 0) {
                return ageValue;
            } else {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }
}
