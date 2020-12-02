package com.zfs.framework.autotest.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元素描述注解
 * 用于在扫描元素时解析并动态装配
 *
 * @author zfs
 * @date 2020/11/28 23:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Element {
	/**
	 * 元素名称，加强可读性属性
	 *
	 * @return
	 */
	String name() default "";

	/**
	 * 元素定位表达式
	 * 并非所有元素都可以定位，所以可空
	 *
	 * @return
	 */
	String express() default "";
}
