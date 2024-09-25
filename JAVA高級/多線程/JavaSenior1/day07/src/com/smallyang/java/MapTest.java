package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * 一、Map的實現類的結構：
 * |----Map接口: 雙列集合，用來存儲一對(key - value)一對的數據 -->高中函數:y=f(x)
 * |----HashMap: 作為Map的主要實現類; 線程不安全的,效率高;存儲null的key and value
 * |----LinkedHashMap: 保證在遍歷map元素時，可以按照添加的順序實現遍歷。
 * 原因： 在原有的HashMap底層結構基礎上，添加了一對指針，指向前一個和後一個元素。
 * 對於頻繁的遍力操作，此類執行效率高於HashMap
 * |----TreeMap: 保證按照添加的keu-value對進行排序，實現排序遍歷。此時考慮key的自然排序或定制排序
 * 底層使用紅黑樹，在往下二叉樹，在往下是排序二叉樹
 * |----HashTable: 作為古老的實現類; 線程安全的,效率低;不能存儲null的key and value
 * |----Properties: 常用來處理配置文件。 key 和 value 都是 String類型。
 * <p>
 * <p>
 * HashMap的底層： 數組 + 鍊表  (jdk7之前)
 * 數組 + 鍊表 + 紅黑樹 (jdk8)
 * <p>
 * <p>
 * <p>
 * 面試題：
 * 1. HashMap的底層實現原理?
 * 2. HashMap和 Hashtable異同?
 * 3. CurrentHashMap 與 Hashtable異同?(暫時不講)
 * <p>
 * <p>
 * 二、Map結構的理解
 * Map中的key，無序的、不可重複的，使用Set存儲所有的key(什麼map對應什麼set，HashMap -> HashSet，LinkedHashMap - > LinkedHashSet..)
 * -> key所在的類要重寫equals()和hashCode() (以HashMap為例)
 * -> value所在的類要重寫equals()
 * Map中的value:無序的、可重複的，使用Collection存儲所有的value。
 * 一個鍵值對:key-value構成了一個Entry對象。
 * Map中的Entry:無序的、不可重複的，使用Set存儲所有的entry
 * <p>
 * 三、HashMap的底層實現原理? 以jdk7為例說明：
 * HashMap map = new HashMap();
 * 在實例化以後，底層創建了長度是16的一堆數組Entry[] table.+
 * ...可能已經執行過多次put...
 * map.put(key1, value1):
 * 首先，調用key1所在類的hashCode()計算key1哈希值，此哈希值經過某種算法計算以後，
 * 得到Entry數組中的存放位置。
 * <p>
 * 如果此位置上的數據為空，此時的keu1-value1添加成功 ----情況1
 * 如果此位置上的數據不為空，(意味著此位置上存在一個或多個數據(以練表形式存在))，
 * 比較key1和已經存在的一個或多個數據的哈希值:
 * 如果key1的哈希值與已經存在的數據的哈希值都不相同，此時key1-value1添加成功。----情況2
 * 如果key1的哈希值與已經存在的某一個數據(key2-value2)的哈希值相同，繼續比較: 調用key1所在類的equals(key2)，
 * 比較：
 * 如果equals()返回false:此時的keu1-value1添加成功 ----情況3
 * 如果equals()返回true:使用value1替換value2值。
 * <p>
 * 補充：關於情況2和情況3：此時key1-value1和原來的數據以練表的方式存儲。
 * <p>
 * 在不斷的添加過程中，或涉及到擴容問題:當超出臨界值(且要存放的位置非空)時，擴容。擴容為原來容量的2倍，並將原有的數據複製過來。
 * <p>
 * jdk8相較於jdk7在底層實現方面的不同：
 * 1. new HashMap():底層沒有創建一個長度為16的Entry的陣列
 * 2. jdk8底層的數組是:Node[]，而非Entry[]
 * 3. 首次調用put()方法時，底層創建長度為16的數組
 * 4.  jdk7底層結構只有：數組 + 鏈表。
 * jdk8中底層結構：數組 + 鏈表 + 紅黑數。
 * 當數組的某一個索引位置上的元素以鏈表形式存在的數據個數 > 8 且 當前數組的長度 > 64時，此時
 * 此索引位置上的所有數據改為使用紅黑樹存儲。
 * <p>
 * 四、LinkedHashMap的底層實現原理(了解)
 * <p>
 * 源碼中：
 * static class Entry<K,V> extends HashMap.Node<K,V> {
 * Entry<K,V> before, after;// 能夠記錄添加的元素的先後順序
 * Entry(int hash, K key, V value, Node<K,V> next) {
 * super(hash, key, value, next);
 * }
 * }
 * <p>
 * 五、Map中定義的方法：
 * 總結: 常用方法:
 * 添加:put(Object key: Object value)
 * 刪除:remove(Object key)
 * 修改:put(Object key: Object value)
 * 查詢:get(Object key)
 * 長度:size()
 * 遍歷:keySet()/values()/entrySet()
 *
 *
 *
 *
 * @author USER
 * @date 2024-05-12 上午 10:23
 */
