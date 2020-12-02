package com.zfs.framework.autotest.ui.element.impl;

import java.util.List;
import java.util.Map;

import com.zfs.framework.autotest.ui.driver.UIDriver;
import com.zfs.framework.autotest.ui.element.Element;
import com.zfs.framework.autotest.ui.element.locate.ElementEntityLocator;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础元素
 *
 * @author zfs
 * @date 2020/11/27 22:57
 */
public abstract class BaseElement<Entity> implements Element<Entity> {
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	protected Element<Entity> parent;
	@Getter
	@Setter
	protected List<Element<Entity>> children;

	@Setter
	protected UIDriver<Entity> driver;
	@Getter
	@Setter
	protected ElementEntityLocator<Entity> locator;

	@Override
	public void init(Map<String, Object> paramMap) {
		//do nothing , overwrite to use
	}
}
