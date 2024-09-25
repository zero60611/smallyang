package com.smallyang.java1;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author USER
 * @date 2024-06-08 下午 04:18
 */
public class DAOTest {

    @Test
    public void test1() {
        CustomerDAO dao1 = new CustomerDAO();
        dao1.add(new Customer());
        List<Customer> forList = dao1.getForList(10);


        StudentDAO dao2 = new StudentDAO();
        Student index = dao2.getIndex(1);

    }
}
