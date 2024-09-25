package com.smallyang.java;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Collection接口中聲明的方法的測試
 *
 * 向Collection接口的實現類的對象中添加數據obj時，，要求obj所在類要重寫equals()
 *
 * @author USER
 * @date 2024-04-22 上午 05:48
 */
public class CollectionTest {
    @Test
    public void test1() {

        Collection coll = new ArrayList();
        coll.add(123);
//        coll.add(new Perosn());
//        Perosn p = new Perosn("Jerry", 20);
//        coll.add(p);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        //1. contains(Object obj):判斷當前集合中是否包含obj
        // 我們在判斷時會調用obj對象所在類的equals()，所以自定義的類都要去重寫equals()
        boolean contains = coll.contains(123);
        System.out.println(contains);
        System.out.println(coll.contains(new String("Tom")));
//        System.out.println(coll.contains(p));

        // 改寫Perosn的equals方法才會變true，因為他contains是調用equals方法
        //     int indexOfRange(Object o, int start, int end) {
        //        Object[] es = elementData;
        //        if (o == null) {
        //            for (int i = start; i < end; i++) {
        //                if (es[i] == null) {
        //                    return i;
        //                }
        //            }
        //        } else {
        //            for (int i = start; i < end; i++) {
        //                if (o.equals(es[i])) {
        //                    return i;
        //                }
        //            }
        //        }
        //        return -1;
        //    }
        System.out.println(coll.contains(new Perosn("Jerry", 20)));

        //2. containsAll(Collection coll1):判斷形參coll1中的所有元素是否都存在於當前的集合中
//        Collection coll1 = Arrays.asList(123, 456); // true
        Collection coll1 = Arrays.asList(123, 4567);// false
        System.out.println(coll.containsAll(coll1));

    }

    @Test
    public void test2() {
        // 3. remove(Object obj)
        Collection coll  = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);
        System.out.println(coll);
        coll.remove(123);
        System.out.println(coll);
        coll.remove(new Perosn("Jerry", 20));
        System.out.println(coll);

        //4. removeAll(Collection coll1):從當前集合中移除coll1中所有的元素
        Collection coll1  = Arrays.asList(123, 456);// 可變形參相當於一個數組
        coll.removeAll(coll1);
        System.out.println(coll);//集合差集的操作
    }

    @Test
    public void test3() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);


        //5. retainAll(Collection coll1):交集:獲取當前集合coll1集合的交集，並返回給當前集合
//        Collection coll1  = Arrays.asList(123, 456, 789);
//        coll.retainAll(coll1);// 交集
//        System.out.println(coll);


        //6. equals(Object obj):要想返回true, 需要判斷當前集合和形參集合的元素都相同
        Collection coll1 = new ArrayList();
//        coll1.add(123);
        coll1.add(new Perosn("Jerry", 20));
        coll1.add(123);
        coll1.add(456);
        coll1.add(new String("Tom"));
        coll1.add(false);
        System.out.println(coll.equals(coll1));
    }// 順序變了，所以false

    @Test
    public void test4() {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(new Perosn("Jerry", 20));
        coll.add(456);
        coll.add(new String("Tom"));
        coll.add(false);

        //7. hashCode():返回當前對象的哈希值
        System.out.println(coll.hashCode());// 根據元素計算出來的一個數

        //8. 集合 ---> 數組: toArray()
        Object[] arr = coll.toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

        //拓展: 數組 --->集合:調用Arrays類的靜態方法asList()
        List<String> list = Arrays.asList(new String[]{"AA", "BB", "CC"});
        // 返回值是一個List，就相當於一個Collection，跟動態數組對應的集合List
        System.out.println(list);
        List<int[]> arr1 = Arrays.asList(new int[]{123, 456});// 可變形參，相當整個當成一個參數

        System.out.println(arr1);// 一個元素依偎著數組int類型，把整體結構當成返回一個元素
        System.out.println(arr1.size());


        List<Integer> arr2 = Arrays.asList(123, 456);// 這時候就認為兩個元素
        System.out.println(arr2);
        System.out.println(arr2.size());

        //  寫成包裝類的對象就會認為是兩個元素了
        List<Integer> arr3 = Arrays.asList(new Integer[]{123, 456});
        System.out.println(arr3);
        System.out.println(arr3.size());

        //9. iterator(): 返回Iterator接口的實例，用於遍歷集合。放在IteratorTest.java中測試
    }
}
