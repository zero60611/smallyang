package com.smallyang.exer;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author USER
 * @date 2024-07-27 上午 07:52
 */
public class PicTest {
    // 圖片的加密
    @Test
    public void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("AAA.jpg");
            fos = new FileOutputStream("AAA_Secret.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                // 字節byte數組進行修改
                // 這寫法不行，因為只是把buffer數組的變量給一個新的宣告的byte b
                // 基本上數組仍然沒有被修正，下面write不會有變動
    //            for (byte b : buffer) {
    //                b = (byte) (b ^ 5);
    //            }

                // 正確的
                for(int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }


    // 圖片的解密
    @Test
    public void test2() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("AAA_Secret.jpg");
            fos = new FileOutputStream("AAA_deSecret.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                // 字節byte數組進行修改
                // 這寫法不行，因為只是把buffer數組的變量給一個新的宣告的byte b
                // 基本上數組仍然沒有被修正，下面write不會有變動
                //            for (byte b : buffer) {
                //                b = (byte) (b ^ 5);
                //            }

                // 正確的
                for(int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
