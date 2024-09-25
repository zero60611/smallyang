package com.smallyang.exer1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author USER
 * @date 2024-06-14 下午 09:05
 */
public class DAO<T> {
    private Map<String, T> map = new HashMap<>();

    // 保存T類型的對象到Map成員變量
    public void save(String id, T entity) {
        map.put(id, entity);
    }

    // 從Map中獲取id對應的對象
    public T get(String id) {
        return map.get(id);
    }

    // 替換Map中key為id的內容，改為entity對象
    public void update(String id, T entity) {
        if (map.containsKey(id)) {
            map.put(id, entity);
        }
    }

    // 返回map中存放的所有T對象
    public List<T> list() {
        List<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for (T t : values) {
            list.add(t);
        }

        List<T> collect = values.stream().collect(Collectors.toList());
//        Arrays.asList(map.values());
        return collect;
    }

    // 刪除指定id對象
    public void delete(String id) {
        map.remove(id);
    }


}
