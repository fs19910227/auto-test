package com.zfs.framework.autotest.ui.element;

import java.util.List;
import java.util.Map;

import com.zfs.framework.autotest.ui.element.ability.Locatable;

/**
 * 顶层接口
 * 所有UI界面上的内容都可以抽象成一个元素
 * 元素之间可以通过嵌套、组合成树形关系
 *
 * @author zfs
 * @date 2020/11/27  22:35
 */
public interface Element<Entity> extends Locatable<Entity> {
	/**
	 * 获取父元素
	 *
	 * @return
	 */
	Element<Entity> getParent();

	/**
	 * 获取子元素
	 *
	 * @return
	 */
	List<Element<Entity>> getChildren();

	/**
	 * 初始化元素
	 *
	 * @param paramMap 参数map
	 */
	void init(Map<String, Object> paramMap);

}
