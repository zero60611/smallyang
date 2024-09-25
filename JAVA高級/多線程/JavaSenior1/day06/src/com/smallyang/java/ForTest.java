package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 5.0 新增了foreach循環，用於遍歷集合、數組
 *
 * @author USER
 * @date 2024-04-26 上午 05:56
 */
public class ForTest {

    @Test
    public void test1() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        //for(集合中元素的類型 局部變量 : 集合對象)
        //內部仍然調用了迭代器。
        for (Object obj : coll) {
            System.out.println(obj);
        }
    }

    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        //for(數組元素的類型 局部變量 : 數組對象)
        for (int i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void test3() {
        String[] arr = new String[]{"MM", "MM", "MM"};

        //方式一:普通for賦值
//        for(int i = 0; i<arr.length;i++) {
//            arr[i] = "GG";
//        }

        //方式二:增強for循環
        for (String s : arr) {
            s = "GG";//因為s是新的變量，因此陣列裡面的值是不變的，但是上面的是用陣列每個元素去改值，所以上面的才會變
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
