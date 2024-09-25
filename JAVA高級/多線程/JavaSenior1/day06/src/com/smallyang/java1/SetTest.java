package com.smallyang.java1;

import com.smallyang.java.Perosn;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 1. Set接口的框架
 * |----Collection接口: 單列集合，用來存儲一個一個的對象
 * |----Set接口: 存儲無序、不可重複的數據。 -->高中講的"集合"
 * |----HasSet：作為Set接口的主要實現類;線程不安全;可以存儲null值
 * |----LinkedHashSet：作為HashSet的子類;遍歷其內部數據時，可以按照添加的順序去遍歷。
 *                      對於頻繁的遍歷操作，LinkedHashSet效率高於HashSet
 * |----TreeSet： 可以按照添加對象的指定屬性，進行排序。
 * <p>
 * <p>
 * 1.Set接口中沒有額外定義新的方法，使用的都是Collection中聲明過的方法。
 *
 * 2.要求:向Set中添加的數據，其所在的類一定要重寫hashCode() equals()方法
 *   要求:重寫的hashCode() equals()要保持一致性:相等的對象必須有相等的散列碼
 *   重寫兩個方法的小技巧:對象中用作equals()方法比較的Field，都應該用來計算hashCode
 * @author USER
 * @date 2024-05-01 下午 06:14
 */
public class SetTest {
    /*
    一、 Set:存儲無序的、不可重複的數據
        1. 無序性:
            不等於隨機性。存儲的數據在底層數組中並非按照數組索引的順序添加。
            而是根據數據的哈希值決定的。

        2. 不可重複性:
            保證添加的元素按照equals()判斷時，不能返回true.
            即:相同的元素只能添加一個。

    二、 添加元素的過程：以HashSet為例:
        我們向HashSet中添加元素a,首先調用元素a所在類的hashCode()方法，計算元素a的哈希值，
        此哈希值接著通過某種算法計算出在HashSet底層數組中的存放位置(即為:索引位置),
        判斷數組此位置上是否已經有元素:
        如果此位置上沒有其他元素,則元素a添加成功. ---> 情況1
        如果此位置上有其他元素b(或以鏈表形式存在的多個元素),則比較元素a與元素b的hash值:
            如果hash值不相同,則元素a添加成功 ---> 情況2
            如果hash值相同,進而需要調用元素a所在類的equals()方法:
                equals()返回true,元素a添加失敗.
                equals()返回false,則元素a添加成功. ---> 情況3

        對於添加成功的情況2和情況3而言: 元素a與已經存在指定索引位置上數據以鏈表的方式存儲.
        jdk7:元素a放到數組中,指向原來的元素.
        jdk8:原來的元素在數組中,指向元素a.
        總結:七上八下

        HashSet底層：數組+鏈表的結構

     */
    @Test
    public void test1() {
        Set set = new HashSet();
//        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add("CC");
        set.add(129);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // LinkedHashSet的使用
    // LinkedHashSet作為HashSet的子類，再添加數據的同時，每個數據還維護了兩個引用，記錄此數據
    // 前一個和後一個數據。
    // 優點: 對於頻繁的遍歷操作，LinkedHashSet效率高於HashSet
    @Test
    public void test() {
        Set set = new LinkedHashSet();
//        Set set = new LinkedHashSet();
        set.add(456);
        set.add(123);
        set.add(123);
        set.add("AA");
        set.add(new User("Tom", 12));
        set.add(new User("Tom", 12));
        set.add("CC");
        set.add(129);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
