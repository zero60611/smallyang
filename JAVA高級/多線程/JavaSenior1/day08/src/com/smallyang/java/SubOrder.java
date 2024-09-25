package com.smallyang.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 * @date 2024-06-08 上午 09:54
 */
public class SubOrder extends Order<Integer>{// SubOrder1：不再是泛型類
    public static  <E> List<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e: arr) {
            list.add(e);
        }
        return list;
    }
}
