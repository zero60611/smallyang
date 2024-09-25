package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author USER
 * @date 2024-05-22 下午 09:15
 */
public class TreeMapTest {

    // 向TreeMap中添加key-value，要求key必須是由同一個類創建的對象
    //因為要按照key進行排序:自然排序、定制排序
    // 自然排序
    @Test
    public void test1() {
        TreeMap map = new TreeMap();
        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);

        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);
//        System.out.println(map);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }

    // 定製排序
    @Test
    public void test2() {
        TreeMap map = new TreeMap<>(((o1, o2) -> {
            if (o1 instanceof User && o2 instanceof User) {
                User user1 = (User) o1;
                User user2 = (User) o2;

                return Integer.compare(user1.getAge(), user2.getAge());
            }
            throw new RuntimeException("error type");
        }));

        User u1 = new User("Tom", 23);
        User u2 = new User("Jerry", 32);
        User u3 = new User("Jack", 20);
        User u4 = new User("Rose", 18);


        map.put(u1, 98);
        map.put(u2, 89);
        map.put(u3, 76);
        map.put(u4, 100);
//        System.out.println(map);
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + "-->" + entry.getValue());
        }
    }
}
