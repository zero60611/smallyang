package com.smallyang.java1;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 註解的使用
 * <p>
 * 1. 理解Annotation:
 * 1.1 jdk5.0新增的功能
 * 1.2 Annotation 其實就是代碼裡的特殊標記，這些標記可以在編譯、類加載、運行時被讀取
 * 並執行相應的處理。通過使用註解程序員可以在不改變原有邏輯情況下，在源文件嵌入補充訊息。
 * <p>
 * 1.3 在JavaSE中，註解的使用目的比較簡單，例如標記過時，忽略警告等，，在JavaSE/Android中註解
 * 很重要角色，例如配置應用程序的任何切麵，代替JAVASE舊版遺留繁冗代碼跟XML配置。
 * <p>
 * 2. Anntation使用示例:
 * 2.1  示例一：生成文檔相關的註解
 * 2.2  示例二：在編譯時進行格式檢查(JDK內製的三個基本註解)
 *
 * @author USER
 * @Override 限定重寫父類方法，該註解只能用於方法
 * @Deprecated:用於表示所修飾的元素(類、方法)已過時。通常是因為所修飾的結構危險或存在或存在更好的選擇
 * @SuppressWarnings:抑制編譯器警告 2.3  示例三：跟蹤代碼依賴性，實現替代配置文件功能
 * <p>
 * 3. 如何自定義註解
 * 3.1 註解聲明為@interface，跟interface完全沒有任何關係
 * 3.2 內部定義成員，通常使用value表示
 * 3.3 可以指定成員的預設值，使用default定義
 * 3.4 如果自定義註解沒有成員，表明是一個標識作用
 * <p>
 * 如果註解有成員，在使用註解時，需要指名成員的值，除非有default
 * 自定義註解必須配上註解的信息處理流程(使用反射)才有意義。
 * 自定義註解通過都會指名兩個元註解:Retention、Target
 * <p>
 * <p>
 * 4.jdk提供的4種元註解
 * 元註解: 對現有的註解進行解釋說明的註解
 * Retention :指定所修飾的Annotation的生命週期: SOURCE、CLASS(預設行為)、RUNTIME
 * 只有聲明為RUNTIME生命週期的註解，才能通過反射獲取。
 * @Retention(RetentionPolicy.RUNTIME) public @interface MyAnnotation {
 * 運行時就可以獲取這個類上面的註解了
 * <p>
 * Target :用於指定被修飾的Annotation能用於修飾那些程序元素
 * *****出現頻率較低*********
 * Documented :表示所修飾的註解在被javadoc解析時，保留下來
 * Inherited : 被他修飾的Annotation將具有繼承性
 * <p>
 * 5. 通過反射來獲取註解信息 ----到反射內容時系統講解
 * <p>
 * 6. jdk 8 中註解的新特性: 可重複註解、類型註解
 * 6.1 可重複註解:
 * 6.1.1 在MyAnnotation上聲明@Repeatable(MyAnnotations.class)，成員值為MyAnnotations.class
 * 6.1.2 MyAnnotation的target和Retention等元註解 與 MyAnnotations 相同。(這邊測試inherited也要，但影片不用)
 * <p>
 * 6.2 類型註解:
 * ElementType.TYPE_PARAMETER 表示該註解能寫在類型變量的聲明語句中(如:泛型聲明)
 * ElementType.TYPE_USE 表示該註解能寫在使用類型的任何語句中
 * @date 2024-04-13 上午 08:28
 */
public class AnnotationTest {
    public static void main(String[] args) {

        Student student = new Student();
        student.walk();
//        student.wal1k();

        Date date = new Date(2020, 11, 05);
        System.out.println(date);

        @SuppressWarnings({"unused", "rawtypes"})
//        @SuppressWarnings("unused")
        ArrayList list = new ArrayList<>();
//        ArrayList list = new ArrayList<>();
    }

    @Test
    public void testGetAnnotation() {
        Class clazz = Student.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }

    }
}


// jdk 8之前的寫法
//@MyAnnotations({@MyAnnotation(value = "hi"), @MyAnnotation(value = "abc")})
@MyAnnotation(value = "hi")
@MyAnnotation(value = "abc")
class Person {
    private String name;
    private int age;

    public Person() {
    }

    @MyAnnotation()
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void walk() {
        System.out.println("人走路");
    }

    public void eat() {
        System.out.println("人吃飯");
    }
}

interface Info {
    void show();
}

class Student extends Person implements Info {
    //    @Override
    public void wal1k() {
        System.out.println("學生走路");
    }

    @Override
    public void show() {

    }
}

// @MyAnnotation的target加 TYPE_PARAMETER就可以在參數泛型加入註解
class Genetic<@MyAnnotation T> {
    public void show()  throws @MyAnnotation RuntimeException{
        // @MyAnnotation的target加 TYPE_USE就可以在參數泛型加入註解
        // 註解的這些結構一樣可以透過反射去拿到這些註解
        // 看看註解的值是多少，看看到底想要幹什麼，再去使用這些註解
        ArrayList<@MyAnnotation String> list = new ArrayList<>();
        int num =(@MyAnnotation int) 10L;
    }
}