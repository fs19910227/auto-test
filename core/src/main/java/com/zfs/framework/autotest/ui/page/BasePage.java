package com.zfs.framework.autotest.ui.page;

import com.zfs.framework.autotest.ui.driver.UIDriver;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 页面元素
 *
 * @author zfs
 * @date 2020/11/28 23:24
 */
@Slf4j
public abstract class BasePage implements Page {
	@Setter
	@Getter
	private String address;

	@Setter
	protected UIDriver<?> driver;

	@Override
	public void access() {
		driver.access(address);
		while (!isIn()) {
			log.info("access page {},wait for enter", address);
		}
	}

	/**
	 * 是否进入页面
	 *
	 * @return t or f
	 */
	public abstract boolean isIn();
}
