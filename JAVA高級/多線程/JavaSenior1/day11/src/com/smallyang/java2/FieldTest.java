package com.smallyang.java2;

import com.smallyang.java1.Person;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 獲取當前運行時類的屬性結構
 *
 * @author USER
 * @date 2024-09-06 上午 08:11
 */
public class FieldTest {

    @Test
    public void test1() {
        Class clazz = Person.class;
        // 獲取屬性結構
        // getFields():獲取目前運行時類及其父類中聲明為public訪問權限的屬性
        Field[] fields = clazz.getFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        System.out.println();

        // declaredFields():獲取當前運行時類中聲明的所有屬性(不含父類中聲明的屬性)
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            System.out.println(f);
        }
    }

    // 權限修飾符  數據類型 變量名 = ...
    @Test
    public void test2() {
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields) {
            // 1. 權限修飾符
            int modifiers = f.getModifiers();
            System.out.println(modifiers);
            System.out.println(Modifier.toString(modifiers));
            // 2. 數據類型
            Class<?> type = f.getType();
            System.out.println(type);
            // 3. 變量名
            String name = f.getName();
            System.out.println(name);
            System.out.println();

        }
    }
}
