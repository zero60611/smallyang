package com.smallyang.java;

/**
 * 使用同步方法解決實現Runnable接口的線呈安全問題
 *
 * 關於同步方法的總結：
 * 1.   同步方法仍然涉及到同步監視器，只是不需要我們顯示的聲明。
 * 2.   非靜態的同步方法，同步監視器是this
 *      靜態的同步方法，同步監視器是：當前類的本身
 *
 *
 * @author USER
 * @date 2023-08-29 上午 05:58
 */


class Window4 extends Thread {
    //  要加static 因為每個對象共享同一個靜態變量
    private static int ticket = 100;

    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
    @Override
    public void run() {
        while (true) {
            show();
        }
    }

    //  同步監視器是window4.class，當前的這個類
    private static synchronized void show() {
    //  同步監視器是t1 t2 t3，此種監視方法是錯誤的，要加static
        //    private synchronized void show() {
        if (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ":賣票，票號為:" + ticket);
            ticket--;
        }
    }
}

public class WindowTest4 {
    public static void main(String[] args) {
        //  有3個window對象，所以同步性不可以用this
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
