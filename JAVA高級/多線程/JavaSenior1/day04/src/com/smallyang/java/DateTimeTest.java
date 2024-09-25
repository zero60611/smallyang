package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * JDK8之前的日期時間API測試
 * 1. System類中的currentTimeMills();
 * 2. java.util.Date和子類java.sql.Date
 * 3. SimpleDateFormat
 * 4. Calendar
 *
 * @author USER
 * @date 2024-02-28 下午 08:47
 */
public class DateTimeTest {

    /*
        SimpleDateFormat的使用:SimpleDateFormat對日期Date類的格式化和解析

        1.兩個操作:
        1.1 格式化:日期--->字符串
        1.2 解析:格式化的逆過程，字符串---->日期

        2. SimpleDateFormat的實例化
     */
    @Test
    public void testSimpleDateFormat() throws ParseException {
        // 實例化SimpleDateFormat:使用預設解析的構造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        // 格式化: 日期---->字符串
        Date date = new Date();
        System.out.println(date);

        String format = sdf.format(date);
        System.out.println(format);

        // 解析: 格式化的逆過程，字符串---->日期
//        String str = "2024-03-01";
        String str = "2024/3/1 上午7:24";// 預設的格式 可以識別 才不會報錯Unparseable
        Date date1 = sdf.parse(str);
        System.out.println(date1);
            /*
                java.lang.RuntimeException: java.text.ParseException:
                        Unparseable date: "2024-03-01"
             */
        //************按照指定的方式進行格式化和解析:調用帶參的構造器***********************
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/text/SimpleDateFormat.html
//        SimpleDateFormat format1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 月要大寫 要不然會變成分鐘mm **********************************
        // 2024-34-01 07:34:14
        System.out.println("**********************************");
        String format3 = format1.format(date);
        System.out.println(format3);
        // 解析:要求字符串必須是符合SimpleDateFormat識別的格式(通過構造器參數體現)
        // 否則會拋異常
        Date parse = format1.parse(format3);
        System.out.println(parse);
    }


    /*
        練習一: 字符串"2020-09-08"轉為java.sql.Date
        練習二: 三天打魚兩天曬網  1990-01-01   xxxx-xx-xx打魚? 曬網?
        舉例 2020-09-08 ?總天數
        總天數%5 == 1 2 3:打魚
        總天數%5 == 4 0:曬網
        總天數計算?
        方式一:(date2.getTime() - date1.getTime()) / (1000*60*60*24)+1 因為可能有小數，就算整除 也是幾小時之類，超過00 00 00午夜十二點

        方式二: 1990-01-01  --> 2019-12-31 + 2020-01-01 -->2020-09-08

     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf1.parse(birth);
        System.out.println(date);
        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);// 存到資料庫date型態的
    }

    /*
        Calendar 日曆類(抽象類)的使用
     */
    @Test
    public void testCalendar() {
        //1. 實例化
        //方式一: 創建其子類，GregorianCalendar
        //方式二: 調用其靜態方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar);
        //2.常用方法
        //get()
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //set()
        //calendar是可變性
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        //add()
        calendar.add(Calendar.DAY_OF_MONTH, 3);// 加了3天  要減的變成負數
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        //getTime() 日曆類 --> Date
        Date time = calendar.getTime();
        System.out.println(time);
//        System.out.println(time.getTime());

        // setTime() :Date ---> 日曆類
        Date date = new Date();
        calendar.setTime(date);
        Date time1 = calendar.getTime();
        System.out.println(time1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
    }
}
