package com.zfs.framework.autotest.config;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ui 配置属性
 *
 * @author zfs
 * @date 2020/11/28 23:48
 */
@ConfigurationProperties(prefix = UIConfigProperties.PREFIX)
@Data
public class UIConfigProperties implements InitializingBean {
	static final String PREFIX = "fs.autotest.ui";

	private UIType type = UIType.WEB;

	private WebUIProperties web;

	public static enum UIType {
		WEB
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Data
	public static class WebUIProperties {
		/**
		 * 浏览器类型
		 */
		private String browser = "chrome";
		/**
		 * 浏览器版本
		 */
		private String version;
		/**
		 * 使用淘宝镜像
		 */
		private Boolean useTaobao = true;

	}

}
