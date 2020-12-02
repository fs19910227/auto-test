package com.zfs.framework.autotest.ui.driver;

import com.zfs.framework.autotest.ui.element.Element;

/**
 * UI 驱动器,屏蔽具体的UI操作实现
 *
 * @author zfs
 * @date 2020/11/28 21:19
 */
public interface UIDriver<Entity> {
	/**
	 * 访问页面
	 *
	 * @param addressExpress 地址表达式
	 */
	void access(String addressExpress);

	/**
	 * 点击UI元素
	 */
	void click(Element<Entity> element);

	/**
	 * 填充UI元素
	 *
	 * @param text 要填充的内容
	 */
	void setText(Element<Entity> element, String text);

	/**
	 * 获取UI元素的值
	 *
	 * @return value
	 */
	String getValue(Element<Entity> element);
}
