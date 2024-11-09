package com.smallyang.java;

/**
 * @author USER
 * @date 2024-10-24 上午 05:35
 */
public interface MyInterface {
    // 如下三個方法的權限修飾符都是public
    void methodAbstract();

    static void methodStatic() {
        System.out.println("interface static method");
    }

    default void methodDefault() {
        System.out.println("interface default method");
        methodPrivate();
    }

    // jdk9中允許interface定義private method
    private void methodPrivate() {
        System.out.println("interface private  default");
    }
}
