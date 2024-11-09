package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author USER
 * @date 2024-10-24 上午 05:55
 */
public class Java9Test {
    // jshell 讀取url

    // java9 特性五： 鑽石操作符的升級
    @Test
    public void test2() {
        // jdk8中，匿名類這個無法類型推斷
        // 鑽石操作符與匿名內部類在java8中不能共存，java9可以
        Comparator<Object> com = new Comparator<>() {// jdk9以後匿名內部類鑽石操作符不用宣告的一樣指定型別
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };


//        jdk7中的新特性：類型推斷
        ArrayList<String> list = new ArrayList<>();
    }

    // java9 特性六： try 操作的升級
//    public static void main(String[] args) {
////        InputStreamReader reader = null;
////        try {
////            reader = new InputStreamReader(System.in);
////            char[] cbuf = new char[20];
////            int len;
////            if((len = reader.read(cbuf)) != -1) {
////                String str = new String(cbuf, 0, len);
////                System.out.println(str);
////            }
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        } finally {
////            if(reader!=null) {
////                try {
////                    reader.close();
////                } catch (IOException e) {
////                    throw new RuntimeException(e);
////                }
////
////            }
////        }
//
//        // java8 中資源關閉: java8中可以實現資源的自動關閉
//        // 要求自動關閉自動關閉的資源的的實例化必須放在try的一對小括號
////        try(InputStreamReader reader = new InputStreamReader(System.in)) {
////            char[] cbuf = new char[20];
////            int len;
////            if((len = reader.read(cbuf)) != -1) {
////                String str = new String(cbuf, 0, len);
////                System.out.println(str);
////            }
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//
//        // java9中資源關閉的操作：需要自動關閉的資源的實力畫可以放在try小括號外面
//        // 此時的資源屬性是常量，聲明為final，不可修改
//        InputStreamReader reader = new InputStreamReader(System.in);
//        try(reader) {
//            char[] cbuf = new char[20];
//            int len;
//            if((len = reader.read(cbuf)) != -1) {
//                String str = new String(cbuf, 0, len);
//                System.out.println(str);
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }

    @Test
    public void test3() {


    }
}
