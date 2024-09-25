package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍歷操作，使用迭代器Iterator接口
 * 1. 內部的方法:hasNext() 和 next()
 *
 * 2. 會不斷無窮迴圈，因為.iterator()都會返回一個新的迭代器對象，
 *    預設游標都在集合的第一個元素之前。
 *
 * 3. 內部定義了remove(),可以在遍歷的時候，刪除集合中的元素，此方法不同於集合直接調用remove()
 *
 *
 * @author USER
 * @date 2024-04-24 下午 10:09
 */
public class IteratorTest {
    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        Iterator iterator = coll.iterator();
        // 方式一:
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
        // 報異常:.NoSuchElementException
//        System.out.println(iterator.next());

        // 方式二: 不推薦
//        for (int i = 0; i < coll.size(); i++) {
//            System.out.println(iterator.next());
//        }

        // 方式三:
        // hasNext():判斷是否有下一個元素
        while (iterator.hasNext()){
            // next():1.指針下移  2.將下移以後集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        // 錯誤方式一:
        // NoSuchElementException
//        Iterator iterator = coll.iterator();
//        while ((iterator.next()) != null) {
//            System.out.println(iterator.next());
//        }

        // 錯誤方式二:會不斷無窮迴圈，因為.iterator()都會返回一個新的迭代器對象，這樣會一直next
        // 預設游標都在集合的第一個元素之前。
        while (coll.iterator().hasNext()) {
            coll.iterator().next();
        }
    }

    // 測試Iterator 的remove()
    // 如果還未調用next()或在上一次調用next方法之後已經調用了remove方法，再調用remove都會報IllegalStateException
    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        //  刪除集合中"Tom"的數據
        Iterator iterator = coll.iterator();
        while (iterator.hasNext()) {
            // .IllegalStateException，指針還沒下來肯定報錯
//            iterator.remove();
            Object obj = iterator.next();
            if("Tom".equals(obj)) {
                iterator.remove();
//                iterator.remove();// 指針還沒下來肯定報錯
            }
        }

        //遍歷集合
        iterator = coll.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
