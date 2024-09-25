package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 其他類的使用
 * 1. System
 * 2. Math
 * 3. BigInteger 和 BigDecimal
 *
 *
 * @author USER
 * @date 2024-03-24 下午 06:49
 */
public class OtherClassTest {

    @Test
    public void test1() {

        String javaHome = System.getProperty("java.home");
        System.out.println("javaHome :" + javaHome);
        String javaVersion = System.getProperty("java.version");
        System.out.println("javaVersion :" + javaVersion);

        String osHome = System.getProperty("os.home");
        System.out.println("osHome :" + osHome);
        String osVersion = System.getProperty("os.version");
        System.out.println("osVersion :" + osVersion);

        String userName = System.getProperty("user.name");
        System.out.println("userName :" + userName);

        String userDir = System.getProperty("user.dir");
        System.out.println("userDir :" + userDir);


    }

    @Test
    public void test2() {
        BigInteger bi = new BigInteger("12433241143324112343324112343324112343324112343324112343324112343324112343324112343324112343324112343324112343324112343324112323");
        BigDecimal db = new BigDecimal("12435.351");
        BigDecimal db2 = new BigDecimal("11");
        System.out.println(bi);
//        BigDecimal divide = db.divide(db2);// 因為除不盡，會報錯
//        System.out.println(divide);
        System.out.println(db.divide(db2, BigDecimal.ROUND_HALF_UP));
        System.out.println(db.divide(db2, 15, BigDecimal.ROUND_HALF_UP));


    }


}
