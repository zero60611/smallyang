package com.smallyang.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;



/**
 * @author USER
 * @date 2024-04-13 上午 09:27
 */

@Repeatable(MyAnnotations.class)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE, TYPE_PARAMETER, TYPE_USE})
//@Target({FIELD, METHOD, PARAMETER, LOCAL_VARIABLE, MODULE})
public @interface MyAnnotation {

    // 屬性，成員變量
    String value() default "hello small yang";
//    String[] value();
}
