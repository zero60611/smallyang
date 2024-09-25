package com.smallyang.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author USER
 * @date 2024-05-22 下午 10:30
 */
public class PropertiesTest {

    //Properties:常用來處理配置文件。key和value都是String
    public static void main(String[] args) throws Exception {
        FileInputStream fis = null;
        try {
            Properties props = new Properties();
            fis = new FileInputStream("jdbc.properties");
            // 加載流對應的文件
            props.load(fis);
            String name = props.getProperty("name");
            String password = props.getProperty("password");
            System.out.println(name + ":" + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
