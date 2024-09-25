package com.smallyang.exer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author USER
 * @date 2024-05-08 下午 10:54
 */
public class CollectionTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);
        coll.forEach(System.out::println);
    }


    public static List duplicatieList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }

    @Test
    public void test2() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(4);
        List list1 = duplicatieList(list);
        list1.forEach(d -> {
            System.out.println(d);
        });
    }

    @Test
    public void test3() {
        HashSet set = new HashSet();

        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);

        p1.name = "CC"; // 改變p1的name，但是沒改變p1的hashCode，hashCode還是當初的"AA"
        set.remove(p1);// 找不到hashCode，所以刪不掉
        System.out.println(set);

        set.add(new Person(1001, "CC"));// 新的hashCode
        System.out.println(set);
        set.add(new Person(1001, "AA"));// 原有的hashCode，但是equals就不一樣
        System.out.println(set);
    }
}
