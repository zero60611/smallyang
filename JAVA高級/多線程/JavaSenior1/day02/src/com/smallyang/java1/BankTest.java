package com.smallyang.java1;

/**
 * 使用同步機制將單例模式中的懶漢模式改寫為線程安全的
 *
 * @author USER
 * @date 2023-09-03 上午 08:31
 */
public class BankTest {
}


/**
 * 懶漢式(單例模式)
 */
class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    //    private static synchronized Bank getInstance(){
    private static Bank getInstance() {
        //  方式一: 效率稍差
//        synchronized (Bank.class) {
//            if (instance == null) {
//                instance = new Bank();
//            }
//            return instance;
//        }
        // 方式二: 效率更高
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
