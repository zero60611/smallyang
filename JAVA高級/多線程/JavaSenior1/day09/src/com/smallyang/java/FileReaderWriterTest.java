package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 一、流的分類
 * 1. 操作數據單位: 字節流、字符流
 * 2. 數據的流向: 輸入流、輸出流
 * 3. 流的角色: 節點流、處理流
 * <p>
 * 二、流的體系結構
 * 抽象基類         節點流                                         緩衝流(調用flush，內存中的數據都會寫出去)
 * InputStream    FileInputStream(read(byte[] buffer)            BufferedInputStream(read(byte[] cbuf)
 * OutputStream   FileOutputStream(write(byte[] buffer, 0, len)  BufferedOutputStream(write(byte[] buffer, 0, len/ flush())
 * Reader         FileReader(read(char[] cbuf)                   BufferedReader(read(char[] cbuf/ readLine())
 * Writer         FileWriter(write(char[] cbuf, 0, len)          BufferedWriter(write(char[] cbuf, 0, len/ flush())
 *
 * @author USER
 * @date 2024-07-17 上午 05:54
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");// 相較於當前工程
        // \JavaSenior1\hello.txt
        System.out.println(file.getAbsolutePath());

        File file1 = new File("day09\\hello.txt");// 相較於當前工程
        //\JavaSenior1\day09\hello.txt
        System.out.println(file1.getAbsolutePath());


    }

    /*
        將day09下的hello.txt文件內容讀入程序中，並輸出到控制台
        說明點：
        1. read()的理解: 返回讀入的一個字符，如果達到文件末尾，返回-1
        2. 異常的處理: 為了保證流資源一定可以執行關閉操作，需要使用try-catch-finally處理
        3. 讀入的文件一定要存在，否則就會報FileNotException。

     */
    @Test
    public void testFileReader() {
        FileReader fr = null;
        try {
            // 1. 實例化File類的對象，指名要操作的文件
            File file = new File("hello.txt");// 相較於當前Module
            // \JavaSenior1\day09\hello.txt
//        System.out.println(file.getAbsolutePath());

            //2. 提供具體的流
            fr = new FileReader(file);

            //3. 數據的讀入
            //read():返回讀入的一個字符，如果達到文件末尾，返回-1
            // 方式一：
//        int data = fr.read();
//        while (data != -1) {
////            System.out.print(data);
//            System.out.print((char) data);
//            data = fr.read();
//        }
            // 方式二： 語法上針對方式一的修改
            int data;
            while ((data = fr.read()) != -1) {
                System.out.println((char) data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. 流的關閉操作
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 或if (fr != null) {包在try外面，都可以
        }

    }

    // 對read()操作升級: 使用read的重載方法
    @Test
    public void testFileReader1() {
        FileReader fr = null;
        try {
            // 1.File類的實例化
            File file = new File("hello.txt");
            // 2.FileReader流的實例化
            fr = new FileReader(file);
            // 3.讀入的操作
            // read(char[] cbuf):返回每次讀入cbuf數組中的字符的個數。如果達到
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                // 方式一：
                // 錯誤
//                for(int i = 0; i < cbuf.length; i++) {
//                    System.out.print(cbuf[i]);
//                }

                // 正確的寫法
//                for(int i = 0; i < len; i++) {
//                    System.out.print(cbuf[i]);
//                }

                // 方式二：
                // 錯誤
//                String str = new String(cbuf);
//                System.out.print(str);


                String str = new String(cbuf, 0, len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.資源的關閉
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
        從內存忠寫出數據到硬碟的文件裡

        說明：
        1. 輸出操作，對應的File可以不存在，並不會報異常。
        2.  File對應的硬碟中的文件如果不存在，在輸出的過程中，會自動創建此文件。
            File對應的硬碟中的文件如果存在：
                如果流使用的構造器是： FileWriter(file, false)/FileWriter(file)：對原有文件的覆蓋。
                如果流使用的構造器是： FileWriter(file, true)：不會對原有文件覆蓋，而是在原有文件基礎上追加內容。
     */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            // 1. 提供File類的對象，指名寫出到的文件
            File file = new File("hello1.txt");

            // 2. 提供FileWritter的對象，用於數據的寫出
//        FileWriter fw = new FileWriter(file);
//        FileWriter fw = new FileWriter(file, false);
            fw = new FileWriter(file, true);

            // 3. 寫出的操作
            fw.write("I have a dream!\n");
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fw != null) {
                try {
                    // 4. 流資源的關閉
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // 文件的複製
    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            // 1. 創建File類的對象，指名讀入和寫出的文件
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");


            // 不能使用字符流來處理圖片等字節數據
//            File srcFile = new File("AAA.jpg");
//            File destFile = new File("BBB.jpg");

            // 2. 創建輸入流和輸出流的對象
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);

            // 3. 數據的讀入和寫出操作
            char[] cbuf = new char[5];
            int len;// 紀錄每次讀入到cbuf數組中的字符個數
            while ((len = fr.read(cbuf)) != -1) {
                // 每次寫出len個字符
                fw.write(cbuf, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 方式一：
            try {
                // 4. 關閉流資源
                if(fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    if(fr != null){
                        fr.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            // 方式二：
            try {
                if(fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if(fr != null){
                    fr.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
