package com.smallyang.exer1;

import java.util.List;

/**
 * @author USER
 * @date 2024-06-19 上午 05:45
 */
public class DATTest {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();
        dao.save("1001", new User(1001, 34, "料卡鋪"));
        dao.save("1002", new User(1002, 20, "曉萱"));
        dao.save("1003", new User(1003, 25, "小玉"));

        dao.update("1003", new User(1003, 30, "曉華"));

        dao.delete("1002");

        List<User> list = dao.list();
//        System.out.println(list);
        list.forEach(System.out::println);

    }
}
