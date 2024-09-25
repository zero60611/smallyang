package com.smallyang.java1;

import java.util.List;

/**
 * @author USER
 * @date 2024-06-08 下午 04:08
 * <p>
 * DAO:data(base) access object
 */
public class DAO<T> {// 表的共性操作DAO
    // 增
    public void add(T t) {

    }

    // 刪
    public boolean remove(int index) {
        return false;
    }

    // 修改
    public void update(int index, T t) {

    }

    // 查詢一條
    public T getIndex(int index) {
        return null;
    }
    // 查詢多條
    public List<T> getForList(int index) {
        return null;
    }

    // 泛型方法
    // 舉例:獲取表中一共有多少條紀錄?獲取最大的員工入質時間
    public <E> E getValue() {
        return null;
    }

}
