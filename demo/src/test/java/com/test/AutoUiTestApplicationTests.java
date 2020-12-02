package com.test;

import com.zfs.framework.autotest.annotation.Element;
import com.zfs.framework.autotest.ui.driver.WebUIDriver;
import com.zfs.framework.autotest.ui.element.impl.atomic.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@SpringBootTest(classes = {Config.class})
class AutoUiTestApplicationTests implements ApplicationContextAware {

	@Autowired
	private WebUIDriver webUIDriver;

	private ApplicationContext context;

	@Element(name = "ok", express = "/id:id")
	private Button button;

	@BeforeAll
	static void init() {
	}

	@Test
	void name2() {
		System.out.println(button);
		System.out.println();
	}

	@Test
	void name() throws InterruptedException {
		//WebDriverRunner.addListener(new AbstractWebDriverEventListener() {
		//	@Override
		//	public void beforeClickOn(WebElement element, WebDriver driver) {
		//		System.out.println("before click");
		//	}
		//
		//	@Override
		//	public void afterClickOn(WebElement element, WebDriver driver) {
		//		System.out.println("after click");
		//
		//	}
		//});
		webUIDriver.access("https://www.baidu.com/");
		Thread.sleep(1000000000);

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
