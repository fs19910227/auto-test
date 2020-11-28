package com.zfs.framework.autotest.ui.element.locate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import lombok.Getter;
import org.openqa.selenium.By;

/**
 * 选择类型
 *
 * @author zfs
 * @date 2020/11/28 21:06
 */
public enum SelectType {
	ID("id", By::id),
	NAME("name", By::name),
	TAG("tag", By::tagName),
	XPATH("xpath", By::xpath);

	@Getter
	String prefix;
	Function<String, By> mapping;

	static Map<String, SelectType> selectTypeMap;

	SelectType(String prefix, Function<String, By> mapping) {
		this.prefix = prefix;
		this.mapping = mapping;
	}

	private static SelectType matchByPrefix(String prefix) {
		if (selectTypeMap == null) {
			selectTypeMap = new HashMap<>();
			for (SelectType value : SelectType.values()) {
				selectTypeMap.put(value.getPrefix(), value);
			}
		}
		return selectTypeMap.getOrDefault(prefix, SelectType.XPATH);
	}

	public static By parse(String prefix, String express) {
		return matchByPrefix(prefix).mapping.apply(express);
	}
}
