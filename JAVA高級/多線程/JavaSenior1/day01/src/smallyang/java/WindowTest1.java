package smallyang.java;

/**
 *
 * * 例子:創建三個賣票的窗口，總票數100張，使用實作Runnable的方式
 * 跟繼承thread方法不同 ticket這邊不用再加static啦，所有線程都共用同一個
 *
 * @author USER
 * @date 2023-08-11 上午 07:45
 */


class Window1 implements Runnable {
    private int ticket = 100;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + ":賣票，票號為:" + ticket);
                ticket--;
            } else {
                break;
            }
        }
    }
}



public class WindowTest1 {
    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
    public static void main(String[] args) {
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
