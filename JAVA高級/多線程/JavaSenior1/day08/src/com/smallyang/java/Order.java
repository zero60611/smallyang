package com.smallyang.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定義的泛型類
 *
 * @author USER
 * @date 2024-06-05 下午 12:12
 */
public class Order<T> {
    String orderName;
    int orderId;

    // 類的內部結構就可以使用類的泛型
    T orderT;

    public Order() {
        // Type parameter 'T' cannot be instantiated directly
//        T[] arr = new T[10];
        // 編譯通過
        T[] arr = (T[]) new Object[10];

    }

    public Order(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;

    }

    // 下面三個方法都不是泛型方法
    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderName='" + orderName + '\'' +
                ", orderId=" + orderId +
                ", orderT=" + orderT +
                '}';
    }

    // Non-static field 'orderT'
    // cannot be referenced from a static context
//    public static void show() {
//        System.out.println(orderT);
//    }
    public void show() {
        try {

        } catch (Exception e) {
            // 編譯不通過
//        } catch (T t) {
//            Required type: Throwable
//            Provided: T
        }
    }

    // 泛型方法:　在方法中出現了泛型的結構，泛型參數與類的泛型參數沒有任何關係。
    // 換句話說，泛型方法所屬的類是不是泛型類都沒有關係
    // 泛型方法可以聲明為靜態的，原因:泛型參數是在調用方法時確定的，並非在
    //  實例化類時確定
//    public <E> List<E> copyFromArrayToList(E[] arr) {
//        ArrayList<E> list = new ArrayList<>();
//        for (E e: arr) {
//            list.add(e);
//        }
//        return list;
//    }
    public static  <E> List<E> copyFromArrayToList(E[] arr) {
//    public <E> List<E> copyFromArrayToList(E[] arr) {
        ArrayList<E> list = new ArrayList<>();
        for (E e: arr) {
            list.add(e);
        }
        return list;
    }
}
