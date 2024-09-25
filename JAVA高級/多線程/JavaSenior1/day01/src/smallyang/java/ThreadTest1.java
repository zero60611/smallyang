package smallyang.java;

/**
 * 創建多線呈的方式二: 實現Runnable接口
 * 1.   創建一個實現了Runnable接口的類
 * 2.   實現類去實現Runnable中的抽象方法: run()
 * 3.   創建實現類的對象
 * 4.   將此對象作為參數傳遞到Thread類的構造器中
 * 5.   將通過Thread類的對象調用start()
 *
 *  比較創建線程的兩種方式。
 *      開發中:優先選擇:實現Runnalbe接口的方式
 *     1.   實現的方式沒有類的單繼承性的侷限性。
 *     2.   實現的方式更適合來處理多個線程有共享數據的情況。
 *     
 *     聯繫: public class Thread implements Runnable
 *     相同點: 兩種方式都需要重寫run()，將線程要執行的邏輯聲明在run()中。
 *
 *
 * @author USER
 * @date 2023-08-11 上午 07:04
 */

//  1.創建一個實現Runnable接口的類
class MyThread1 implements Runnable {

    //2.  實現類去實現Runnable中的抽象方法: run()
    @Override
    public void run() {
        for(int i =0; i < 100; i++){
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+i);
            }
        }
    }
}


public class ThreadTest1 {
    public static void main(String[] args) {
        //  3.   創建實現類的對象
        MyThread1 t1 = new MyThread1();
        //  4.   將此對象作為參數傳遞到Thread類的構造器中
        Thread thread1 = new Thread(t1);
        //  5.   將通過Thread類的對象調用start():
        //      1.啟動線程
        //      2.調用當前線程的run()->調用了Runnable類型的target的run()
        thread1.setName("tt1");

        thread1.start();

        // 再啟動一個線程
        Thread thread2 = new Thread(t1);
        thread2.setName("tt2");
        thread2.start();
    }
}
