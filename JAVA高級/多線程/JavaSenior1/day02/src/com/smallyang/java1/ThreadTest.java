package com.smallyang.java1;

/**
 * 演示線程的死鎖問題
 * 1.   死鎖的理解:不同的線程分別占用對方需要的同步資源不放棄，
 * 都在等待對方放棄自己需要的同步資源，就形成了線程的死鎖。
 * 
 * 2.   說明:
 * -1. 出現死鎖後，不會出現異常，不會出現提示，指示所有的線程都處於阻塞狀態，無法繼續。
 * -2.  我們使用同步十，要避免出現死鎖。
 *
 * @author USER
 * @date 2023-09-03 下午 03:59
 */
public class ThreadTest {
    public static void main(String[] args) {
        StringBuffer s1 = new StringBuffer();
        StringBuffer s2 = new StringBuffer();
        new Thread() {
            @Override
            public void run() {
                synchronized (s1) {
                    s1.append("a");
                    s2.append(1);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    synchronized (s2) {
                        s1.append("b");
                        s2.append(2);
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s2) {
                    s1.append("c");
                    s2.append(3);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (s1) {
                        s1.append("d");
                        s2.append(4);
                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }
            }
        }).start();
    }
}
