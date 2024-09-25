package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * Lambda表達式的使用舉例
 *
 * @author USER
 * @date 2024-09-21 下午 05:38
 */
public class LambdaTest {
    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我愛小羊1");
            }
        };
        r1.run();

        System.out.println("***********************************");


        Runnable r2 = () -> System.out.println("我愛小羊2");

        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        int compare = com1.compare(12, 21);
        System.out.println(compare);

        System.out.println("****************************************");


        // 看到 -> Lambda表達式
        Comparator<Integer> com2 = (o1, o2) -> Integer.compare(o1, o2);
        int compare1 = com2.compare(32, 21);
        System.out.println(compare1);
        System.out.println("****************************************");
        // 看到  ::  方法引用
        Comparator<Integer> com3 = Integer::compare;

        int compare2 = com3.compare(32, 21);

        System.out.println(compare2);


    }
}
