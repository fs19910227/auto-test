package com.zfs.framework.autotest.ui.element;

import java.util.List;

/**
 * 元素组合而成的合集
 *
 * @author zfs
 * @date 2020/11/28 23:20
 */
public interface ElementBox<Entity> {
	/**
	 * 列出所有元素
	 *
	 * @return
	 */
	List<Element<Entity>> elements();
}
