package smallyang.java;

/**
 * 測試Thread中常用方法
 * 1.   start():啟動當前線呈;調用當前線呈run()
 * 2.   run():通常需要重寫Thread類中的此方法，將創建的線呈要執行的操作聲明在這邊
 * 3.   currentThread():靜態方法，返回執行當前代碼的線呈
 * 4.   getName():獲取當前線呈的名字
 * 5.   setName():設置當前線呈的名字
 * 6.   yield():釋放當前CPU的執行權，但下一刻也有可能又回來此線呈，所以不一定。
 * 7.   join():在線呈A中調用線呈B的join()，此時線呈A就會進入阻塞狀態，直到線呈B完全執行完以後，
 *      線呈A才會結束阻塞狀態繼續執行。
 * 8.   stop():已過時，當執行此方法時，強制結束當前線呈。
 * 9.   sleep(Long millitime):讓當前線呈"睡眠"指定的millitime毫秒，在指定的毫秒時間內，當前線呈是阻塞狀態。
 * 10.  isAlive():判斷當前線呈是否還存活。
 *
 *
 * 線呈的優先級:
 * 1.
 *  MAX_PRIORITY:10
 *  MIN_PRIORITY:1
 *  NORM_PRIORITY:5 --預設優先級
 *
 * 2. 如何獲取和設置當前線呈的優先級: 這邊指的是cpu的高機率，非絕對
 *      getPriority():獲取線呈的優先級
 *      setPriority(int p):設置線呈的優先級
 *
 *  說明: 高優先級的線呈要搶占低優先級線呈CPU的執行權。但是只是從機率上講，高優先級的線呈高機率的情況下
 *      被執行，並不意味著只有當高優先級的線呈執行完以後，低優先級的線呈才會執行。
 *
 * @author USER
 * @date 2023-08-06 上午 09:08
 */

class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
//                try {
//                    sleep(80);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }
//            if(i % 20 == 0){
////                Thread.currentThread().yield();
//                //  釋放CPU，實際上是這些CPU在主控這些線呈
//                this.yield();
//            }
        }
    }

    public HelloThread(String name) {
        super(name);
    }
}


public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread("建構子產生的線呈名");
//        h1.setName("分線呈開跑");
        //  設置分線呈的優先級
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();


        Thread mainThread = Thread.currentThread();
        mainThread.setName("主線呈");
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().getPriority() + ":" + i);
            }

//            if (i == 20) {
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
        }
//        System.out.println(h1.isAlive());
//        System.out.println(Thread.currentThread().getPriority());
    }
}
