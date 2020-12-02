package com.zfs.framework.autotest.ui.element.impl.atomic;

import com.zfs.framework.autotest.ui.element.ability.Clickable;

/**
 * 按钮元素
 * 定位为只有点击,无法取值或者设置值的类型
 *
 * @author zfs
 * @date 2020/11/27 23:01
 */
public class Button<Entity> extends AtomicElement<Entity> implements Clickable {

	@Override
	public void click() {
		driver.click(this);
	}
}
