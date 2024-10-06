package com.smallyang.java3;

import com.smallyang.java2.Employee;
import com.smallyang.java2.EmployeeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream關注的是對數據的運算，與CPU打交道
 * 集合關注的是數據的存儲，與內存打交道
 * <p>
 * 2.
 * 2.1 Stream自己不會存儲元素。
 * 2.2 Stream不會改變源對象，相反，他們會返回一個持有結果的新 Stream。
 * 2.3 Stream操作是延遲執行的，這意味著他們會等到需要結果的時候才執行。
 * <p>
 * 3. Stream執行流程
 * 3.1  Stream的實例化
 * 3.2 一系列的中間操作(過濾、映射、...)
 * 3.3 終止操作
 * <p>
 * 4. 說明：
 * 4.1 一個中間操作鏈，對數據源的數據進行處理。
 * 4.2 一旦執行終止操作，就執行中間操作鏈，並產生結果。之後不會再被使用。
 * <p>
 * <p>
 * 測試Stream的實例化
 *
 * @author USER
 * @date 2024-10-02 上午 09:18
 */
public class StreamAPITest {

    // 創建 Stream方式一: 通過集合
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
//        default Stream<E> stream() : 返回一個順序流
        // 按照List新增資料的順序
        Stream<Employee> stream = employees.stream();

//        default Stream<E> parallelStream() : 返回一個順序流
        Stream<Employee> employeeStream = employees.parallelStream();
    }

    // 創建 Stream方式二: 通過數組
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        // 調用 Arrays類 static <T> Stream<T> stream(T[] array): 返回一個流
        // 通過泛型返回指定類型
        IntStream stream = Arrays.stream(arr);

        Employee employee = new Employee(1001, "Tom");
        Employee employee1 = new Employee(1002, "Mars");
        Employee[] arr1 = new Employee[]{employee, employee1};
        Stream<Employee> stream1 = Arrays.stream(arr1);
    }

    // 創建 Stream方式三: 通過Stream的of()
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
    }

    // 創建 Stream方式四: 創建無限流
    @Test
    public void test4() {
        // 疊代
//        public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        // 遍歷前10個偶數
        //  中間操作 初始值0，不斷加2 限制10個  forEach在終止操作， 這樣中間操作才會執行
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);
        // 生成
//        public static<T> Stream<T> genenrate(Supplier<T> s)
        //  中間操作 生成隨機數 限制10個  forEach在終止操作， 這樣中間操作才會執行
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

}
