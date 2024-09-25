package com.smallyang.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 動態代理的舉例
 *
 * @author USER
 * @date 2024-09-19 上午 05:47
 */
// 接口定義為一個規範
interface Human {

    String getBelief();

    void eat(String food);
}

// 被代理類
class SuperMan implements Human {

    @Override
    public String getBelief() {
        return "i believe i can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜歡吃" + food);
    }
}

class HumanUtil{
    public void method1 () {
        System.out.println("**********通用方法一**********");
    }
    public void method2 () {
        System.out.println("**********通用方法二**********");
    }
}


/*
    要想實現動態代理，需要解決的問題?
    問題一:如何根據加載到內存忠的被代理類，動態的創見一個代理類及其對象。
    問題二:當通過代理類的對象調用方法時，如何動態的去調用被代理類中的同名方法。
 */

// 代理類對象:代理類建造工廠
class ProxyFactory {
    // 調用此方法:返回一個代理類的對象。解決問題一
    public static Object getProxyInstance(Object obj) {// obj:被代理類的對象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);
        // 生成代理類對象
        // 幫我們動態的建代理類的對象            superMan    加載器                       接口
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
    }
}

class MyInvocationHandler implements InvocationHandler {
    private Object obj;// 需要使用被代理類的對象進行賦值
    public void bind(Object obj) {
        this.obj = obj;
    }

    // 當我們通過代理類的對象，調用方法a時，就會自動調用如下的方法：invoke()
    // 將被代理類要執行的方法a的功能聲明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        HumanUtil util = new HumanUtil();
        // AOP原理：動態代理的功用方法一
        util.method1();

        // 這方法的使用是藉由哪一個對象，以及裡面是什麼參數
        // method:即為代理類對象調用的方法， 此方法也就做為了被代理類對象要調用的方法。
        // obj:被代理類的對象
        Object returnValue = method.invoke(obj, args);


        // AOP原理：動態代理的功用方法二
        util.method2();

        // 上述方法的返回值就做為當前類中的invoke()的返回值
        return returnValue;
    }
}


public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        // proxyInstance:代理類的對象
        // 這樣就跟靜態不一樣，不需要建造一對伊關係，可以一對多，因此找不到一個專屬代理類
        Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
        // 下面方法都是MyInvocationHandler的invoke調用
        // 當通過代理類對象，調用的這兩個方法，會自動執行調用都是被代理類中的同名方法，
        /*
        getBelief中間沒東西，所以調用通用方法中間夾帶的沒有東西
         **********通用方法一**********
         **********通用方法二**********
         */


        String belief = proxyInstance.getBelief();
        /*
        eat方法有書衝內容，所以這個打印才有東西
        **********通用方法一**********
        我喜歡吃漢堡
        **********通用方法二**********
         */
        proxyInstance.eat("漢堡");
        System.out.println(belief);

        System.out.println("*******************************************");

        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyInstance1.produceCloth();
    }
}
