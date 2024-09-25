package smallyang.java;

/**
 * 多線呈的創建，方式一:繼承Thread類
 * 1.   創建一個繼承Thread類的子類
 * 2.   重寫Thread類的run()-->將此線呈聲明的操作寫在run方法中
 * 3.   創建Thread類的子類的對象
 * 4.   通過此對象調用start()
 * <p>
 *  例子 遍歷100以內的所有的偶數
 *
 *
 */
//  1.   創建一個繼承Thread類的子類
class MyThread extends Thread {
    //  2.   重寫Thread類的run()

    @Override
    public void run() {
       for(int i = 0 ;i < 100 ;i++){
           if (i % 2 == 0){
               System.out.println(Thread.currentThread().getName() + ":" + i);
           }
       }
    }
}


public class ThreadTest {
    public static void main(String[] args) {
        //  3.   創建Thread類的子類的對象
        MyThread myThread1 = new MyThread();

        //  4.   通過此對象調用start():1.啟動當前線呈 2.調用當前現成的run()
        myThread1.start();
        //  問題一: 我們不能通過直接調用run()的方式啟動線呈
//        myThread1.run();// 不能新增一個線呈，一定要start()

        //  問題二: 再啟動一個線呈，遍歷100以內的偶數，不可以讓已經start()的線呈去執行，會報illegalStateException
        //  我們需要重新創建一個線呈的對象
        MyThread myThread2 = new MyThread();
        myThread2.start();


        for (int i = 0 ;i<300; i++){
            System.out.println(Thread.currentThread().getName() + ":" + "HEELO　ＳＭＡＬＬ　ＹＡＮＧ");
        }
    }
}
