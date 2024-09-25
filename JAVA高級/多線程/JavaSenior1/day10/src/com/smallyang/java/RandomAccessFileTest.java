package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1. RandomAccessFile直接繼承於java.lang.Object，實現了
 * DataInput、DataOut接口
 * 2. RandomAccessFile既可以作為一個輸入流、又可以作為一個輸出流。
 * 3. 如果RandomAccessFile作為輸出流，寫出到的文件不存在，則執行過程中自動創建。
 * 如果寫出到的文件存在，則會對原有文件內容進行覆蓋(預設情況下是從頭覆蓋)
 *
 * 4. 可以通過相關的操作，實現RandomAccessFile"插入"數據的效果
 *
 * @author USER
 * @date 2024-08-06 上午 06:03
 */
public class RandomAccessFileTest {

    @Test
    public void test1() throws FileNotFoundException {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //1.
            raf1 = new RandomAccessFile(new File("AAA.jpg"), "rw");
            raf2 = new RandomAccessFile(new File("XXXS.jpg"), "rw");

            //2.
            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer)) != -1) {
                raf2.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //3.
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }

    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        long length = raf1.length();// 可以往後遞增 找出長度就好
        raf1.seek(3);// 將指針調到角標3的位置
        raf1.write("RRIIOO".getBytes());
        raf1.close();
    }

    /*
        RandomAccessFile實現數據的插入效果
     */
    @Test
    public void test3() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt", "rw");
        raf1.seek(3);// 將指針調到角標3的位置

        // 保存指針3後面的數據都保存在StringBuilder裡面了
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buffer = new byte[20];
        int len;
        // 從指標3開始append
        while ((len = raf1.read((buffer))) != -1) {
            builder.append(new String(buffer, 0, len));
        }

        // 調回指針，寫入數據
        raf1.seek(3);
        raf1.write("廖小羊".getBytes());

        // 將StringBuilder中的數據寫入到文件中
        raf1.write(builder.toString().getBytes());
        raf1.close();

        // 思考:將StringBuilder替換為ByteArrayOutputStream
    }
}
