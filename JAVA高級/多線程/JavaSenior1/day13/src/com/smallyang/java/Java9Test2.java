package com.smallyang.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author USER
 * @date 2024-10-27 上午 09:57
 */
public class Java9Test2 {

    public static void main(String[] args) {
        /*
        takeWhile：
        行為差異：
            filter：會對整個 Stream 進行逐一檢查，並將符合條件的所有元素保留。即使在 Stream 中間遇到不符合條件的元素，filter 仍會繼續檢查直到流結束。
            takeWhile：從流的開始依序檢查元素，只要條件成立就繼續，當遇到第一個不符合條件的元素時就會停止，並丟棄該元素及後續的所有元素。
        停止條件：
            filter 不會停止檢查，即使遇到不符合條件的元素，它仍會繼續檢查剩餘的元素。
            takeWhile 會在遇到第一個不符合條件的元素時立即停止，不再檢查後續元素。
         */
        List<Integer> list = Arrays.asList(23, 43, 45, 55, 61, 54, 32, 2, 45, 89, 7);
        // 一、
        // 返回從從開頭開始按照指定規則儘量多的元素
//        list.stream().takeWhile(x -> x< 60).forEach(System.out::println);

        // 二、
        // 跟takeWhile正好相反，逐一檢查，符合地都drop，不符合的，後面元素全部返回
//        list.stream().dropWhile(x -> x < 60).forEach(System.out::println);

        // 三、
        //  of參數中的多個元素，可以包含多個值，且可包含有值為null
        //  可變形參
//        Stream<Integer> stream1 = Stream.of(1, 2, 3, null);
//        stream1.forEach(System.out::println);

        //  of()參數不能存儲單個null值，不能僅單個元素且為null，否則，報異常
//        Stream<Object> stream2 = Stream.of(null);
        //  這個可以
//        Stream<Object> stream2 = Stream.of(null, null);
//        stream2.forEach(System.out::println);

        // ofNullable 不允許放多個參數，只能一個
//        Integer i = 10;
//        i = null;
        //ofNullable():形參變量是可以為null值的單個元素
//        Stream<Object> stream3 = Stream.ofNullable(i);
//        long count = stream3.count();
//        System.out.println(count);
//        stream3.forEach(System.out::println); //要註解，因為stream被closed了


        // 四、
        // 會無限增加下去
//        Stream.iterate(0, x->x+1).forEach(System.out::println);
//        Stream.iterate(0, x->x+1).limit(10).forEach(System.out::println);

//        Stream<Integer> aa = list.stream();
        // java9中重載的方法
//        Stream.iterate(0, x -> x < 100, x -> x+1).forEach(System.out::println);


//        List<String> list1 = new ArrayList<>();
//        System.out.println(list1 == null);


        // java 9 新特性十一:Optional提供了新的方法stream()
        List<String> list1 = new ArrayList<>();
        list1.add("aa");
        list1.add("bb");
        list1.add("cc");

        /*
        Optional.ofNullable(list1)：
            這行會創建一個 Optional<List<String>>，如果 list1 為 null，那麼 list11 會是一個空的 Optional。否則，list11 會包含 list1 這個 List<String>。
            list11.stream()：

            這會將 Optional 轉換為 Stream。如果 Optional 有值（即 list1 不為 null），那麼這個 Stream 會包含一個元素，即 list1 這個 List<String>。如果 Optional 為空，則會是一個空的 Stream。
            flatMap(x -> x.stream())：

            flatMap 會將 Stream<List<String>> 中的每一個 List 攤平成為 Stream<String>。這裡的 x 代表 List<String>，x.stream() 會把這個 List 轉換成 Stream<String>，然後將所有的元素展開成一個 Stream<String>。
            如果使用 map，則會生成一個 Stream<Stream<String>>，但使用 flatMap 則會把所有內部的 Stream 攤平成為單一的 Stream<String>。
            forEach(System.out::println)：

            最後一步會對攤平後的 Stream<String> 中的每個元素逐一進行操作，在這裡每個元素都會被打印出來。
         */
        Optional<List<String>> list11 = Optional.ofNullable(list1);
        Stream<List<String>> stream = list11.stream();
//        System.out.println(stream.count());// 1
        // map = add,  flatMap = addAll
        // 使用 flatMap 將 Stream<List<String>> 攤平成 Stream<String>，再對每個字串打印
        stream.flatMap(x -> x.stream()).forEach(System.out::println);

        /*
        與 map 和 flatMap 的區別
        map：如果你用 map，會得到一個 Stream<Stream<String>>，
                這是一個包含 Stream 的 Stream。
                對於 map 而言，Stream 內的每一個元素還是 Stream，所以無法直接進行打印。
        flatMap：則會將這些內部的 Stream 攤平成單一的 Stream<String>，所以可以直接使用 forEach 來操作每一個元素。
         */
    }
}
