package com.smallyang.java1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author USER
 * @date 2024-09-06 上午 08:06
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
//@Retention(RetentionPolicy.CLASS)// 只有內存中的結果才能通過反射獲取
@Retention(RetentionPolicy.RUNTIME)// 只有內存中的結果才能通過反射獲取
public @interface MyAnnotation {
    String value() default "hello";
}
