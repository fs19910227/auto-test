package com.zfs.framework.autotest.ui.element.ability;

import com.zfs.framework.autotest.ui.element.locate.ElementEntityLocator;

/**
 * 可定位的
 *
 * @author zfs
 * @date 2020/12/01 22:23
 */
public interface Locatable<Entity> {
	/**
	 * 获取元素定位器
	 *
	 * @return
	 */
	ElementEntityLocator<Entity> getLocator();
}
