package com.smallyang.java;


import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author USER
 * @date 2024-10-12 下午 05:34
 */
public class OptionalTest {

    @Test
    public void test1() {
        // empty():創建Optional對象內部的value = null
        Optional<Object> op1 = Optional.empty();
        if (!op1.isPresent()) {// Optional封裝的數據是否包含數據
            System.out.println("數據為空");
        }
//        System.out.println(op1.get());// java.util.NoSuchElementException: No value present
        System.out.println(op1.isPresent());
        System.out.println(op1);
        // 如果Optional封裝的數據value為空，則get()報錯。否則,value不為空時，返回value
//        System.out.println(op1.get());
    }

    @Test
    public void test2() {
        String str = "hello";
//        str = null;
        // of(T t) :封裝數據t 生成Optional對象，要求t非空，否則會報錯。
        Optional<String> op1 = Optional.of(str);
        //get()通常與of()方法搭配使用，用於獲取內部封裝的數據value
        System.out.println(op1.get());
    }


    @Test
    public void test3() {
        String str = "小羊";
        str = null;
        // ofNullable(T t): 封裝數據t賦給Optional內部的value。不要求t非空
        Optional<String> str1 = Optional.ofNullable(str);
//        orElse(T t1) :如果Optional內部的value非空，則返回value，
//        如果value為空，則返回t1。
        String str2 = str1.orElse("小暄");
        System.out.println(str2);
    }
}
