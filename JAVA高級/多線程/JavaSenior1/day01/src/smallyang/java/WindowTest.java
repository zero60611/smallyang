package smallyang.java;

/**
 * 例子:創建三個賣票的窗口，總票數100張，使用繼承Thread的方式
 *
 * @author USER
 * @date 2023-08-10 上午 06:08
 */


class Window extends Thread {
    //  要加static 因為每個對象共享同一個靜態變亮
    private static int ticket = 100;

    /**
     * 但這樣寫法有線程安全問題，之後會處理，應該要用同步性
     */
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


public class WindowTest {

    public static void main(String[] args) {
        Window t1 = new Window();
        Window t2 = new Window();
        Window t3 = new Window();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

    }
}
