package com.smallyang.java;

/**
 * 使用同步代碼塊解決既成Thread類的方式的線程安全問題
 * 例子:創建三個窗口賣票，總票數為100張，使用繼承Thread類的方式
 * 說明:在繼承Thread類創建多線程的方式中，使用THIS充當同步監視器
 * 考慮使用當前類來充當同步監視器 XXX.class
 *
 *
 *
 * @author USER
 * @date 2023-08-26 上午 06:57
 */

class Window2 extends Thread {
    //  要加static 因為每個對象共享同一個靜態變量
    private static int ticket = 100;

    //  這個一樣要static
    private static Object object = new Object();

    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
    @Override
    public void run() {
        while (true) {
            //  正確
//            synchronized (object) {
            synchronized (Window2.class) { // 類 也是對象 反射在細說 有個類 例如，Class  clazz = Window2.class， Window2.class只會加載一次
                //  錯誤 因為有多個window對象 t1 t2 t3  3個物件
//            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ":賣票，票號為:" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }

        }
    }
}


public class WindowTest2 {
    public static void main(String[] args) {
        //  有3個window對象，所以同步性不可以用this
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
