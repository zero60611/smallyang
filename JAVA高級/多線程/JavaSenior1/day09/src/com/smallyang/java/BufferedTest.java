package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author USER
 * <p>
 * 處理流之一：緩衝流的使用
 * <p>
 * 1. 緩衝流
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 * <p>
 * 2. 作用：提供流的讀取、寫入的速度
 * 提高讀寫速度的原因：內部提供了一個緩衝區
 * <p>
 * 3. 處理流，　就是＂套接＂在已有的流的基礎上。
 * @date 2024-07-24 上午 11:48
 */
public class BufferedTest {

    /*
        實現緋聞本文件的複製

     */

    @Test
    public void bufferedStreamTest() {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1. 造文件
            File srcFile = new File("AAA.jpg");
            File destFile = new File("FFF.jpg");

            // 2. 造流
            // 2.1 造節點流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 2.2 造緩衝流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3. 複製的細節:讀取、寫入的過程
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer);

                bos.flush();//刷新緩衝區 在緩衝區還沒塞滿自動flush前就自己手動清空緩衝區
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 資源關閉
            // 要求：先關閉外層的流，再關閉內層的流
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            long end = System.currentTimeMillis();
            System.out.println("buffer時間為:" + (end - start));
        }


        // 說明： 關閉外層流的同時，內層流也會自動地進行關閉，
        //      關於內層流的關閉，我們可以省略。
//        fos.close();
//        fis.close();
    }

    public void copyFileWithBuffered(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            // 1. 造文件
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            // 2. 造流
            // 2.1 造節點流
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 2.2 造緩衝流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            // 3. 複製的細節:讀取、寫入的過程
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 資源關閉
            // 要求：先關閉外層的流，再關閉內層的流
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        // 說明： 關閉外層流的同時，內層流也會自動地進行關閉，
        //      關於內層流的關閉，我們可以省略。
//        fos.close();
//        fis.close();
    }

    @Test
    public void testCopyFileWithBuffered() {
        long start = System.currentTimeMillis();
//        String srcPath = "C:\\Users\\USER\\Desktop\\test.jpg";
//        String destPath = "C:\\Users\\USER\\Desktop\\test1.jpg";

        // 直接寫出去就沒問題了，但如果在內存中看byte不夠大可能就亂碼，在內存讀的話還是用字符
        String srcPath = "D:\\Digmon\\DigimonInterface\\[KTXP][Digimon Adventure Last Evolution Kizuna][BIG5][1080p][BDrip][HEVC].mkv.mkv";
        String destPath = "D:\\Digmon\\DigimonInterface\\[KTXP][Digimon Adventure Last Evolution Kizuna][BIG5][1080p][BDrip][HEVC]_1.mkv";
        copyFileWithBuffered(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("Buffered複製操作花費的時間為:" + (end - start));
    }

    /*
        使用BufferedReader和BufferedWriter實現文本的複製

     */
    @Test
    public void testBufferedReaderBufferedWriter() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //  創建文件和相應的流
            br = new BufferedReader(new FileReader(new File("hello.txt")));
            // 預設後面會有false，如果有相同文件就會覆蓋。
            bw = new BufferedWriter(new FileWriter(new File("hello_11.txt")));

            // 讀寫操作
            // 方式一: 使用char[]數組
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1) {
//                bw.write(cbuf, 0, len);
//    //            bw.flush(); // 不用非得調用flush()
//            }

            // 方式二: 使用String
            String data;
            while ((data = br.readLine()) != null) {
//                方法一:
//                bw.write(data + "\n");// data中不包含換行符，一種方式在後面加換行
                // 方法二:
                bw.write(data);
                bw.newLine();// 提供換行操作 這個比較好
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 關閉資源
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }



    }
}
