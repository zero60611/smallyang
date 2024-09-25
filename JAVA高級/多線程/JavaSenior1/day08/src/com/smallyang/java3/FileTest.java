package com.smallyang.java3;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * File類的使用
 * 1. File類的一個對象，代表一個文件或一個文件目錄(俗稱:資料夾)
 * 2. File類聲明在java.io包下
 *
 * @author USER
 * @date 2024-06-19 上午 06:17
 */
public class FileTest {
    /*
        1. 如何創建File類的實例
            File(String filePath)
            File(String parentPath, String childPath)
            File(File  parentFile, String childPath)

        2.
            相對路徑： 相較於某個路徑下，指名的路徑。
            絕對路徑： 包含盤符在內的文件或目錄的路徑。

        路徑分割符
            windows:\\
            unix:/

        3.File類中涉及到關於文件或文件目錄的創建、刪除、重命名、修改時間、文件大小等方法，
            並未涉及到寫入或讀取文件內容的操作，如果需要讀取或寫入文件內容，必須使用IO流來完成。

        4.後續File類的對象常會作為參數傳遞給流的構造器中，指名讀取或寫入的"終點".

     */
    @Test
    public void test1() {
        // 構造器1
        File file1 = new File("hello.txt");// 鄉對路徑，相對於idea的module下面的路徑(java08)
        // \\ 多一個 因為在JAVA要轉譯
        File file2 = new File("C:\\Users\\USER\\Desktop\\IT倉庫\\backend\\JAVA 學習\\JAVA高級\\多線程\\JavaSenior1\\day08\\he.txt");// 鄉對路徑，相對於idea的module下面的路徑(java08)

        System.out.println(file1);
        System.out.println(file2);

        // 構造器2:
        File file3 = new File("C:\\Users\\USER\\Desktop\\IT倉庫\\backend\\JAVA 學習\\JAVA高級\\多線程\\", "JavaSenior1");
        System.out.println(file3);

        // 構造器3:
        File file4 = new File(file3, "hi.txt");
        System.out.println(file4);
    }

    @Test
    public void test2() {
        File file1 = new File("hello.txt");
//        File file2 = new File("D:\\");
        File file2 = new File("D:\\io\\hi.txt");

        System.out.println(file1.getAbsolutePath());
        System.out.println(file1.getPath());
        System.out.println(file1.getName());
        System.out.println(file1.getParent());
        System.out.println(file1.length());
        System.out.println(new Date(file1.lastModified()));
        System.out.println(file1.list());
        System.out.println(file1.listFiles());
        System.out.println();
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
        System.out.println(Arrays.asList(file2.list()));
        System.out.println(Arrays.asList(file2.listFiles()));

    }

    @Test
    public void test3() {
        File file2 = new File("D:\\");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
        System.out.println(Arrays.asList(file2.list()));
        System.out.println(Arrays.asList(file2.listFiles()));
    }

    /*
        要返回true，需要file1在硬碟存在且file2不存在
     */
    @Test
    public void test4() {
        File file1 = new File("hello.txt");
        File file2 = new File("D:\\hi.txt");

        boolean b = file2.renameTo(file1);
        System.out.println(b);

    }

    @Test
    public void test5() {
        File file1 = new File("hello.txt");
        file1 = new File("hello1.txt");
        System.out.println(file1.isDirectory());
        System.out.println(file1.isFile());
        System.out.println(file1.exists());
        System.out.println(file1.canRead());
        System.out.println(file1.canWrite());
        System.out.println(file1.isHidden());
        System.out.println();
        File file2 = new File("D:\\NBA");
        file2 = new File("D::\\BVS");
        System.out.println(file2.isDirectory());
        System.out.println(file2.isFile());
        System.out.println(file2.exists());
        System.out.println(file2.canRead());
        System.out.println(file2.canWrite());
        System.out.println(file2.isHidden());
    }


    @Test
    public void test6() throws IOException {
        // 文件的創建
        File file1 = new File("hy.txt");
        if (!file1.exists()) {
            file1.createNewFile();
            System.out.println("創建成功");
        } else {
            file1.delete();
            System.out.println("刪除成功");
        }
    }

    @Test
    public void test7() {
        // 文件目錄的創建
        File file1 = new File("id\\id1");
        boolean mkdir = file1.mkdir();
        if (mkdir) {
            System.out.println("創建成功1");
        } else {
            System.out.println("創建失敗1");
        }

        System.out.println();
        File file2 = new File("id\\id1");
        boolean mkdir2 = file2.mkdirs();
        if (mkdir2) {
            System.out.println("創建成功2");
        }else {
            System.out.println("創建失敗2");
        }
    }
}