public class MapTest {
    /*
        Set keySet() 返回所有key構成的set集合
        Collection values()  返回所有vlaue構成的Collection集合
        Set entrySet()  返回所有key-value對構成的Set集合
     */
    @Test
    public void test5() {
        Map map = new HashMap();
        map.put("AA", 123);
        Object put1 = map.put(45, 1234);
        map.put("BB", 456);
        // 遍歷所有的key集:keySet()
        Set set = map.keySet();
        Iterator iteratorKeySet = set.iterator();
        while (iteratorKeySet.hasNext()) {
            System.out.println(iteratorKeySet.next());
        }

        System.out.println();

        // 遍歷所有的value集: values()
        Collection values = map.values();
        for (Object obj : values) {
            System.out.println(obj);
        }

        System.out.println();

        // 遍歷所有的key:value
        // 方式一； entrySet():
        Set setEntrySet = map.entrySet();
        Iterator iterator = setEntrySet.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            //entrySet集合中的元素都是entry
            Map.Entry entry = (Map.Entry) next;
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println();
        // 方式二:
        Set set1 = map.keySet();
        Iterator iteratorKeySet1 = set1.iterator();
        while (iteratorKeySet1.hasNext()) {
            Object next = iteratorKeySet1.next();
            Object o = map.get(next);
            System.out.println(next + ":" + o);

        }
    }

    /*
        獲取指定key
        包含指定key
        包含指定value
        key-value對的個數
        isEmpty: 判斷當前map是否為空
        equals():判斷當前map和參數對象obj是否相等


     */
    @Test
    public void test4() {
        Map map1 = new HashMap();
        map1.put("AA", 123);
        Object put = map1.put(45, 123);
        map1.put("BB", 456);

        // get()
        Map map = new HashMap();
        map.put("AA", 123);
        Object put1 = map.put(45, 123);
        map.put("BB", 456);
        System.out.println(put1);
        System.out.println(map.get(45));
        System.out.println(map.get(455));

        System.out.println("equals-" + map1.equals(map));
        // containsKey()
        boolean bb = map.containsKey("BB");
        boolean ee = map.containsKey("EE");
        System.out.println(bb);
        System.out.println(ee);

        // containsValue
        boolean b = map.containsValue(123);
        boolean bc = map.containsValue(999);
        System.out.println(b);
        System.out.println(bc);

        // size()
        System.out.println(map.size());

        // isEmpty()
        boolean empty = map.isEmpty();
        System.out.println(empty);

        map.clear();
        System.out.println(map.isEmpty());
    }

    /*
        添加、刪除、修改
     */
    @Test
    public void test3() {
        // put()
        Map map = new HashMap();
        // 添加
        map.put("AA", 123);
        Object put = map.put(45, 123);
        map.put("BB", 123);
        // 修改
        Object aa = map.put("AA", 87);
        System.out.println(map);
        System.out.println(put);
        System.out.println(aa);

        // putAll()
        Map map1 = new HashMap();
        map1.put("CC", 123);
        map1.put("DD", 123);
        map.putAll(map1);
        System.out.println(map);

        // remove()
        Object cc = map.remove("CC");
        System.out.println(map);
        System.out.println(cc);

        // clear()
        map.clear();
        System.out.println(map);// 與map = null操作不同
        System.out.println(map.size());
    }

    @Test
    public void test2() {
        Map map = new HashMap<>();
        map.put(123, "AA");
        map.put(345, "BB");
        map.put(12, "CC");
        System.out.println(map);


        Map map1 = new LinkedHashMap();
        map1.put(123, "AA");
        map1.put(345, "BB");
        map1.put(12, "CC");
        System.out.println(map1);
    }

    @Test
    public void test1() {
        Map map = new HashMap();
        map.put(null, null);

        // java.lang.NullPointerException
//        Map map1 = new Hashtable<>();
//        map1.put(null, null);

    }
}
