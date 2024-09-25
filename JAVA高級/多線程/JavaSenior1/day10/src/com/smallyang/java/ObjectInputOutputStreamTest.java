package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 對象流的使用
 * 1. ObjectInputStream and ObjectOutputStream
 * 2. 作用：
 * 用於存儲和讀取基本數據類型或對象的處理流，它的強大之處就是可以把Java中的對象寫入到數據源中，也能把對象從數據源中還原回來。
 *
 * 3. 要想一個Java對象可序列化的，需要滿相應的要求。見Person.java
 *
 *
 * @author USER
 * @date 2024-08-04 上午 11:29
 */
public class ObjectInputOutputStreamTest {

    /*
        序列化過程：將內存中的Java對象保存到硬碟中獲通過網路傳輸出去
        使用ObjectOutputStream實現
     */
    @Test
    public void testObjectOutputStream() {
        ObjectOutputStream oos = null;
        try {
            //1.
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //2.
            oos.writeObject(new String("我愛廖小暄"));
            oos.flush();//刷新操作


            oos.writeObject(new Person("name", 23));
            oos.writeObject(new Person("smallyang", 23, new Account(5000)));
            oos.flush();//刷新操作

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(oos != null) {
                try {
                    //3.
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /*
     反序列化： 將硬碟文件中或網路流的對象還原為內存中的一個Java對象
     使用ObjectInputStream來實現。
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            //1.
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object o = ois.readObject();
            String o1 = (String) o;


            Person o2 = (Person) ois.readObject();
            Person o3 = (Person) ois.readObject();
            System.out.println(o1);
            System.out.println(o2);
            System.out.println(o3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


}
