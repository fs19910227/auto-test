package com.zfs.framework.autotest.config;

import com.zfs.framework.autotest.config.UIConfigProperties.WebUIProperties;
import com.zfs.framework.autotest.ui.driver.UIDriver;
import com.zfs.framework.autotest.ui.driver.WebUIDriver;
import com.zfs.framework.autotest.ui.element.locate.ElementEntityLocator;
import com.zfs.framework.autotest.ui.element.locate.WebUIElementLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * ui 自动配置
 * 依赖于Spring 自动化配置
 *
 * @author zfs
 * @date 2020/11/28 23:48
 */
@Configuration
@EnableConfigurationProperties(UIConfigProperties.class)
public class UIAutoConfig {
	@Autowired
	private UIConfigProperties configProperties;

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = UIConfigProperties.PREFIX, name = "type", havingValue = "WEB")
	public UIDriver<?> driver() {
		WebUIProperties webConfig = configProperties.getWeb();
		//浏览器种类
		System.setProperty("selenide.browser", webConfig.getBrowser());
		//国内，使用淘宝镜像
		System.setProperty("wdm.useTaobaoMirror", webConfig.getUseTaobao().toString());
		//由于淘宝镜像没有win64，强制指定32架构
		//todo 默认 win系统使用,后续处理系统版本
		System.setProperty("wdm.architecture", "32");
		//浏览器版本
		System.setProperty("wdm.chromeDriverVersion", webConfig.getVersion());
		return new WebUIDriver();
	}

	@Bean
	@Scope(SCOPE_PROTOTYPE)
	@ConditionalOnProperty(prefix = UIConfigProperties.PREFIX, name = "type", havingValue = "WEB")
	public ElementEntityLocator<?> locator() {
		return new WebUIElementLocator();
	}
}
