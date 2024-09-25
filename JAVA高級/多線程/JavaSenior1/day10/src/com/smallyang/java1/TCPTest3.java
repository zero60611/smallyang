package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 實現TCP的網路編程
 * 從客戶端發送文件給服務端，服務端保存到本地，並返回發送成功訊息給客戶
 * 並關閉相應連接
 *
 * @author USER
 * @date 2024-08-16 上午 06:18
 */
public class TCPTest3 {
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
        // 關閉數據的輸出 這樣server端的 讀寫過程才會走完
        socket.shutdownOutput();

        //5.接收來自服務器端的數據並顯示在控制台上
        InputStream inputStream = socket.getInputStream();
        // 考慮亂碼
        // 這邊不用寫內容 他直接存在內部數組裡
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bufferr = new byte[20];
        int len1;
        // 讀取server端傳來的東西
        while ((len1 = inputStream.read(bufferr))!= -1) {
            // 寫道數組中
            baos.write(bufferr, 0, len1);
        }
        System.out.println(baos.toString());

        //6.資源關閉
        fis.close();;
        outputStream.close();
        socket.close();
        baos.close();

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
        FileOutputStream fos = new FileOutputStream(new File("TTTTTTT.jpg"));

        //5. 讀寫過程
        // 因為read是阻塞式操作，客戶端關閉輸出指示後 while就不再走，可以繼續走下去反饋
        // nio就不是阻塞式了
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }

        System.out.println("圖片產出完成");

        //6. 服務器端給予客戶端反饋
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hi i am smallyang".getBytes());

        System.out.println("資訊輸出完成");

        // 7.資源關閉
        fos.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
        outputStream.close();
    }
}
