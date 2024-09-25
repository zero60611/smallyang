package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author USER
 * @date 2024-08-24 上午 10:55
 */
public class ReflectionTest {

    // 反射之前，對於Person的操作
    @Test
    public void test1() {
        // 1. 創建Person類的對象
        Person p1 = new Person("Tom", 12);

        // 2. 通過對象，調用其內部的屬性、方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        // 在Person類外部，不可以通過Person類的對象調用其內部私有結構。
        // 比如: name、showNation()以及私有構造器
    }

    // 反射之後，對於Person的操作
    @Test
    public void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Person.class;
        // 1. 通過反射，創建Person類的對象
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Object obj = constructor.newInstance("Tom", 12);
        Person p = (Person) obj;
        System.out.println(p.toString());
        // 2. 通過反射，調用對象指定的屬性、方法
        // 調用屬性
        Field age = clazz.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());

        // 調用方法
        // 這是空參的方法
        Method show = clazz.getMethod("show");
        // 這個方法由p 調用
        show.invoke(p);
        System.out.println("************************************");
        // 通過反射，可以調用Person類的私有結構的。比如: 私有的構造器、方法、屬性
        // 調用私有的構造器
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
        constructor1.setAccessible(true);
        Person person1 = (Person) constructor1.newInstance("Jerry");
        System.out.println(person1);

        // 調用私有的屬性跟方法
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        // 直接通過對象來設置屬性
        name.set(person1, "tomcat");
        System.out.println(person1);

        // 調用私有的方法
        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(person1, "Taiwan");
        System.out.println(nation);
    }

    //  疑問? 通過直接new的方式或反射的方式都可以調用公共的結構，開發中到底用哪個?
    //  建議：直接new的方式。
    //  什麼時候會使用：反射的方式。反射特徵：動態性(不排除編譯時不清楚要造哪個類的對象)
    //  舉例: 一開始瀏覽器不知道要註冊還登入，把url /login傳到後台， 後台一看是login就造login的對象，實際上是一個servlet，這是一個動態，這時候需要用反射來做
    //  疑問? 反射機制與面向對象的封裝性是不是矛盾?如何看待這兩個技術?
    //  不矛盾。  封裝性:建議怎麼調用 反射：解決能不能調的問題


    /*
      關於java.lang.Class類的理解
      1. 類的加載過程
        程序經過javac.exe命令以後，會生成一個或多個字節碼文件(.class結尾)。
        接著我們使用java.exe命令對某個字節碼文件進行解釋運行。相當於將某個字節碼文件加載到內存中。
        此過程就稱為類的加載。加載到內存中的類，我們就稱為運行時類，
        此運行時類，就作為Class的一個實例。


      2.換句話說，Class的實例就對應著一個運行時類。
      3.加載到內存中的運行時類後，實際上會緩存一段時間，在此時間之內，我們可以通過不同的方式來獲取此運行時類
     */

    // 獲取Class的實例的方式(前三種方式需要去掌握)
    @Test
    public void test3() throws ClassNotFoundException {
        // 方式一：調用"運行時類"的屬性: .class
        Class clazz1 = Person.class;
        System.out.println(clazz1);

        // 方式二：通過"運行時類"的對象，調用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);

        // 方式三： 調用Class的靜態方法: forName(String classPath)
        Class clazz3 = Class.forName("com.smallyang.java.Person");
//        clazz3 = Class.forName("java.lang.String");
        System.out.println(clazz3);

        // 都是獲取同一個 "運行時類"
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz1 == clazz3);

        // 方式四： 使用類的加載器：ClassLoader (了解)
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("com.smallyang.java.Person");
        System.out.println(clazz4);
        System.out.println(clazz1 == clazz4);
    }

    // 萬事萬物皆對象?對象.xxx,File,URL,反射,前端,數據庫操作...

    //  除了對應運行時類 還有以下
    // Class實例可以是那些結構的說明：
    @Test
    public void test4() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c4 = String[].class;
        Class c5 = int[][].class;
        Class c6 = ElementType.class;
        Class c7 = Override.class;
        Class c8 = int.class;
        Class c9 = void.class;
        Class c3 = Class.class;
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c4);
        System.out.println(c5);
        System.out.println(c5);
        System.out.println(c6);
        System.out.println(c7);
        System.out.println(c8);
        System.out.println(c9);

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要元素類型與維度一樣，就是同一個Class
        System.out.println(c10 == c11);


    }

}





//import java.lang.reflect.Method;
//
//public class ActionDispatcher {
//    public void dispatch(String actionName, String methodName) {
//        try {
//            // 通過反射加載指定的類
//            Class<?> actionClass = Class.forName(actionName);
//            Object actionInstance = actionClass.getDeclaredConstructor().newInstance();
//
//            // 通過反射找到指定的方法並調用它
//            Method method = actionClass.getMethod(methodName);
//            method.invoke(actionInstance);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
//
//public class LoginAction {
//    public void login() {
//        System.out.println("Login action executed!");
//    }
//}
//
//// 使用範例
//public class Main {
//    public static void main(String[] args) {
//        ActionDispatcher dispatcher = new ActionDispatcher();
//        dispatcher.dispatch("LoginAction", "login");  // 將動態調用 LoginAction 的 login 方法
//    }
//}
