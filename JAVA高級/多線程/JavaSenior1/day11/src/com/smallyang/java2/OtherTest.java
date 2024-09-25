package com.smallyang.java2;

import com.smallyang.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author USER
 * @date 2024-09-08 上午 10:30
 */
public class OtherTest {

    /*
        獲取構造氣器結構
     */
    @Test
    public void test1() {
        Class clazz = Person.class;
        // getConstructors():獲取當前運行時類中聲明為public的構造器
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor c : constructors){
            System.out.println(c);
        }
        System.out.println();

        // getDeclaredConstructors():獲取當前運行時類中聲明的所有的構造器
        Constructor[] constructors1 = clazz.getDeclaredConstructors();
        for (Constructor c : constructors1){
            System.out.println(c);
        }
    }

    /*
        獲取運行時類的父類
     */
    @Test
    public void test2() {
        Class clazz = Person.class;
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
//        System.out.println(superclass.getSuperclass());

    }

    /*
    獲取運行時類的帶泛型的父類
 */
    @Test
    public void test3() {
        Class clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
    }

    /*
        獲取運行時類的帶泛型的父類的泛型

        代碼： 邏輯性代碼  vs  功能性代碼
     */
    @Test
    public void test4() {
        Class clazz = Person.class;
        Type genericSuperclass = clazz.getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        // 獲取泛型類型
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        System.out.println(genericSuperclass);
//        System.out.println(actualTypeArguments[0].getTypeName());
        System.out.println(((Class)actualTypeArguments[0]).getName());
    }

    /*
        獲取運行時類實現的接口
     */
    @Test
    public void test5() {
        Class clazz = Person.class;

        Class[] interfaces = clazz.getInterfaces();
        for(Class c : interfaces) {
            System.out.println(c);
        }

        System.out.println();
        // 獲取運行時類的父類實現的接口
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        for(Class c : interfaces1) {
            System.out.println(c);
        }
    }

    /*
        獲取運行時類所在的包
     */
    @Test
    public void test6() {
        Class clazz = Person.class;
        Package aPackage = clazz.getPackage();
        System.out.println(aPackage);
    }
    /*
        獲取運行時類聲明的註解
     */
    @Test
    public void test7() {
        Class clazz = Person.class;
        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation a : annotations) {
            // 通過反射去讀取註解的內容，去判定後面要幹什麼
            System.out.println(a);
        }
    }
    /*
        靜態代理:
            明星:被代理 想實現某些功能
            經紀人:代理 透過代理類來做
        －＞共同實現某個接口

        動態代理:
        不指名代理類是誰，在運行的時候看被代理的人是誰，我動態幫你造一個代理類。
        代理類要造對象的時候，需要拿到被代理類他所實現的接口，然後我也實現的接口，獲取運行時類實現的接口
     */
}
