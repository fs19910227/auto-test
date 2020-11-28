package com.zfs.framework.autotest.ui.element.locate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.zfs.framework.autotest.ui.element.Element;
import org.openqa.selenium.By;
import org.springframework.util.Assert;

/**
 * web ui 定位器
 * 与元素一对一绑定
 *
 * @author zfs
 * @date 2020/11/28 22:54
 */
public class WebUIElementLocator
	implements ElementEntityLocator<SelenideElement> {
	/**
	 * 对应的实际UI元素
	 */
	private SelenideElement realElement;
	/**
	 * 逻辑UI元素
	 */
	private Element<SelenideElement> logicElement;
	/**
	 * 选择器
	 */
	private By by;
	/**
	 * 是否绝对定位
	 */
	private boolean absoluteLocate = false;

	@Override
	public void bind(Element<SelenideElement> logicElement, String express) {
		this.logicElement = logicElement;
		Assert.hasText(express, "ui express cannot be null");
		parseExpress(express);
	}

	/**
	 * 解析表达式
	 * 解析规则如下
	 * 格式 type:express
	 *
	 * eg:
	 * 通过id选择  id:xxx
	 * 通过name选择 name:xxx
	 * 通过xpath选择 xpath:xxx
	 * 通过tag选择 tag:xxx
	 *
	 * 默认会使用相对定位，即在父元素的基础上使用表达式定位
	 * 若想使用决定定位，请在表达式前加/
	 * eg:
	 * 通过id选择,绝对定位  /id:xxx
	 *
	 * @param express 定位表达式
	 **/
	private void parseExpress(String express) {
		String[] split = express.split(":");
		String p = split[0];
		if (p.startsWith("/")) {
			absoluteLocate = true;
			p = p.substring(1);
		}
		String e = split[1];
		this.by = SelectType.parse(p, e);
	}

	@Override
	public SelenideElement locateElement() {
		if (realElement != null) {
			return realElement;
		}
		if (absoluteLocate) {
			this.realElement = Selenide.$(by);
		} else {
			Element<SelenideElement> parent = logicElement.getParent();
			if (parent == null) {
				this.realElement = Selenide.$(by);
			} else {
				ElementEntityLocator<SelenideElement> locator = parent.getLocator();
				SelenideElement pRealElement = locator.locateElement();
				if (pRealElement != null) {
					this.realElement = pRealElement.$(by);
				} else {
					//父类无法定位,直接使用绝对定位
					this.realElement = Selenide.$(by);
				}
			}
		}
		return this.realElement;
	}

	@Override
	public void timedLocate(long time, TimeUnit timeUnit) throws TimeoutException {
		final long expect = timeUnit.toMillis(time);
		final long start = System.currentTimeMillis();
		while ((System.currentTimeMillis() - start) < expect) {
			SelenideElement selenideElement = locateElement();
			if (selenideElement != null) {
				break;
			}
		}
		if (realElement == null) {
			throw new TimeoutException("locate element fail,can not find element:" + logicElement);
		}
	}
}
