package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * 測試 FileInputStream和FileOutputStream的使用
 * <p>
 * 結論：
 * 1. 對於文本文件(.txt,.java,.c,.cpp)，使用字符流處理
 * 2. 對於非文本文件(.jpg, .mp3, .mp4, .avi, .doc, .ppt, ....)使用字節流處理
 *
 * @author USER
 * @date 2024-07-24 上午 06:24
 */
public class FileInputOutputStreamTest {

    // 使用字節流 FileInputStream 處理文本文件，可能出現亂碼的。
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            // 1. 造文件
            File file = new File("hello.txt");

            // 2. 造流
            fis = new FileInputStream(file);

            // 3. 讀數據
            byte[] buffer = new byte[5];
            int len;//紀錄每次讀取的字節的個數
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 關閉資源
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /*
        實現對圖片的複製操作
     */
    @Test
    public void testFileInputOutputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            //
            File srcFile = new File("AAA.jpg");
            File destFile = new File("CCC.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 複製的過程
//            byte[] buffer = new byte[5];
            byte[] buffer = new byte[1024];// 1.數字越大，io次數雖然降低，但占用內存多。通常都用1024
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("copy success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void copyFile(String srcPath, String destPath) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            // 複製的過程
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("copy success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Test
    public void testCopyFile() {
        long start = System.currentTimeMillis();
//        String srcPath = "AAA.jpg";
//        String destPath = "XXX.jpg";
        String srcPath = "D:\\Digmon\\DigimonInterface\\[KTXP][Digimon Adventure Last Evolution Kizuna][BIG5][1080p][BDrip][HEVC].mkv.mkv";
        String destPath = "D:\\Digmon\\DigimonInterface\\[KTXP][Digimon Adventure Last Evolution Kizuna][BIG5][1080p][BDrip][HEVC]_2.mkv";
        // 直接寫出去就沒問題了，但如果在內存中看byte不夠大可能就亂碼，在內存讀的話還是用字符
//        String srcPath = "hello.txt";
//        String destPath = "hello3.txt";
        copyFile(srcPath, destPath);

        long end = System.currentTimeMillis();

        System.out.println("FileOutput複製操作花費的時間為:" + (end - start));

    }



    /*
    Files.copy 是 Java 7 引入的 NIO (New I/O) API 中的一部分，它提供了更高效和便捷的文件操作方法。
    StandardCopyOption.REPLACE_EXISTING 表示如果目標文件已存在，則替換它。
     */
    public static void copyFile2(String srcPath, String destPath) {
        Path source = new File(srcPath).toPath();
        Path destination = new File(destPath).toPath();

        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy success");
        } catch (IOException e) {
            System.err.println("Copy failed: " + e.getMessage() + "-->" + e);
        }
    }

    public static void main(String[] args) {
        // 這邊要寫詳細路徑
        String srcPath = "C:\\Users\\USER\\Desktop\\test.jpg";
        String destPath = "C:\\Users\\USER\\Desktop\\test1.jpg";
        copyFile2(srcPath, destPath);
    }

    public void copyFileByBuffer(String srcPath, String destPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destPath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();  // 保持行尾格式一致
            }
            System.out.println("Copy success");
        } catch (IOException e) {
            System.err.println("Copy failed: " + e.getMessage());
        }
    }
}
