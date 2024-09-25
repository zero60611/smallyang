package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author USER
 * @date 2024-09-01 上午 11:51
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        // 對於自定義類，使用系統類加載器進行加載
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);// 得到系統類加載器
        //調用系統類加載的getParent():獲取負責加載平臺類和模塊類的類加載器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        // 調用加載平臺類和模塊類的類加載器的getParent():無法獲取到引導類加載器
        // 引導類加載器主要負責加載java的核心類庫，無法加載自定義類的。
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);// 還有，但我們沒辦法獲取到。

        // 這邊可以加載到引導類加載器核心類庫，但是無法獲取引導類加載器的。
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);

    }

    /*
      Properties:用來讀取配置文件
     */
    @Test
    public void test2() throws IOException {
        Properties props = new Properties();
        // 此時的文件預設在當前的module下。
        // 讀取配置文件的方式一：
//        FileInputStream fis = new FileInputStream("jdbc.properties");
        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
        props.load(fis);

        // 讀取配置文件的方式二：使用系統類加載器進行加載
        // 配置文件預設識別為：當前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        // java.lang.NullPointerException: inStream parameter is null
        // 這邊檔案要在src底下
//        InputStream is = classLoader.getResourceAsStream("jdbc1.properties");
//        props.load(is);

        String user = (String) props.get("user");
        String password = (String) props.get("password");
        System.out.println("user is " + user + " and password is " + password);

    }


}
