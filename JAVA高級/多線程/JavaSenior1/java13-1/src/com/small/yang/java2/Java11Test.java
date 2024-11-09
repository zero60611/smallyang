package com.small.yang.java2;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class Java11Test {

    // java 11 新特性一:String中新增的方法
    @Test
    public void test1() {
//        isBlank(); 是否為空
        System.out.println("  \t  \t  \n".isBlank());
//        strip(); 前後都去除
        System.out.println("---------" + "  \t ABC \t  \n".strip() + "---------");
        System.out.println("---------" + "  \t ABC \t  \n".trim() + "---------");
//        stripTrailing(); 左邊沒去除
        System.out.println("---------" + "  \t ABC \t  \n".stripTrailing() + "---------");

//        stripLeading(); 右邊沒去除
        System.out.println("---------" + "  \t ABC \t  \n".stripLeading() + "---------");

//        repeat(int count); 複製
        String str1 = "abc";
        String str2 = str1.repeat(5);
        System.out.println(str2);

//        lines().count(); 行數統計
        String str3 = "abcdefg \n  \n  \n  \n ";
        System.out.println(str3.lines().count());
    }

    //java11 新特性二: Optional新增的方法
    @Test
    public void test2() {
        Optional<Object> op = Optional.empty();
        System.out.println(op.isPresent()); //判斷內部value是否存在
        System.out.println(op.isEmpty());   //判斷內部是否為空

        op = Optional.of("abc");
        // orElseThrow():value 非空，返回value，否則拋異常NoSuchElementException
        var obj = op.orElseThrow();
        System.out.println(obj);

        // or  針對 optional
        op = Optional.empty();
        Optional<String> op1 = Optional.of("hello");
        //  供給者 是 不放東西 但返回東西，所以()空參 -> return
        Optional<Object> op2 = op.or(() -> op1);
        System.out.println(op2);

        // 針對裡面的值到底要誰，一個返回容器，一個返回容器裡面的value
        Object o = op2.orElse("AA");
        System.out.println(o);
    }

    // java11新特性三： 局部變量類型推斷的升級
    @Test
    public void test3() {
        // 錯誤的形式：必須要有類型，可以加上var
//       Consumer<String> con1 =  (@Deprecated t) -> System.out.println(t.toUpperCase());

        // 正確的形式：
        // 使用var的好處是在使用lambda表達式時給參數加上註解
        Consumer<String> con2 = (@Deprecated var t) -> System.out.println(t.toUpperCase());
    }

    // java11新特性四： HttpClient替換原有的HttpURLConnection。
    @Test
    public void test4() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8080/test/")).build();
            HttpResponse.BodyHandler<String> responseHandler = HttpResponse.BodyHandlers.ofString();
            HttpResponse<String> res = client.send(req, responseHandler);

            CompletableFuture<HttpResponse<String>> httpResponseCompletableFuture = client.sendAsync(req, responseHandler);
//            httpResponseCompletableFuture.thenApply(t -> t.body()).thenAccept(System.out::println);
            httpResponseCompletableFuture.thenApply(HttpResponse::body).thenAccept(System.out::println);

            String body = res.body();
            System.out.println(body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
