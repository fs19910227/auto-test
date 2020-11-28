package com.zfs.framework.autotest.ui.driver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.zfs.framework.autotest.ui.element.Element;
import com.zfs.framework.autotest.ui.element.locate.ElementEntityLocator;

/**
 * 基于selenide
 * 提供驱动web元素的能力
 *
 * @author zfs
 * @date 2020/11/28 21:06
 */
public class WebUIDriver implements UIDriver<SelenideElement> {
	/**
	 * 刷新当前页面
	 */
	public void refresh() {
		Selenide.refresh();
	}

	@Override
	public void access(String url) {
		Selenide.open(url);
	}

	private SelenideElement locate(Element<SelenideElement> element) {
		ElementEntityLocator<SelenideElement> locator = element.getLocator();
		return locator.locateElement();
	}

	@Override
	public void click(Element<SelenideElement> element) {
		SelenideElement locate = locate(element);
		if (locate != null) {
			locate.click();
		}
	}

	@Override
	public void setText(Element<SelenideElement> element, String text) {
		SelenideElement locate = locate(element);
		if (locate != null) {
			locate.clear();
			locate.setValue(text);
		}

	}

	@Override
	public String getValue(Element<SelenideElement> element) {
		SelenideElement locate = locate(element);
		if (locate != null) {
			return locate.getValue();
		} else {
			return null;
		}
	}
}
