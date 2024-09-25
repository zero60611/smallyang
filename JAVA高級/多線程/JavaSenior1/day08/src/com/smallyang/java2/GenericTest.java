package com.smallyang.java2;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 1. 泛型在繼承方面的體現
 * <p>
 * <p>
 * 2. 通配符的使用
 *
 * @author USER
 * @date 2024-06-09 上午 09:22
 */
public class GenericTest {

    /*
        1. 泛型在繼承方面的體現
        
            類A是類B的父類，但是G<A>和G<B>二者不具備子父類關係，二者為並列關係。
            補充：類A是類B的父類，A<G> 是 B<G> 的 父類


     */
    @Test
    public void test1() {
        Object obj = null;
        String str = null;

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;

        // 邊譯不通過
//        Required type:
//        String
//        Provided:
//        Date
//        Date date = new Date();
//        str = date;


        // 邊譯不通過
        // 不具備子父類關係
//        Required type:
//        List
//                <Object>
//        Provided:
//        List
//                <String>
        List<Object> list1 = null;
        List<String> list2 = new ArrayList<>();
        // 此時的list1 和 list2類型不具有子父類關係
//        list1 = list2;
        /*
            反證法:
            假設list1 = list2
            list1.add(123); 導致混入非Strring的數據。出錯。
         */
        show(list1);
//        Required type:
//        List
//                <Object>
//        Provided:
//        List
//                <String>
//        show(list2);
        show1(list2);

    }

    public void show(List<Object> list) {

    }

    public void show1(List<String> list) {

    }


    @Test
    public void test2() {
        AbstractList<String> list1 = null;
        List<String> list2 = null;// 最上面的父類
        ArrayList<String> list3 = null;
        list1 = list3;
        list2 = list1;
        list2 = list3;
//        list3 = list1;
        // 概念就像
        // 父類  :  子類
        List<String> list = new ArrayList<>();
    }

    /*
        2. 通配符的使用
            通配符: ?

            類A是類B的父類，G<A>和G<B>是沒有關係的，二者共同的父類是：G<?>
     */
    @Test
    public void test3() {
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;
        list = list1;
        list = list2;
//        print(list1);
//        print(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        // 添加(寫入): 對於List<?>就不能向其內部添加數據
        // 除了添加null外
//        list.add("DD");
//        list.add('?');

        list.add(null);

        //  獲取(讀取): 允許讀取數據，讀取的數據類行為Object
        Object o = list.get(0);
        System.out.println(o);


    }

    public void print(List<?> list) {
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
        3. 有限制條件的通配符的使用
            ? extends A:
                G<? extends A>可以做為G<A>和G<B>的父類，其中B是A的子類
            ? super A:
                 G<? super A>可以做為G<A>和G<B>的父類，其中B是A的父類

                    >=的意思
     */
    public void test4() {
        List<? extends Person> list1 = null;
        List<? super Person> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person> list4 = new ArrayList<Person>();
        List<Object> list5 = new ArrayList<Object>();

        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        // 讀取
        list1 = list3;
        Person p = list1.get(0);
        // 編譯不通過
//        Student s = list1.get(0);

        list2 = list4;
        Object object = list2.get(0);
//        Person person = list2.get(0);

        // 寫入數據
        // 編譯不通過
//        list1.add(new Student());
        list2.add(new Person());
        list2.add(new Student());
    }

}
