package com.smallyang.java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  創建線程的方式四: 使用連線池
 *
 * 好處:
 * 1.提供響應速度(減少創建新線程的時間)
 * 2.降低資源消耗(重複利用線程池中線程，不需要每次都創建)
 * 3.便於線程管理
 *  corePoolSize:核心池的大小
 *  maximumPoolSize: 最大線程數
 *  keepAliveTime:線程沒有任務時最多保持多長時間後會終止。
 *
 *  創建多線程有四種方式
 *
 * @author USER
 * @date 2023-09-24 上午 10:41
 */

class NumberThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class NumberThread1 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}


public class ThreadPool {
    public static void main(String[] args) {
        //1.  提供指定線程數量的線程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        //  設置線程池的屬性
        System.out.println(service.getClass());
        ThreadPoolExecutor executor = (ThreadPoolExecutor) service;
        executor.setCorePoolSize(15);
//        executor.set
        //2.  執行指定的線程的操作，需要提供實現Runnable接口或Callable接口實現類的物件
        service.execute(new NumberThread());//適用於Runnable
        service.execute(new NumberThread1());//適用於Runnable
//        service.submit(Callable callable);// 適用於Callable

        //3.  關閉連接池
        service.shutdown();
    }
}
