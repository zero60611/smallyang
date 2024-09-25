package com.smallyang.exer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author USER
 * @date 2024-05-01 下午 04:21
 */
public class ListExer {

    /*
        區分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testListRemove(){
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

//        list.remove(2); // index=2

        list.remove(Integer.valueOf(2));// 移除物件 2
        System.out.println(list);


    }
}
