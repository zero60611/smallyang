package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 1.List接口框架
 * |----Collection接口: 單列集合，用來存儲一個一個的對象
 * |----List接口: 存儲有序、可重複的數據。 -->"動態"數組，替換原有數組
 *      |----ArrayList：作為List接口的主要實現類;線程不安全的，效率高。底層使用Object[] elementData存儲
 *      |----LinkedList:對於頻繁的插入、刪除操作，使用此類效率比ArrayList高;底層使用雙向鏈表存儲。
 *      |----Vector：作為List接口的古老實現類;線程安全，效率低。底層使用Object[] elementData存儲
 * <p>
 * <p>
 * 2.ArrayList的源碼分析：
 * 2.1 jdk7的情況下：
 * ArrayList list = new ArrayList();//底層創建了長度是10的Object[]數組elementData
 * list.add(123);//elementData[0] = new Integer(123);
 * ...
 * list.add(11);//如果此次的添加導致底層elementData數組容量不構，則擴容。
 * 預設情況下，擴容為原來的容量的1.5倍，同時需要將原有數組中的數據複製到新的數組中。
 * <p>
 * 結論：建議開發中使用帶參的構造器:ArrayLis list = new ArrayList(int capacity)
 * <p>
 * 2.2 jdk8中ArrayList的變化
 * ArrayList list = new ArrayList();//底層Object[] elementData初始化為{}.並沒有創建長度為10的數組
 * <p>
 * list.add(123);//第一次調用add()時，底層才創建了長度10的數組，並將數據123添加到elementData[0]
 * ...
 * 後續的添加和擴容操作與jdk7一樣。
 * <p>
 * 2.3 小結：jdk7中的ArrayList的創見類似於單例的惡漢式，而jdk8中的ArrayList的對象
 * 的創見類似於單例的懶漢式，延遲了數組的創建，節省內存。
 * <p>
 * 3.LinkedList的源碼分析:
 * LinkedList list = new LinkedList(); 內部聲明了Node類型的first和last屬性，預設為null
 * list.add(123);//將123封裝到Node中，創建了Node對象。
 * <p>
 * 其中，Node定義為：體現了LinkedList的雙項鏈表的說法
 * private static class Node<E> {
 * E item;
 * Node<E> next;
 * Node<E> prev;
 * <p>
 * Node(Node<E> prev, E element, Node<E> next) {
 * this.item = element;
 * this.next = next;
 * this.prev = prev;
 * }
 * }
 * <p>
 * 4. Vector的源碼分析:jdk7和jdk8中通過Vector()構造器創建對象時，底層都創建了長度為10的數組
 * 在擴容方面，預設擴容為原來的數組長度的2倍
 * <p>
 * 面試題:ArrayList、LinkedList、Vector三者的異同
 * 同： 三個類都是實現了List接口，存儲數據的特點相同:存儲有序、可重複的數據
 * <p>
 * 異： 見上
 * <p>
 * <p>
 * 5. List接口中的常用方法
 *
 * @author USER
 * @date 2024-04-26 上午 06:22
 */
public class ListTest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("SS");
        LinkedList<String> list2 = new LinkedList<>();
        list2.add("SS");
    }

    /*
        void add(int index, Object ele):在index位置插入ele元素
        boolean addAll(int index, Collection eles):從index位置開始將eles中所有元素添加進來
        Object get(int index):獲取指定index位置的元素
        int indexOf(Object obj):返回obj在當前集合中首次出現的元素
        int lastIndexOf(Object obj):返回obj在當前集合中末次出現的元素
        Object remove(int index):移除指定index位置的元素，並返回此元素
        Object set(int index, Object ele):設置指定index位置的元素為ele
        List subList(int fromIndex, int toIndex):返回從fromIndex到toIndex位置的子集合


        總結：常用方法
        增：add(Object obj)
        刪：remove(int index)/remove(Object obj)
        改：set(int index, Object obj)
        查：get(int index)
        插：add(int index, Object ele)
        長度：size()
        遍歷：1.Iterator迭代器方式
             2.增強for循環
             3.普通的循環
     */

    @Test
    public void test3() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        // 方式一:Iterator迭代器方式
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("*********************");

        // 方式二:增強for循環
        for (Object obj : list) {
            System.out.println(obj);
        }
        System.out.println("*********************");
        // 方式三:普通的循環
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void test2() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Perosn("Tom", 12));
        list.add(456);

        // int indexOf(Object obj):返回obj在當前集合中首次出現的元素
        int index = list.indexOf(456);// 返回obj在集合中首次出現的位置
        int index1 = list.indexOf(4567);// 如果不存在，返回-1
        System.out.println(index);
        System.out.println(index1);

        //  int lastIndexOf(Object obj):返回obj在當前集合中末次出現的元素,如果不存在，返回-1
        System.out.println(list.lastIndexOf(456));
        System.out.println(list.lastIndexOf(4567));

        // Object remove(int index):移除指定index位置的元素，並返回此元素
        // overload重載，與Collection.remove(Object obj)形參列表不同
        Object obj = list.remove(0);
        System.out.println(obj);
        System.out.println(list);

        // Object set(int index, Object ele):設置指定index位置的元素為ele
        list.set(1, "CC");
        System.out.println(list);

        //  List subList(int fromIndex, int toIndex):返回從fromIndex到toIndex位置左閉右開區間的子集合
        List list1 = list.subList(2, 4);
        System.out.println(list);
        System.out.println(list1);
    }


    @Test
    public void test1() {
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Perosn("Tom", 12));
        list.add(456);
        System.out.println(list);

        //  void add(int index, Object ele):在index位置插入ele元素
        list.add(1, "BB");
        System.out.println(list);

        //        boolean addAll(int index, Collection eles):從index位置開始將eles中所有元素添加進來
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
        System.out.println(list);
        System.out.println(list.size());

        //         Object get(int index):獲取指定index位置的元素
        System.out.println(list.get(0));
    }
}

//    void linkLast(E e) {
//        final Node<E> l = last;          // 1. 獲取當前鏈表的最後一個節點，並將其存儲在變量l中。
//        final Node<E> newNode = new Node<>(l, e, null);  // 2. 創建一個新的節點。新節點的前一個節點是l，存儲的數據是e，後一個節點為null。
//        last = newNode;                  // 3. 更新鏈表的最後一個節點指向新節點。
//        if (l == null)                   // 4. 檢查之前的最後一個節點l是否為null，也就是檢查鏈表是否為空。
//            first = newNode;             // 5. 如果鏈表為空（l為null），則新節點也成為鏈表的第一個節點。
//        else
//            l.next = newNode;            // 6. 如果鏈表不為空，則將之前的最後一個節點l的next指向新節點，從而將新節點連接到鏈表的末尾。
//        size++;                          // 7. 增加鏈表的節點計數，即鏈表的大小。
//        modCount++;                      // 8. 增加修改計數，這通常用於追踪結構修改的次數，以便實現fail-fast行為（如迭代器的快速失效機制）。
//    }

