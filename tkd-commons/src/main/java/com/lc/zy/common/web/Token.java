/**
 * 
 */
package com.lc.zy.common.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于防止表单重复提交的注解.
 * 在需要生成token的方法上使用@Token，在删除token的方法上使用@Token(Type.REMOVE).
 * 
 * @author Wu.Yanhong
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {
	public static enum Type {NEW, REMOVE;}
	
	public Type value() default Type.NEW ;
}