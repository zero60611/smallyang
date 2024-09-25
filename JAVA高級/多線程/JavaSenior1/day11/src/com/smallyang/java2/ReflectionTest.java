package com.smallyang.java2;

import com.smallyang.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 調用運行時類中指定的結構：屬性、方法、構造器
 *
 * @author USER
 * @date 2024-09-08 上午 11:44
 */
public class ReflectionTest {

    /*
        了解就好
     */
    @Test
    public void testField() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Person.class;

        // 創建運行時類的對象
        Person person = (Person) clazz.getConstructor().newInstance();

        // 獲取指定的屬性：getField():要求運行時類中的屬性聲明為public
        // 通常不採用此方法
//        Field id = clazz.getField("id");
//        Field id = clazz.getField("age");//java.lang.NoSuchFieldException: age
        Field id = clazz.getDeclaredField("age");
        // 設置當前屬性的值
        // set():參數1 指名設置哪個對象的屬性  參數2:將此屬性值設置為多少
        id.set(person, 1001);

        /*
            獲取當前屬性的值
            get():參數1: 獲取哪個對象的當前屬性值


         */
        int id1 = (int) id.get(person);
        System.out.println(id1);
    }

    /*
    如何操作運行時類中的指定屬性-- 需要掌握
     */
    @Test
    public void testField1() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class clazz = Person.class;
        // 創建運行時類的對象
        Person person = (Person) clazz.getConstructor().newInstance();

        // 1. getDeclaredField(String fieldName):獲取運行時類中指定變量名的屬性
        Field name = clazz.getDeclaredField("name");
        // 2. 保證當前屬性是可訪問的
        name.setAccessible(true);// private要去得到存取權才能設值
        // 3. 獲取、設置指定對象的此屬性值
        name.set(person, "Tom");
        System.out.println(name.get(person));
    }

    /*
    如何操作運行時類中的指定方法-- 需要掌握
     */
    @Test
    public void testMethod() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Person.class;
        // 創建運行時類的對象
        Person person = (Person) clazz.getConstructor().newInstance();

        /*
           1. 獲取指定的某個方法
             getDeclaredMethod():
             參數1: 指名獲取方法的名稱
             參數2:指名獲取的方法的行參列表(可變形參)

         */
        Method show = clazz.getDeclaredMethod("show", String.class);

        // 2. 保證當前方法是可訪問的
        show.setAccessible(true);
        /*
         3. 調用方法的 invoke():參數1: 方法的調用者   參數2:給方法形參賦值的實參
         invoke()的返回值即為對應類中調用的方法的返回值。
         
         */
        Object returnValue = show.invoke(person, "CNN");// String nation = p.show("CNN");
        System.out.println(returnValue);

        System.out.println("***********如何調用靜態方法******************");

//        showDest()
        Method showDest = clazz.getDeclaredMethod("showDest");
        showDest.setAccessible(true);
        // 如果調用的運行時類中的方法沒有返回值，則此invoke()則返回null
//        Object invoke = showDest.invoke(person);
//        Object invoke = showDest.invoke(Person.class);
        // 本身class就知道static是誰，只有非靜態才需要知道哪一個對象，所以不用傳哪一個對象
        // 這邊可以測試工具類方法
        Object invoke = showDest.invoke(null);
        System.out.println(invoke);// null

    }

    /*
        如何調用運行時類中的指定的構造器
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Person.class;

        /*
            1. 獲取指定的構造器
            getDeclaredConstructor(): 參數: 指名構造器的參數列表
         */
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class);
        System.out.println(declaredConstructor);

        // 2. 保證此構造器是可訪問的
        declaredConstructor.setAccessible(true);

        // 3. 調用此構造器創建運行時類的對象
        Person person = (Person) declaredConstructor.newInstance("Tom");
        System.out.println(person);
    }
}
