package com.association.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation:注释
 * 用来跳过验证的PassToken
 *
 * @author baozi
 * @version 1.0
 * @date 2020/3/28 18:16
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken
{
    boolean required() default true;
}
