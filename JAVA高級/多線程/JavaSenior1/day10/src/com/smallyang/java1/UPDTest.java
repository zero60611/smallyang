package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

/**
 * @author USER
 * @date 2024-08-17 上午 09:51
 */
public class UPDTest {


    // 發送端
    @Test
    public void sender() throws IOException {
        DatagramSocket socket = new DatagramSocket();

        String str = "我是廖小羊UDP發射導彈";
        byte[] bytes = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inet, 9090);
        socket.send(packet);

        socket.close();

    }

    // 接收端
    @Test
    public void receiver() throws IOException {
        DatagramSocket socket = new DatagramSocket(9090);
        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer, 0, buffer.length);

        socket.receive(packet);

        System.out.println(new String(packet.getData(), 0, packet.getLength()));

    }
}
