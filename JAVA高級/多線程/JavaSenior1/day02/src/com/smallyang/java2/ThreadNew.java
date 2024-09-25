package com.smallyang.java2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 創建線程的方式三:實現callable接口  -jdk5.0新增
 *
 * 如何理解實現callable接口的方式創建多線程比實現Runnable接口創建多線程的方式強大?
 * 1.call()可以有返回值。
 * 2.call()可以拋出異常，被外面的操作捕獲，獲取異常的信息。
 * 3.callable是支持泛型的，看下面Callable<Integer>，call方法返回值就是限定的值Integer。
 *
 *
 * @author USER
 * @date 2023-09-22 下午 10:09
 */
//1.創建一個實現callable的實現類
//class NumThread implements Callable {
class NumThread implements Callable<Integer> {// 添加泛型

    //2.實現call方法，將此線程需要執行的操作聲明在call()中
    @Override
//    public Object call() throws Exception {
    // 添加泛型可以指定返回的類別，就不用限定只能最終父類的Object了
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }

        return sum;
    }
}


public class ThreadNew {
    public static void main(String[] args) {
        //3.創建callable接口實現類的對象
        NumThread numThread = new NumThread();

        //4.將此callable接口實現類的對象做為參數，傳遞到FutureTask構造器中，創建FutureTask對象
//        FutureTask futureTask = new FutureTask(numThread);
        FutureTask<Integer> futureTask = new FutureTask<>(numThread);

        //5.將FutureTask類的對象作為參數，傳遞到Thread類的構造器中，創建Thread對象，並調用start()
        new Thread(futureTask).start();
        try {
            // get()返回值即為FutureTask構造器參數callAble實現類重寫的call()的返回值
            //  對call不感興趣，就不用去調用get，調用get只是為了獲得call的返回值
            //6.獲取callable中的call方法的返回值
//            Object sum = futureTask.get();
            //  FutureTask<Integer>指定泛型為Integer後就可以指定了
            Integer sum = futureTask.get();
            System.out.println("futureTask sum:" + sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
