package com.smallyang.java1;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 一、網路編程兩個問題
 * 1. 如何準確定位網路上一台或多台主機：定位主機上特定應用
 * 2. 找到主機後如何靠高效進行數據傳輸
 * <p>
 * 二、網路編程兩要素
 * 1. 對應問題一：IP 和 port號
 * 2. 對應問題二：網路通信協議
 * TCP/IP參考模型(應用層、傳輸層、網路層、物理+數據連接層)
 * <p>
 * 三、通信要素一：IP和端口號
 * 1. IP：唯一的標識Internet上的計算機(通信實體)
 * 2. Java中使用InetAddress類代表IP
 * 3. IP分類：IPv4 和 IPv6;  萬維網(實體路徑)和區域網的區別(192.xx)
 * 4. 域名:  www.badiu.com
 * 5. 本地迴路地址： 127.0.0.1 對映著:localhost
 * 6. 如何實例化InetAddress兩個方法：getByName()、getLocalHost()
 *   兩個常用方法getHostName()/getHostAddress()
 *
 * 7. 端口號：正在計算機上運行的進程。
 *  要求：不同的進程有不同的端考號。
 *  範圍：被規定為一個16位的整數 0~65535
 *  
 * 8. 端口號與IP地址的組合得出一個網路套接字：Socket
 *   網路通信又稱Socket通信，也稱Socket編程
 *
 * @author USER
 * @date 2024-08-09 上午 06:30
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
//        InetAddress byName = InetAddress.getByName("192.168.100.1");
//        InetAddress byName = InetAddress.getByName("www.google.com");
        InetAddress byName = InetAddress.getByName("127.0.0.1");
        System.out.println(byName);
//        System.out.println(byName.getAddress());
//        System.out.println(byName.getCanonicalHostName());
        System.out.println(byName.getHostName());
        System.out.println(byName.getHostAddress());

        // 獲取本地IP
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);

    }


}
