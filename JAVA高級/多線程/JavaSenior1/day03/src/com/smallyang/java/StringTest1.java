package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 涉及到String類與其他結構之間的轉換
 * @author USER
 * @date 2023-12-05 上午 11:27
 */
public class StringTest1 {

        /*
            String 與 byte[]之間的轉換
            編碼:String --> byte[]:調用String 的 getBytes()
            解碼:byte[] --> String:調用String的構造器

            編碼: 字符串 -->字節(把從能看得懂的轉換為看不懂的二進制數據)
            解碼: 編碼的逆過程，字節 -->字符串(把看不懂的二進制數據的轉換為從能看得懂)

            說明:　解碼時，要求解碼使用的字符集是否跟編碼使用的字符集一致，才不會導致亂碼
         */
        @Test
        public void test3() throws UnsupportedEncodingException {
            String str1 = "abc1235中國";
            byte[] bytes = str1.getBytes();// 使用預設的編碼字符集進行轉換
            System.out.println(Arrays.toString(bytes));

            byte[] gbks = str1.getBytes("gbk");// 使用gbk的編碼字符集進行轉換
            byte[] UTF = str1.getBytes("utf-8");// 使用utf-8的編碼字符集進行轉換
            System.out.println(Arrays.toString(gbks));
            System.out.println(Arrays.toString(UTF));
            System.out.println("************************************");

            String bytesS = new String(bytes);//// 使用預設的編碼字符集進行解碼
            System.out.println(bytesS);

            String gbkS = new String(gbks);//// 使用utf-8的解碼來針對被gbks編碼的字節解碼 會亂碼
            System.out.println(gbkS);

            String gbkSUtf8 = new String(gbks, "gbk");//編碼集解碼集一致就不會出現亂碼
            System.out.println(gbkSUtf8);

        }

        /*
           String 與 char[]之間轉換
           String --> char[] :調用String toCharArray()

        */
    @Test
    public void test2() {
        String str1 = "abc123"; //  題目:a21cb3
        char[] chars = str1.toCharArray();
        for (char aChar : chars) {
            System.out.print(aChar);
        }
        System.out.println();
        char[] arr = new char[]{'h','e','l','l','o'};
        String arr2 = new String(arr);
        System.out.print(arr2);
    }


    /*
        複習:
        String 與基本數據類型、包裝類之間的轉換。

        String --> 基本數據類型、包裝類: 調用包裝類的靜態方法:parseXxx(Str)
        基本數據類型、包裝類 --> String:調用String的重載的valueOf(xxx)
     */

    @Test
    public void test1() {
        String str1 = "123";// 常量池
        int num = Integer.parseInt(str1);

        String str2 = String.valueOf(num);
        String str3 = num + "";// 在heap
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
    }
}
