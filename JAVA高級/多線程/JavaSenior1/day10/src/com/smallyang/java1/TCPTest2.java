package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 實現TCP的網路編程
 * 例題2:　客戶端發送文件給服務端，服務端將文件保存在本地
 *
 *
 * @author USER
 * @date 2024-08-16 上午 06:02
 */
public class TCPTest2 {
    /*
    涉及到的異常應該使用try-catch
     */
    @Test
    public void client() throws IOException {
        //1.造socket
        Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
        //2.獲取輸出流
        OutputStream outputStream = socket.getOutputStream();
        //3.獲取輸入流 從文件讀數據
        FileInputStream fis = new FileInputStream(new File("AAA.jpg"));
        //4.讀寫過程
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fis.read(buffer))!= -1) {
            outputStream.write(buffer, 0, len);
        }
        //5.資源關閉
        fis.close();;
        outputStream.close();
        socket.close();

    }

    @Test
    public void server() throws IOException {
        //1. 造serverSocket
        ServerSocket serverSocket = new ServerSocket(9090);
        //2. 獲取客戶端socket
        Socket socket = serverSocket.accept();
        //3. 獲取客戶端輸入流
        InputStream inputStream = socket.getInputStream();
        //4.保存數據到本地
        FileOutputStream fos = new FileOutputStream(new File("XFEF.jpg"));

        //5. 讀寫過程
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
       // 6.資源關閉
        fos.close();
        inputStream.close();
        socket.close();
        serverSocket.close();

    }
}
