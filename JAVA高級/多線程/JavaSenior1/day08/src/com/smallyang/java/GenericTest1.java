package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 如何自定義泛型結構:泛型類、泛型接口;泛型方法
 * <p>
 * 1. 如何自定義泛型結構:泛型類、泛型接口
 *
 * @author USER
 * @date 2024-06-05 下午 12:22
 */
public class GenericTest1 {
    @Test
    public void test1() {
        // 如果定義了泛型類，實例化沒有指明類的泛型，則認為此泛型類別為Object類型
        // 要求:如果大家定義了類是帶泛型的，建議在實例化時盎指名類的泛型
        Order order = new Order();
        order.setOrderT(123);
        order.setOrderT("ABC");

        // 建議: 實例化時指名類的泛型
        Order<String> order1 = new Order<>("orderAA", 1001, "");
        order1.setOrderT("AA: hello");


    }

    @Test
    public void test2() {
        // 由於子類在繼承帶泛型的父類時，指明了泛型類型，則實例化子類對象時，不再需要指名泛型
        SubOrder subOrder1 = new SubOrder();
        subOrder1.setOrderT(1122);

        SubOrder1<String> subOrder2 = new SubOrder1<>();
        subOrder2.setOrderT("subOrder2...");
    }

    @Test
    public void test3() {
        // 泛型不同的引用不能相互賦值
        ArrayList<String> list1 = null;
        ArrayList<Integer> list2 = null;
//        Required type:
//        ArrayList
//                <String>
//        Provided:
//        ArrayList
//                <Integer>
//        list1 = list2;
    }

    @Test
    public void test4() {
        Order<String> order = new Order<>();
        Integer[] arr = new Integer[]{1,2,3,4};
        // 泛型方法在調用時，指名泛型參數的類型
        List<Integer> list = order.copyFromArrayToList(arr);

    }
}
