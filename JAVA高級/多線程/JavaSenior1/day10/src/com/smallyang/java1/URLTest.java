package com.smallyang.java1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author USER
 * @date 2024-08-18 上午 10:58
 */
public class URLTest {
    public static void main(String[] args) {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
//            URL url = new URL("https://google.com");
            URL url = new URL("http://localhost:8080/emamples/beauty.jpg?username=smallyang");
            String protocol = url.getProtocol();// 協議名
            System.out.println("協議名:" + protocol);
            String host = url.getHost();// 主機名
            System.out.println("主機名:" + host);
            int port = url.getPort();// 端口號
            System.out.println("端口號:" + port);
            String path = url.getPath();// 文件路徑
            System.out.println("path:" + path);
            String file = url.getFile();// 文件名
            System.out.println("文件名:" + file);
            String query = url.getQuery();// 查詢名
            System.out.println("查詢名:" + query);

            // 額外訪問

            // 強轉
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("BBBAA.jpg");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 關閉資源
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            if(urlConnection != null) {
                urlConnection.disconnect();
            }
        }




    }
}
