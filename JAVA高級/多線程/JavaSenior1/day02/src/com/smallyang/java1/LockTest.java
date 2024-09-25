package com.smallyang.java1;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 解決線程安全問題的方式三:Lock鎖 ----JDK5.0新增
 *
 * 1.   面試題:synchronized 與 lock的異同?
 *          相同:兩者都可以解決線程安全問題
 *          不同:synchronized機制在執行完相應的同步代碼以後，自動地釋放同步監視器
 *              Lock需要手動的啟動同步監視器(lock())，同時結束同步也需要手動的實現unLock()
 * 
 * 2. 優先順序:
 *      locl -> 同步代碼塊  -> 同步方法
 *
 * 3. 如何解決線程安全問題，有幾種方式?
 *
 * @author USER
 * @date 2023-09-03 下午 06:05
 */

class Window implements Runnable {
    private int ticket = 100;
    //  1.  實例化ReentrantLock
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true) {
            try {
                //2.    調用鎖定的方法:lock
                lock.lock();

                if (ticket > 0) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": 售票，票號為:" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                //3.    調用解鎖的方法
                lock.unlock();
            }


        }
    }
}

public class LockTest {
    public static void main(String[] args) {
        Window w = new Window();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();


    }
}
