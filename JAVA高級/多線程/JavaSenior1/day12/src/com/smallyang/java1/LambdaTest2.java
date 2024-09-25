package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * java內置的4大核心函數式接口
 * 消費型接口 Consumer<T>    void accept(T t)
 * 供給型接口 Supplier<T>    T get()
 * 函數型接口 Function<T, R> R apply(T t)
 * 斷定行接口 Predicate<T>   boolean test(T t)
 *
 * @author USER
 * @date 2024-09-23 下午 10:36
 */
public class LambdaTest2 {
    @Test
    public void test1() {
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("想賺" + aDouble + "元");
            }
        });
        System.out.println("********************************");
        happyTime(50000, money -> System.out.println("想賺" + money + "元"));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("A", "B", "C", "D");
        List<String> filterStringList = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("A");
            }
        });
        System.out.println(filterStringList);

        System.out.println("******************************");
        List<String> filterStringList1 = filterString(list, s -> s.contains("B"));
        System.out.println(filterStringList1);
    }

    // 根據給定的規則，過濾集合中的字符串，此規則由Predicate的方法決定
    public List<String> filterString(List<String> list, Predicate<String> pre) {
        List<String> filterList = new ArrayList<>();
        for (String s : list) {
            if (pre.test(s)) {
                filterList.add(s);
            }
        }
        return filterList;
    }
}
