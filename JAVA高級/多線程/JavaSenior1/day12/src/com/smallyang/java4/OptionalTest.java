package com.smallyang.java4;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Optional類：為了在程序中避免出現空指針異常而創建的。
 *
 * 常見方法：ofNullable(T t)
 *          orElse(T t)
 * 
 * 
 * @author USER
 * @date 2024-10-07 上午 05:45
 */
public class OptionalTest {

    /*
    Optional.of(T t) : 創建一個Optional實例，t 必須非常空
    Optional.empty() : 創建一個空的Optional實例
    Optional.ofNullable(T t) : t 可以為null
     */
    @Test
    public void test1() {
        Girl girl = new Girl();
//        girl = null;
        //of(T t) :保證t是非空的
        Optional<Girl> girl1 = Optional.of(girl);
        System.out.println(girl1);
    }

    @Test
    public void test2() {
        Girl girl = new Girl();
        girl = null;
        // ofNullable(T t): t可以為null
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);

        // orElse(T t1): 如果當前的Optional內部封裝的t是非空的，則返回內部的t
        // 如果內部的t是空的，則返回orElse() 方法中的參數t1
        Girl girl2 = girl1.orElse(new Girl("小美"));
        System.out.println(girl2);
        System.out.println(Thread.currentThread());
        ThreadLocal<String> str = new ThreadLocal<>();
        str.set("HELLO");
        System.out.println(str.get());
        str.remove();

        System.out.println(str.get());
    }

    public String getGirlName(Boy boy) {
        //java.lang.NullPointerException
        return boy.getGirl().getName();
    }

    // 優化以後getGirlName
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if(girl != null) {
                return girl.getName();
            }
        }
        return null;
    }   
    
    // 使用Optional優化以後getGirlName
    public String getGirlName2(Boy boy) {
        Optional<Boy> boy1 = Optional.ofNullable(boy);
        // boy1一定非空
        Boy boy2 = boy1.orElse(new Boy(new Girl("小玉")));
        Girl girl = boy2.getGirl();
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        //  girl2一定非空
        Girl girl2 = girl1.orElse(new Girl("小華"));

        return girl2.getName();
    }

    @Test
    public void test3() {
        Boy boy = null;
        boy = new Boy();
        boy = new Boy(new Girl("安齋拉拉"));
//        String girlName = getGirlName(boy);
//        String girlName = getGirlName1(boy);
        String girlName = getGirlName2(boy);
        System.out.println(girlName);
    }
}
