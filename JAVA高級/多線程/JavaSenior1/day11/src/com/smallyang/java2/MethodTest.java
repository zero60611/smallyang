package com.smallyang.java2;

import com.smallyang.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 獲取運行時類的方法結構
 *
 * @author USER
 * @date 2024-09-06 上午 09:06
 */
public class MethodTest {

    @Test
    public void test1() {
        Class clazz = Person.class;

        // getMethods():獲取當前運行時類及其所有父類中聲明為public權限的方法
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        System.out.println();

        // getDeclaredMethods():獲取當前運行時類中聲明的所有方法(不含父類聲明)
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            System.out.println(m);
        }
    }

    /*
        應用：框架 = 註解+反射+設計模式

        @XXXX
        權限修飾符  返回值類型  方法名(參數類型1 形參名1, ....) throw XXXException{
        }
      */
    @Test
    public void test2() {
        Class clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {
            // 1. 獲取方法聲明的註解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }

            // 2. 權限修飾符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            // 3. 返回值類型
            System.out.print(m.getReturnType().getName() + "\t");

            // 4. 方法名
            System.out.print(m.getName());
            System.out.print("(");
            // 5. 形參列表
            Class[] parameterTypes = m.getParameterTypes();
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                for (int i = 0; i < parameterTypes.length; i++) {
                    if (i == parameterTypes.length - 1) {

                        System.out.print(parameterTypes[i].getName() + " arg_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + " arg_" + i + ",");

                }
                System.out.print(")");

                Class[] exceptionTypes = m.getExceptionTypes();

                // getExceptionTypes() 方法即使沒有拋出任何異常，會返回一個空的 Class[]，所以以下的判斷沒有用 false true 整體仍為false，外層相反的判斷會讓if進去
                //                if (!(exceptionTypes == null && exceptionTypes.length == 0)) {
//                    System.out.println("throws ");
//                    for (int i = 0; i < exceptionTypes.length; i++) {
//                        if (i == exceptionTypes.length - 1) {
//                            System.out.println(exceptionTypes[i].getName());
//                            break;
//                        }
//
//                        System.out.println(exceptionTypes[i].getName() + "i");
//                    }
//                }
                if (exceptionTypes.length != 0) {
                    System.out.println("throws ");
                    for (int i = 0; i < exceptionTypes.length; i++) {
                        if (i == exceptionTypes.length - 1) {
                            System.out.println(exceptionTypes[i].getName());
                            break;
                        }

                        System.out.println(exceptionTypes[i].getName() + "i");
                    }
                }

            }

            System.out.println();

        }


    }
}
