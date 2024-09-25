package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 泛型的使用
 * 1. jdk 5.0新增的特性
 *
 * 2. 在集合中使用泛型:
 *  總結:
 *  2.1 集合接口或集合類在在jdk5.0 時都修改為帶泛型的結構
 *  2.2 在實例化集合類時，可以指名具體的泛型類型
 *  2.3 指名完以後，在集合類或接口中凡是定義類或接口時，內部結構(比如: 方法、構造器、屬性等)使用到類的
 *      泛型的位置，都指定為實例化的泛型類型。
 *      比如: add(E e)  ---> 實例化以後: add(Integer e)
 *
 *  2.4 注意點: 泛型的類型必須是類，不能是基本數據類型。需要用到基本數據類型的位置，拿包裝類替換
 *  2.5 如果實例化時，沒有指明泛型的類型。預設類型為java.lang.Object類型。
 *
 * 3. 如何自定義泛型結構:泛型類、泛型接口;泛型方法，見GenericTest1.java
 *
 *
 *
 *
 *
 *
 * @author USER
 * @date 2024-06-05 上午 06:07
 */
public class GenericTest {
    // 在集合中使用泛型之前的情況
    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        // 需求:存放學生的成績
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

        // 問題一:類型不安全
        list.add("Tom");

        for (Object score : list) {
            // 問題二: 強轉時，可能出現ClassCastException
            int stuScore = (Integer) score; // Integer可以自動拆箱成int
            System.out.println(stuScore);

        }

    }

    // 在集合中使用泛型的情況：以ArrayList
    @Test
    public void test2() {
        // 不能用int，基本數據類型
        ArrayList<Integer> list = new ArrayList<>();
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);

        // 編譯時就會進行類型檢查，保證數據的安全
//        list.add("Tom");// compile就過不去
        // 方式一:
        for (Integer score : list) {
            // 避免了強轉操作
            int stuScore = score;
            System.out.println(stuScore);
        }
        // 方式二:
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    // 在集合中使用泛型的情況：以HashMap為例
    @Test
    public void test3() {
//        Map<String, Integer> map = new HashMap<String, Integer>();
        // jdk7新特性:類型推斷
        Map<String, Integer> map = new HashMap<>();
        map.put("Tom", 87);
        map.put("Terry", 87);
        map.put("Jack", 67);

//        map.put(123,"ABC");
        // 泛型的嵌套
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "------" + value);
        }


    }
}
