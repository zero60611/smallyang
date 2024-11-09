package com.smallyang.java;

/**
 * @author USER
 * @date 2024-10-24 上午 05:42
 */
public class MyInterfaceImpl implements MyInterface{
    @Override
    public void methodAbstract() {

    }

//    @Override
//    public void methodDefault() {
////        MyInterface.super.methodDefault();
//        System.out.println("重寫啦~");
//
//    }

    public static void main(String[] args) {
        // 接口中的靜態方法只能由接口自己來調用
        MyInterface.methodStatic();

        // 接口的實現類不能調用接口的靜態方法
//        MyInterfaceImpl.methodStatic();
        MyInterfaceImpl impl = new MyInterfaceImpl();
        impl.methodDefault();

        // 接口中的私有方法不能在接口外部被調用
        // 'methodPrivate()' has private access in 'com.smallyang.java.MyInterface'
//        impl.methodPrivate();

    }
}
