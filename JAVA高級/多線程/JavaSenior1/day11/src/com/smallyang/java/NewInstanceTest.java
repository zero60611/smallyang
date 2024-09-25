package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * 通過反射創建對應的"運行時類"的對象
 *
 * @author USER
 * @date 2024-09-02 上午 06:00
 */
public class NewInstanceTest {

    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Person.class;
        /*
          newInstance: 調用此方法，創建對應的運行時類的對象。內部調用了運行時類的空參構造器

          要想此方法正常的創建運行時類的對象，要求：
          1. 運行時類必須提供空參的構造器
          2. 空參的構造器訪問權限得夠，通常設置為public。

          在javabean中要求提供一個public的空參構造器。原因：
          1. 便於通過反射，創建運行時類的對象。
          2. 便於子類繼承此運行時類，預設調用super()時，保證父類有此構造器。
          
          通過反射造對象的時候都會需要空參構造器

          有的是調方法，有的是new 構造器
          只要造對象 就得用構造器 也只有構造器才能造對象
         */
//        Object instance = clazz.newInstance();// 已被拋棄
        //改成
        /*
          在 Java 9 及以後的版本中，Class.newInstance() 方法已被標記為過時（deprecated），
          並在更高版本中被替換。原因是該方法存在一些固有的缺陷和問題，
          比如無法處理構造函數的異常
          （除了 InstantiationException 和 IllegalAccessException），
          以及它強制要求無參構造函數的存在。

            替代方法
            推薦使用 Constructor.newInstance() 方法來替代 Class.newInstance()。
            這種方法更加靈活，並且能夠更好地處理帶有參數的構造函數。

            這樣做的好處：
            更好的異常處理：Constructor.newInstance()
            可以捕獲所有與構造函數相關的異常，包括 InvocationTargetException，這使得代碼更加健壯。
            支持有參構造函數：
            Constructor.newInstance() 可以方便地調用帶有參數的構造函數。
         */
        Constructor constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();
        System.out.println(instance);

    }

    // 體會反射的動態性，之後框架都是搞這種的，基於框架的通用結構，使用的反射
    @Test
    public void test2() throws Exception {

        for (int i = 0; i < 100; i++) {


            int num = new Random().nextInt(3);// 0 1 2
            String classPath = "";
            System.out.println(num);
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
//                    classPath = "java.sql.Date";// 沒空參構造器 會有異常
                    classPath = "java.lang.Object";// 沒空參構造器 會有異常
                    break;
                case 2:
                    classPath = "com.smallyang.java.Person";
                    break;
            }
            try {
                System.out.println(classPath);
                Object o = getInstance(classPath);
                System.out.println(o);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
      創建一個指定類的對象
      classPath:指定類的全類名
     */
    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);

        return clazz.getConstructor().newInstance();
    }

}
