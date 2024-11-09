package com.small.yang.java;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Java10Test {
    /*
        java10新特性1 局部變量的類型推斷
     */
    @Test
    public void test1() {
//        int num = 10;
        // 1. 聲明變量時，根據所賦的值，推斷變量的類型
        var num = 10;

        var list = new ArrayList<Integer>();
        list.add(123);

        // 2. 遍歷操作
//        for(Integer i : list) {
//
//        }
        for (var i : list) {
            System.out.println(i);
            System.out.println(i.getClass());
        }

        // 3 .普通的遍歷操作
        for (var i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }


    @Test
    public void test2() {
        // 類型推斷是透過右邊來判斷左邊的類型，所以右邊一定要寫。
        //1. 局部變量不賦值，就不能實現類型推斷。
//        var num;

        //2.lambda表達式中，左邊的函數式接口不能聲明為var
        // lambda只能賦給函數式接口，用var會不知道是給什麼，不知道有沒有return，不知哪個functionProgram
//        Supplier<Double> sup = () -> Math.random();
//         var sup = () -> Math.random();

        //3. 方法引用中，左邊的函數式接口不能聲明為var
//        Consumer<String> con = System.out::println;
//        var con = System.out::println;

        //4. 數組靜態初始化中，注意下面的情況也不可以
//        int[] arr = new int[]{1,2,3,4};
//        var arr = new int[]{1,2,3,4};
//        var arr = {1,2,3,4};  XXX
    }

    @Test
    public void test3() {
        //情況 1:沒有初始化的局部變量聲明
//        var s = null;


        //情況 6:catch區塊 exception不能用var
//        try {
//
//        } catch (var e) {
//            e.printStackTrace();
//        }

    }


    //情況 2:方法的返回類型
//    public var method1() {
//        // var是由裡面決定外面，但方法是由外面來決定裡面該返回什麼
//        //  如果return值跟外面不符合，那也是會噴錯。
//        return 0;
//    }

    //情況 3:方法的參數類型
//    public void method2(var num) {
//        // 參數內如果寫int，那傳其他的就不行，所以是由參數裡面的型態決定外面傳參的型態，var 是由外面去決定，沒決定所以無法傳參
//    }

    //情況 4:構造器的參數類型，跟情況3一樣
//    public Java10Test(var i) {
//
//    }

    //情況 5:屬性
    //  因為屬性有時候有預設值，必須先告訴我類型才能知道是什麼預設值，
    //  因此如果用var在屬性，會很亂很難判斷。
//    var num = 10;

    @Test
    public void test4() {
        try {
            var url = new URL("https://www.google.com.tw/");
            var connection = url.openConnection();
            var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            Stream<String> lines = reader.lines();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    // java10新特性2:　集合中新增的copyOf(),用於創建只讀的集合
    @Test
    public void test5() {
        var list1 = List.of(1, 2, 3);
        var copy1 = List.copyOf(list1);// 只返回只讀集合，就沒必要再造一個
        System.out.println(list1 == copy1);// true

        var list2 = new ArrayList<Integer>();
        list2.add(1);
        var copy2 = List.copyOf(list2);
        System.out.println(list2 == copy2);// false

        // 結論：copyOf(Xxx coll):如果參數coll本身就是一個只讀集合，則copyOf返回的方法即為當前的coll
        // 如果參數coll不是一個只讀集合，則copyOf()返回一個新的只讀集合
        String text = "\u2003 hello world \u2003";

// 使用 .trim()
        System.out.println("trim: '" + text.trim() + "'"); // 結果為 "hello world "，前後的 Unicode 空白字符未移除

// 使用 .strip()
        System.out.println("strip: '" + text.strip() + "'"); // 結果為 "hello world"，前後的所有 Unicode 空白字符都移除


    }

}
