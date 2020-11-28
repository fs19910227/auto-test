package com.zfs.framework.autotest.ui.element.atomic;

import java.util.Collections;
import java.util.List;

import com.zfs.framework.autotest.ui.element.BaseElement;
import com.zfs.framework.autotest.ui.element.Element;

/**
 * 原子元素
 * 最基本的元素，不存在子元素
 *
 * @author zfs
 * @date 2020/11/27 22:59
 */
public abstract class AtomicElement<Entity> extends BaseElement<Entity> {
	@Override
	public List<Element<Entity>> getChildren() {
		return Collections.emptyList();
	}
}
