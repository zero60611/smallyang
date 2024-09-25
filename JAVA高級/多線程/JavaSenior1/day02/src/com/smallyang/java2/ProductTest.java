package com.smallyang.java2;

/**
 * 線程通信的應用: 生產者與消費者
 * <p>
 * 問題:
 * 1.   是否是多線程問題?是  生產者線程  消費者線程
 * 2.   是否有共享數據的問題?
 * 3.   如何解決線程的安全問題?同步機制，有三種方法。
 * 4.   是否涉及線程的通信?是
 *
 * @author USER
 * @date 2023-09-17 上午 09:06
 */
    // 因為clerk同步監視器只有一個，因此進入生產跟消費的方法的時候監視器只有一個被拿，不會同時去用兩個監視器操作
    //  只有唯一一個監視器的對象
class Clerk {
    private int productCount = 0;

    //  生產產品
    public synchronized void produceProduct() {
        if (productCount < 20) {
            productCount++;
            System.out.println(
                    Thread.currentThread().getName() + ":開始生產商品第" + productCount + "個"
            );

            notify();
        } else {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //  消費產品
    public synchronized void consumeProduct() {
        if (productCount > 0) {
            System.out.println(Thread.currentThread().getName() + ":開始消費第" + productCount + "個");
            productCount--;

            notify();
        }else {
            // 等待
            try {
                wait();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
        }
    }
}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":開始消費產品");

        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.produceProduct();
        }
    }
}

class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println(getName() + ":開始生產產品");

        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.consumeProduct();
        }
    }
}

public class ProductTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        p1.setName("生產者1");

        Consumer c1 = new Consumer(clerk);
        c1.setName("消費者1");
        Consumer c2 = new Consumer(clerk);
        c2.setName("消費者2");

        p1.start();
        c1.start();
        c2.start();

    }

}
