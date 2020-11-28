package com.zfs.framework.autotest.annotation;

import com.zfs.framework.autotest.ui.element.BaseElement;

/**
 * 自动装配元素注解
 * 被标记的元素会被自动装配
 *
 * @author zfs
 * @date 2020/11/28 23:39
 */
public @interface ElementAutoWire {
	/**
	 * 元素名称，加强可读性属性
	 *
	 * @return
	 */
	String name() default "";

	/**
	 * 元素类型
	 *
	 * @return
	 */
	Class<? extends BaseElement<?>> type();

	/**
	 * 元素定位表达式
	 *
	 * @return
	 */
	String express();
}
