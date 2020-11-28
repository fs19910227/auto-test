package com.zfs.framework;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@SpringBootTest
class AutoUiTestApplicationTests {

	@BeforeAll
	static void init() {
		//浏览器种类
		System.setProperty("selenide.browser", "chrome");
		//国内，使用淘宝镜像
		System.setProperty("wdm.useTaobaoMirror", "true");
		//由于淘宝镜像没有win64，强制指定32架构
		System.setProperty("wdm.architecture", "32");
		//浏览器版本
		System.setProperty("wdm.chromeDriverVersion", "86");
	}

	@Test
	void name() {
		open("https://www.baidu.com/");


	}
}
