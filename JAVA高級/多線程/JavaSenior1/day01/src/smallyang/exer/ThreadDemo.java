package smallyang.exer;

/**
 *
 *  練習: 創建兩個分線呈，其中一個現成遍歷100以內偶數，另一個遍歷100以內奇數
 *
 * @author USER
 * @date 2023-08-05 下午 07:19
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThread1 m1 = new MyThread1();
//        MyThread2 m2 = new MyThread2();
//        m1.start();
//        m2.start();

        //  創建Thread匿名子類的方式
        new Thread(){
            //  new 不是一個Thread，而是匿名子類的對象
            @Override
            public void run() {
                for(int i = 0 ;i < 100; i++){
                    if(i % 2 == 0){
                        System.out.println(Thread.currentThread().getName() + ":" +"100內偶數有:"+i);
                    }
                }
            }
        }.start();

        new Thread(){
            //  new 不是一個Thread，而是匿名子類的對象
            @Override
            public void run() {
                for(int i = 0 ;i < 100; i++){
                    if(i % 2 != 0){
                        System.out.println(Thread.currentThread().getName() + ":" +"100內奇數有:"+i);
                    }
                }
            }
        }.start();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for(int i = 0 ;i < 100; i++){
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":" +"100內偶數有:"+i);
            }
        }
    }
}
class MyThread2 extends Thread{
    @Override
    public void run() {
        for(int i = 0 ;i < 100; i++){
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":" +"100內奇數有:"+i);
            }
        }
    }
}