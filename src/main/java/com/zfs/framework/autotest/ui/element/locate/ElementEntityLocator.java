package com.zfs.framework.autotest.ui.element.locate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.zfs.framework.autotest.ui.element.Element;

/**
 * 元素实体定位器
 * 根据实际的元素处理方式定位元素
 *
 * @author zfs
 * @date 2020/11/28 22:39
 */
public interface ElementEntityLocator<Entity> {
	/**
	 * 绑定元素
	 *
	 * @param logicElement logicElement
	 * @param express      定位表达式
	 */
	void bind(Element<Entity> logicElement, String express);

	/**
	 * 定位实际元素
	 *
	 * @return realElement
	 */
	Entity locateElement();

	/**
	 * 带有超时的元素定位
	 *
	 * @param time     时间
	 * @param timeUnit 时间单位
	 * @throws TimeoutException 超时抛出异常
	 */
	void timedLocate(long time, TimeUnit timeUnit) throws TimeoutException;
}
