package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * jdk 8 中日期時間API的測試
 *
 * @author USER
 * @date 2024-03-01 下午 09:10
 */
public class JDK8DateTimeTest {
    @Test
    public void testDate() {
        Date date = new Date(2020, 9, 8);
        System.out.println(date);// Fri Oct 08 00:00:00 GMT+08:00 3920
        // 要處理偏移量
        Date date1 = new Date(2020 - 1900, 9 - 1, 8);
        System.out.println(date1);// Tue Sep 08 00:00:00 GMT+08:00 2020

    }


    /*
        LocalDate、LocalTime、LocalDateTime的使用

        說明:
            1. LocalDateTime相較於LocalDate、LocalTime 使用率高
            2. 類似於Calendar
     */
    @Test
    public void test1() {
        // now():獲取當前的日期、時間、日期+時間
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of():設置指定的年 月 日 時 分 秒，沒有偏移量
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 13);
        System.out.println(localDateTime1);

        //getXxxx()
        int dayOfMonth = localDateTime.getDayOfMonth();
        System.out.println(dayOfMonth);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        Month month = localDateTime.getMonth();
        System.out.println(month);
        int monthValue = localDateTime.getMonthValue();
        System.out.println(monthValue);
        int minute = localDateTime.getMinute();
        System.out.println(minute);
        int hour = localDateTime.getHour();
        System.out.println(hour);

        //withDayOfMonth() 體現不可變性，
        // 超過區間會報錯 java.time.DateTimeException: Invalid value for DayOfMonth (valid values 1 - 28/31): 52
        //withXXXX設置相關的屬性
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);//2024-03-02
        System.out.println(localDate1);//2024-03-22

        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);//2024-03-02T07:52:45.210077600
        System.out.println(localDateTime2);//2024-03-02T04:52:45.210077600

        //
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime);//2024-03-02T07:56:41.285481700
        System.out.println(localDateTime3);//2024-06-02T07:54:58.919187

        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime);//2024-03-02T07:56:41.285481700
        System.out.println(localDateTime4);//2024-02-25T07:56:41.285481700

    }

    /*
        Instant的使用
        類似於 java.util.Date類
     */
    @Test
    public void test2() {
        //now():獲取本初子午線對應的標準時間
        Instant instant = Instant.now();
        System.out.println(instant);// 格林威治天文臺 本初子午線 2024-03-02T00:02:24.976887600Z

        //添加時間的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2024-03-02T08:13:43.635313800+08:00

        //  toEpochMilli():獲取自1970/1/1 0:0:0(UTC)開始的毫秒數
        long milli = instant.toEpochMilli();
        System.out.println(milli);
//        java.sql.Date date = new java.sql.Date(milli);
//        System.out.println(date);// 2024-03-02

        // ofEpochMilli():通過給定的毫秒數，獲取Instant實例 -->Date(Long millis)
        Instant instant1 = Instant.ofEpochMilli(1709338856929L);
        System.out.println(instant1);


    }

    /*
        DateTimeFormatter:格式化或解析日期、時間
        類似SimpleDateFormat
     */
    @Test
    public void test3() {
        // 方式一:預定義的標準格式:如ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        // 方式二:本地化相關格式，如ofLocalizedDateTime(FormatStyle.LONG)
        // 方式三:字定義的格式，如ofPattern("yyyy-mm-dd hh:mm:ss E")
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        // 格式化: 日期 --->字符串
        /*
            2024-03-02T17:50:29.872422900
            class java.time.LocalDateTime
            2024-03-02T17:50:29.8724229
            class java.lang.String
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getClass());
        System.out.println(format);
        System.out.println(format.getClass());

        // 解析:
        // 因為不確定是date time datetime，所以是用接口接回傳值
        // 多態
        TemporalAccessor parse = formatter.parse("2024-03-02T17:50:29.8724229");
        System.out.println(parse);

        // 方式二:本地化相關格式，如ofLocalizedDateTime(FormatStyle.LONG)
        // ofLocalizedDateTime()
        // FormatStyle.LONG/FormatStyle.MEDIUM/FormatStyle.SHORT，適用於LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        DateTimeFormatter formatter3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        // 格式化
        String format1 = formatter1.format(localDateTime);
        String format2 = formatter2.format(localDateTime);
        String format3 = formatter3.format(localDateTime);
        System.out.println(format1);
        System.out.println(format2);
        System.out.println(format3);// 10:31

        //ofLocalizedDate()
        //FormatStyle.FULL/ FormatStyle.LONG/FormatStyle.MEDIUM/FormatStyle.SHORT 適用於LocalDate
         // 格式化
        DateTimeFormatter formatter4 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter formatter5 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        String format4 = formatter4.format(LocalDate.now());
        String format5 = formatter5.format(LocalDate.now());
        System.out.println(format4);//2024年3月2日 星期六
        System.out.println(format5);//2024年3月2日

        // 方式三:字定義的格式，如ofPattern("yyyy-mm-dd hh:mm:ss E")
        DateTimeFormatter formatter6 = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");
        // 格式化
        String format6 = formatter6.format(LocalDateTime.now());
        System.out.println(format6);

        // 解析
        /*
        {MinuteOfHour=14, HourOfAmPm=10, Year=2024, MilliOfSecond=0, DayOfMonth=2, SecondOfMinute=54, NanoOfSecond=0, MicroOfSecond=0},ISO
         */
        TemporalAccessor parse1 = formatter6.parse(format6);
        System.out.println(parse1);
    }


}
