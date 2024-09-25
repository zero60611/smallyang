package com.smallyang.java;

/**
 * * 例子:創建三個賣票的窗口，總票數100張，使用實作Runnable的方式
 * 跟繼承thread方法不同 ticket這邊不用再加static啦，所有線程都共用同一個
 * <p>
 * <p>
 * 1.  問題：賣票過程中，出現了重票、錯票 --> 出現了線程的安全問題。
 * 2.  問題出現的原因：當某個線程操作車票的過程中，尚未操作完成時，其他線程參與進來也操作車票。
 * 3.  如何解決：當一個線程A在操作ticket的時候，其他線程不能參與進來，直到線程A操作完ticket，其他線程才可以開始操作ticket。
 * 4.  在java中，我們通過同步機制，來解決線程的安全問題。
 * <p>
 * 方式一：    同步代碼塊
 * synchronized(同步監視器){
 * //需要被同步的代碼
 * }
 * 說明： 1.操作共享數據的代碼，即為需要被同步的代碼塊
 *          ->不能包含代碼多了，也不能包少了
 *      2.共享數據：   多個線程共同操作的變量，如ｔｉｃｋｅｔ就是共享數據。
 *      3.同步監視器: 俗稱 鎖。任何一個類的對象都可以充當鎖
 *      要求: 多個線程必須要共用同一把鎖
 *
 *  補充: 在實現Runnable接口創建多線程的方式中，我們可以考慮使用this充當同步監視器
 * <p>
 *
 * 方法二：    同步方法
 *  如果操作共享數據的代碼完整的聲明在一個方法中，我們不訪將此方法聲明同步的。
 *
 *
 * 5.同步的方式，解決了線程的安全問題。-----好處
 *   操作同步代碼時，只能有一個線程參與，其他線程等待，相當於是一個單線程的概念。--->效率低--侷限性
 * 
 * 
 * @author USER
 * @date 2023-08-11 上午 07:45
 */


class Window1 implements Runnable {
    private int ticket = 100;
//    Object object = new Object();
    Dog dog = new Dog();
    @Override
    public void run() {
        //  移到裡面就不是共用同一個物件了，不安全。
//        Object object = new Object();

        while (true) {
//            synchronized (object) {
//            synchronized (dog) {
            synchronized (this) {   // 此時this，就是Window1  w1的物件
                //  while (true)不能放在裡面，要不然其他線程過來可能就沒票了，邏輯要正確
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


public class WindowTest1 {
    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
    public static void main(String[] args) {
        //  只有一個window對象，所以同步性可以用this
        Window1 w1 = new Window1();

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

class Dog{

}
