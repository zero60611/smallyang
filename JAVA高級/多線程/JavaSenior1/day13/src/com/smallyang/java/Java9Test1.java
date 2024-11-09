package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author USER
 * @date 2024-10-26 下午 12:23
 */
public class Java9Test1 {

    // java9新特性八: 集合工廠方法: 創建只讀集合
    //
    @Test
    public void test1() {
        List<String> nameList = null;
        //  只能讀取，不能再額外新增
//        jdk以前以前
        nameList = Collections.unmodifiableList(nameList);
//        nameList = List.of("AA", "BB", "CC");
        System.out.println(nameList);

        
        // jdk 9以後
        List<Integer> list = List.of(1, 2, 2, 3);
        Set.of("1","2");
        Map<String, Integer> stringIntegerMap = Map.ofEntries(Map.entry("1", 34), Map.entry("2", 35));
    }

    @Test
    public void test2(String[] args) {
        // java9 新特性九 InputStream 加強 新方法
        ClassLoader c1 = this.getClass().getClassLoader();
        try(InputStream is = c1.getResourceAsStream("hello.txt"); OutputStream os = new FileOutputStream("src\\hello1.txt")){
            is.transferTo(os);// 把輸入流中的所有數據直接自動地複製到輸出劉忠
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
