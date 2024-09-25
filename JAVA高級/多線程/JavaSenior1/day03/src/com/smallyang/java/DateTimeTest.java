package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * JDK8 之前日期和時間的API測試
 *
 * @author USER
 * @date 2024-01-17 上午 04:37
 */
public class DateTimeTest {
    /*
        java.util.Date類
                |---java.sql.Date類
         1. 兩個構造器的使用
             >構造器一:Date():創建一個對應當前時間的Date對象
             >構造器二: 創建指定毫秒數的Date對象
         2. 兩個方法的使用
            >toString():顯示當前的年月日時分秒
            >getTime(): 獲取當前Date對象對應的毫秒數(timestamp)

         3. java.sql.Date對映著數據庫中的日期類型的變量
         > 如何實例化
         > 如何將 java.util.Date對象轉換為 java.sql.Date
     */
    @Test
    public void test2() {
        //  構造器一:Date():創建一個對應當前時間的Date對象
        Date date1 = new Date();// Sat Jan 20 11:14:31 GMT+08:00 2024
        //
        System.out.println(date1);
        System.out.println(date1.getTime());// 返回當前時間與1970/1/1 0時0分0秒之間以毫秒為單位的時間差

        //構造器二: 創建指定毫秒數的Date對象
//        new Date(2010,1,13);已被棄用
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println(date2);

        //  創建java.sql.Date對象
        java.sql.Date date3 = new java.sql.Date(System.currentTimeMillis());
        System.out.println(date3);// 2024-01-20

        //  如何將 java.util.Date對象轉換為 java.sql.Date
        //  情況一：
//        Date date4 = new java.sql.Date(System.currentTimeMillis());
//        java.sql.Date date5 = (java.sql.Date)date4;
        //  情況二：
        Date date6 = new Date();
//        java.sql.Date date7 = (java.sql.Date)date6;
        java.sql.Date date7 = new java.sql.Date(date6.getTime());
        System.out.println(date7);// new了父類還轉成子類絕對不行啊
        //  因此強轉不可行，要用getTime得知long 再用構造器方式處理
    }


    // 1. System類中的currentTimeMillis()
    @Test
    public void test1() {
        long time = System.currentTimeMillis();
        // 返回當前時間與1970/1/1 0時0分0秒之間以毫秒為單位的時間差
        // 稱為時間戳
        System.out.println(time);
    }
}
