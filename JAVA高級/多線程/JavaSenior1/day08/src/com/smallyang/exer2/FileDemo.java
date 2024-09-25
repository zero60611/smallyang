package com.smallyang.exer2;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author USER
 * @date 2024-06-26 上午 06:19
 */
public class FileDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("D\\io\\hello.txt");
        //創建一個與file同目錄下的另外一個文件，文件名為haha.txt
        File destFile = new File(file.getParent(), "haha.txt");
        boolean newFile = destFile.createNewFile();
        if (newFile) {
            System.out.println("創建成功");
        }

    }
}
