package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Collections:操作Collection、Map的工具類
 * <p>
 * <p>
 * 面試題:Collection 和 Collections的區別?
 *
 * @author USER
 * @date 2024-05-24 下午 11:18
 */
public class CollectionsTest {
    /*
        reverse(List): 反轉List中元素的順序
        shuffle(List): 對List集合元素進行隨機排序
        sort(List): 根據元素的自然順序隊指定List集合元素按升序排序
        sort(List, Comparator):根據指定的Comparator產生的順序對List集合元素進行排序
        swap(List, int, int):將指定List集合中的i處元素和j處元素進行交換

        Object max(Collection):根據元素的自然順序，返回給定集合中的最大元素
        Object max(Collection, Comparator):根據Comparator指定的順序，返回給定集合中的最大元素
        Object min(Collection):
        Object min(Collection, Comparator):
        int frequency(Collection, Object):返回指定集合中指定元素的出現次數
        void copy(List dest, List src):將src中的內容複製到dest中
        boolean replaceAll(List list, Object oldVal, Object newVal):使用新值替換List對象所有舊值
     */

    @Test
    public void test2() {
        List list = new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(765);
        list.add(765);
        list.add(-97);
        list.add(0);
        list.add(-3);

        // java.lang.IndexOutOfBoundsException: Source does not fit in dest
//        List dest = new ArrayList<>();
//        Collections.copy(dest, list);
        List dest = Arrays.asList(new Object[list.size()]);
        System.out.println(dest.size());
        System.out.println(dest);
        Collections.copy(dest, list);
        System.out.println(dest);


        /*
            Collections類中提供了多個synchronizedXxx()方法
            可將指定集合包裝成線程同步的集合，從而可以解決多線程併發訪問集合時的線程安全問題
         */

        // 返回的list1即為線程安全的list
        List list1 = Collections.synchronizedList(list);
        System.out.println(list1);

    }

    @Test
    public void test1() {
        List list = new ArrayList<>();
        List list1 = new ArrayList<>();
        list.add(123);
        list.add(43);
        list.add(765);
        list.add(765);
        list.add(765);
        list.add(-97);
        list.add(0);
        list.add(-3);
        System.out.println(list);
//        Collections.reverse(list);
//        Collections.shuffle(list);
//        Collections.sort(list);
//        Comparator com =test11();
//        Collections.sort(list, com);
//        Collections.swap(list, 1, 2);
//        int frequency = Collections.frequency(list, 765);
//        System.out.println(frequency);
//        Integer max = (Integer) Collections.max(list);
//        System.out.println(max);
        Collections.copy(list1, list);
        System.out.println(list);
        System.out.println(list1);
    }

    private Comparator test11() {
        // 按照年齡從小到大排列
        return (o1, o2) -> {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                Integer u1 = (Integer) o1;
                Integer u2 = (Integer) o2;
                int compare = Integer.compare(u1, u2);
//                if(compare == 0) {
//                    return u1.compareTo(u2.getName());
//                }

                return compare;
//                return -compare;
            } else {
                throw new RuntimeException("輸入數據的類型不匹配");
            }
        };
    }
}
