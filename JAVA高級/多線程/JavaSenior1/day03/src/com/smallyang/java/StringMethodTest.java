package com.smallyang.java;

import org.junit.jupiter.api.Test;

/**
 * @author USER
 * @date 2023-10-22 上午 09:05
 */
public class StringMethodTest {

    @Test
    public void test4() {
        String str1 = "廖小羊大冒險";
        String str2 = str1.replace('廖', '楊');
        String str3 = str1.replace("廖小羊", "星馬豪");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println("*****************************************");
        String str4 = "HEllodadas123145odsdsads";
        String str5 = str4.replaceAll("\\d+", ",");//d代表數字 +代表全部
        System.out.println(str5);

        String cellNum = "0939789006";
        boolean isTelMatcher = cellNum.matches("\\d");// is all number?
        System.out.println(isTelMatcher);
        // is 09 opening?
        boolean is0939 = cellNum.matches("09\\d{11}");
        System.out.println(is0939);

    }

    @Test
    public void test3() {
        String str1 = "helloworld";
        boolean b1 = str1.endsWith("wld");
        boolean b2 = str1.startsWith("hel");
        boolean b3 = str1.startsWith("ll",2);
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);


        String str2 = "wo";
        System.out.println(str1.contains(str2));

    }


    @Test
    public void test1() {
        String s1 = "   he ll ow orld   ";
        System.out.println(s1.length());
        System.out.println(s1.charAt(2));
        System.out.println(s1.isEmpty());
        System.out.println(s1.toLowerCase());
        System.out.println(s1.toUpperCase());
        System.out.println(s1.trim());

        String s2 = "AA";
        String s3 = "aa";
        System.out.println(s2.equals(s3));
        System.out.println(s2.equalsIgnoreCase(s3));

        System.out.println(s2.concat(s3));

        String s5 = "abc";
        System.out.println(s5.substring(1));
        String s4 = new String("abe");
        System.out.println(s5.compareTo(s4));// 涉及字符串排序
    }
}
