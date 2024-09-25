package com.smallyang.exer;

/**
 * @author USER
 * @date 2024-05-11 上午 07:02
 */
public class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public MyDate() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof MyDate) {
//            MyDate o1 = (MyDate) o;
//            // 比較年
//            int minusYear = this.getYear() - o1.getYear();
//            if (minusYear != 0) {
//                return minusYear;
//            }
//
//            // 比較月
//            int minusMonth = this.getMonth() - o1.getMonth();
//            if (minusMonth != 0) {
//                return minusMonth;
//            }
//
//            // 比較日期
//            return this.getDay() - o1.getDay();
//        }
//        throw new RuntimeException("傳入的數據不一致");
//    }

    @Override
    public int compareTo(MyDate o) {
        // 比較年
        int minusYear = this.getYear() - o.getYear();
        if (minusYear != 0) {
            return minusYear;
        }

        // 比較月
        int minusMonth = this.getMonth() - o.getMonth();
        if (minusMonth != 0) {
            return minusMonth;
        }

        // 比較日期
        return this.getDay() - o.getDay();
    }
}
