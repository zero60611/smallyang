package com.smallyang.java2;

/**
 * 線程的通信: 2個線程交替打印，1~100
 *
 * 涉及到的三個方法
 * 1. wait():一旦執行此方法，當前線程就進入阻塞狀態，並釋放同步監視器。
 * 2. notify():一旦執行此方法，就會喚醒被wait的一個線程，如果有多個線程被wait，就喚醒優先級高的那個。
 * 3. notifyAll():一旦執行此方法，就會喚醒所有被wait的線程
 *
 * 說明:
 * 1.wait、notify、notifyAll三個方法必須使用在同步代碼快or同步方法中
 * 2.wait、notify、notifyAll三個方法必須使用在同步代碼快or同步方法中的同步監視器
 *  否則會出現IllegalMonitorStateException
 *
 *  3.wait、notify、notifyAll 是定義在java.laon.Object中
 *
 *  面試題: sleep()、wait()的異同?(常考的final finally or override overrode or string stringbuffer or collection collections)
 *  1. 相同點:一旦執行此方法，都可以使得當前的線程進入阻塞狀態。
 *  2. 不同點:1) 兩個方法聲明位置不同:Thread類中聲明sleep()、Object類中聲明wait()
 *           2) 調用要求不同:sleep可以在任何需要的場景下調用。wait必須使用在同步代碼塊或同步方法中
 *           3) 關於是否釋放同步監視器:如果兩個方法都使用在同步代碼塊或同步方法中，sleep不會釋放鎖，wait會釋放鎖
 *
 * @author USER
 * @date 2023-09-16 上午 05:57
 */
class Number implements Runnable {
    private int number = 1;
    //  Exception in thread "線程1" Exception in thread "線程2" Exception in thread "線程3" java.lang.IllegalMonitorStateException: current thread is not owner
    private Object object = new Object();// IllegalMonitorStateException

    @Override
    public void run() {

        while (true) {
//            synchronized (this){
            synchronized (object){
                // 多個線程阻塞就要用notifyAll()
//                  notifyAll();

                //  這時候線程1的wait被喚醒，因為線程二已經持有this同步監視器的鎖
                //  因此線程1解鎖出去後也暫時進不來
                //  凡是省略的，就看是不是靜態，不是靜態就是省略this(類的實例)了
//                notify();
                object.notify();

                if(number <= 100){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        // 使得調用wait()方法讓線程進入阻塞狀態，並且釋放了this同步監視器的鎖，這樣線程2才進得來
//                        wait();
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }

        }

    }
}


public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);
        Thread t3 = new Thread(number);

        t1.setName("線程1");
        t2.setName("線程2");
        t3.setName("線程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
