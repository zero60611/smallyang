package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author USER
 * @date 2024-05-04 上午 10:38
 */
public class TreeSetTest {

    /*
        1.向TreeSet中添加的數據，要求是相同類的對象。
        2.兩種排序方式:自然排序 和 訂制排序

        //  更嚴格一些
        3.自然排序中，比較兩個對象是否相同的標準為:compareTo()返回0，不再是equals()
        4.定制排序中，比較兩個對象是否相同的標準為:compare()返回0，不再是equals()
     */
    @Test
    public void test1() {
        // 透過compareTo()，底層就不能放相同的數據
        TreeSet set = new TreeSet<>();
        // 失敗: 不能添加不同類的對象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom", 12));

        // 舉例一:
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);
//        String 也可
        // 舉例二:
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Jack", 33));
        set.add(new User("Jack", 56));


        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        Comparator com = new Comparator<>() {
            // 按照年齡從小到大排列
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    int compare = Integer.compare(u1.getAge(), u2.getAge());
                    if(compare == 0) {
                        return u1.getName().compareTo(u2.getName());
                    }

                    return compare;
                } else {
                    throw new RuntimeException("輸入數據的類型不匹配");
                }
            }
        };
        TreeSet set = new TreeSet(com);
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Marry", 33));
        set.add(new User("Jack", 33));
        set.add(new User("Jack", 56));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
