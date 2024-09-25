package com.smallyang.java;

import org.junit.jupiter.api.Test;

/**
 * String 類使用
 *
 * @author USER
 * @date 2023-10-07 上午 09:30
 */
public class StringTest {
    String str = new String("good");
    char[] ch = {'t', 'e', 's', 't'};
    /*
        結論:
        1.常量與常量的拼接結果在常量池，且常量池不會存在相同內容的常量。
        2.只要有其中一個是變量，結果就在堆中。
        3.如果拼接的結果調用intern()方法，返回值就在常量池中。
     */

    @Test
    public void test4() {
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 + "hadoop";
        System.out.println(s1 == s3);

        final String s4 = "javaEE";
        String s5 = s4 +  "hadoop";
        System.out.println(s1 == s5);


    }

    public static void main(String[] args) {
        StringTest st = new StringTest();
        st.change(st.str, st.ch);
        System.out.println(st.str);
        System.out.println(st.ch);
    }

    public void change(String str, char[] ch) {
        str = "testDSDSD";
        ch[0] = 'b';
        System.out.println(str);
    }


    /*
        String 的實例化方式
        方式一:通過字面量定義的方式
        方式二:通過new + 構造器的方式

        面試題: String s = new String("abc");方式創建對象
        在內存中創建了幾個對象?
        兩個:一個是堆空間中new結構，另一個是char[]對應的常量池中的數據:"abc"
     */
    @Test
    public void test2() {
        //  此時的s1 s2數據javaEE生明在方法區中的字符串常量池中。
        String s1 = "javaEE";
        String s2 = "javaEE";
        System.out.println(s1 == s2);
        //  通過new + 構造器的方式:此時的s3 s4保存的地址值，是數據在堆空間中開闢空間以後對應的地址值
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");
        System.out.println(s3 == s4);

        Person p1 = new Person("TOM", 12);
        Person p2 = new Person("TOM", 12);
        System.out.println(p1.name.equals(p2.name));// true
        System.out.println(p1.name == p2.name);// true

        p1.name = "JERRY";
        System.out.println(p2.name);

    }

    /*
        String字符串使用雙引號""表示
        1.String聲明為final的，不可被繼承
        2.String實現了Serializable接口:表示字符串是支持序列化的
                實現了Comparable接口:表示String可以比較大小
            體現:
            2.1.當對字串重新賦值時，需要重寫指定內存區域賦值，不能使用原有的value進行賦值。
            2.2當對現有的字串進行連接操作時，也要重新指定內存區域賦值，不能使用原有的value進行賦值。
            2.3當調用String的replace()方法修改指定字符或字符串時，不能使用原有的value進行賦值。
        3.String內部定義了final char[] value用於存儲字符串數據
        4.String:代表不可變的字符序列。簡稱:不可變性。
        5.通過字面量的方式(區別於new) 給一個字符串賦值，此時的字符串值失明在字符串
        常量池中。
        6.字符串常量池是不會存儲同內容的字串的。


     */
    @Test
    public void test1() {
        String s1 = "abc";//   字面量定義方式
        String s2 = "abc";
        s1 = "cde";
        System.out.println(s1 == s2);

        System.out.println("********************");
        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);
        System.out.println(s2 == s3);
        System.out.println("********************");

        String s4 = "abc";
        String s5 = s4.replace("a", "m");
        System.out.println(s4 == s5);
    }


}
