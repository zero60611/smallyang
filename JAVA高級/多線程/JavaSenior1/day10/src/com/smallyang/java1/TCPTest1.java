package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

/**
 * 實現TCP的網路編程
 * 例子1：
 *
 * @author USER
 * @date 2024-08-11 下午 07:03
 */
public class TCPTest1 {

    // 客戶端
    @Test
    public void client() {

        Socket socket = null;
        OutputStream os = null;
        try {
            // 1. 創建Socket對象，指名服務器端的ip和端口號
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            // 2. 獲取一個輸出流，用於輸出數據
            os = socket.getOutputStream();
            // 3. 寫出數據操作
            os.write("您好，我是廖小羊".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 4. 資源關閉
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    // 服務端
    @Test
    public void server() {
        ServerSocket ss = null;
        Socket acceptSocket = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1. 創建服務器端的ServerSocket，指名自己的端口號
            ss = new ServerSocket(8899);
            // 2. 調用accept()表示接受來自客戶端的socket
            acceptSocket = ss.accept();
            // 3. 獲取輸入流
            inputStream = acceptSocket.getInputStream();


            // 不建議這樣寫 可能會有亂碼
//        byte[] buffer = new byte[1024];
//        int len;
//        while ((len = inputStream.read(buffer))!= -1) {
//            String str = new String(buffer, 0, len);
//            System.out.println(str);
//        }

            // 4. 讀取輸入流中的數據
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            System.out.println(baos.toString());
            System.out.println(acceptSocket.getInetAddress().getHostAddress());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // 5. 關閉資源
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (acceptSocket != null) {
                    acceptSocket.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }






    }


}
