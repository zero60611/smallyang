package com.smallyang.java;

/**
 * 使用同步方法解決實現Runnable接口的線程安全
 * @author USER
 * @date 2023-08-26 上午 07:49
 */
class Window3 implements Runnable {
    private int ticket = 100;

    // synchronized加在方法要確定run裡面的方法所涉及的數據是同步，程式碼不能包多或包少
    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    //  因此把要包的程式碼設在這裡
    private synchronized void show() {//  同步監視器 this
//        synchronized (this) {
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName() + ":賣票，票號為:" + ticket);
                ticket--;
            }
//        }
    }
}


public class WindowTest3 {
    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
    public static void main(String[] args) {
        //  只有一個window對象，所以同步性可以用this
        Window3 w1 = new Window3();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}