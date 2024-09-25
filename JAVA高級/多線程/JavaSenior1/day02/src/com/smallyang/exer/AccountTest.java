package com.smallyang.exer;

/**
 * 銀行有一個帳戶
 * 兩個存戶向同一個帳戶存錢3000元，每次存1000，存3次，每次存完印餘額
 * <p>
 * 分析：
 * 1.  是否是多線程問題? 是 有兩個儲戶
 * 2.  是否共享數據? 有 帳戶
 * 3.  是否有線程安全? 有
 * 4.  需要考慮如何解決線程安全問題?同步機制:有三種
 *
 * @author USER
 * @date 2023-09-09 上午 08:14
 */

class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    //  因為共用的同步監視器 this只有一個Account，所以可以直接在方法上加synchronized
    public synchronized void deposit(double amt) {
        if (amt > 0) {
            balance += amt;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + "存錢成功，餘額為:" + balance);
        }
    }
}

class Customer extends Thread {
    private Account acct;

    public Customer(Account account) {
        this.acct = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acct.deposit(1000);
        }
    }
}

public class AccountTest {
    public static void main(String[] args) {
        Account acct = new Account(0);
        Customer c1 = new Customer(acct);
        Customer c2 = new Customer(acct);

        c1.setName("甲");
        c2.setName("乙");
        c1.start();
        c2.start();
    }

}
