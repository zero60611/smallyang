package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 *
 * 處理流之二：　轉換流的使用
 * 1. 轉換流：
 *  InputStreamReader: 將一個字節(byte)的輸入流轉換為字符(char)的輸入流
 *  OutputStreamReader:　 將一個字符(char)的輸入流轉換為字節(byte)的輸出流
 *
 * 2. 作用： 提供字節流與字符流之間的轉換
 *
 * 3.
 * 解碼 InputStreamReader： 字節、字節數組 ---->字符數組、字符串
 * 編碼 OutputStreamReader： 字符數組、字符串 ----> 字節、字節數組
 *
 * 4. 字符集
 *
 *
 * @author USER
 * @date 2024-07-27 上午 09:55
 */
public class InputStreamReaderTest {

    /*
        此時處理異常的話，仍然應該使用try-catch-finally
        InputStreamReader的使用，實現字節的輸入流到字符的輸入流的轉換
     */
    @Test
    public void test1() throws IOException {
        FileInputStream fis = new FileInputStream("hello.txt");
//        InputStreamReader isr = new InputStreamReader(fis);// 使用系統預設的字符集
        // 參數2指名了字符集,具體使用哪個字符集，取決於文件保存時使用的字符集。
//        InputStreamReader isr1 = new InputStreamReader(fis, "UTF-8");// 使用UTF-8的字符集
        InputStreamReader isr1 = new InputStreamReader(fis, "gbk");// 使用UTF-8的字符集

        char[] cbuf = new char[20];
        int len;
        while ((len = isr1.read(cbuf)) != -1){
            String str = new String(cbuf, 0, len);
            System.out.print(str);
        }

//        isr.close();
        isr1.close();

    }


    @Test
    public void test2() throws Exception{
        // 1. 造文件、造流
        File file1 = new File("hello.txt");
        File file2 = new File("hello_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis, "utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos, "gbk");

        // 2. 讀寫過程
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1) {
            osw.write(cbuf, 0, len);
        }

        // 3. 關閉資源
        isr.close();
        osw.close();
    }
}
