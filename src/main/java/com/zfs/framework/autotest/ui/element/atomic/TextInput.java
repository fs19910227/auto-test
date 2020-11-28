package com.zfs.framework.autotest.ui.element.atomic;

import com.zfs.framework.autotest.ui.element.ability.Readable;
import com.zfs.framework.autotest.ui.element.ability.Writeable;

/**
 * 文本输入框
 * 可以读写值
 *
 * @author zfs
 * @date 2020/11/27 23:01
 */
public class TextInput<Entity> extends AtomicElement<Entity> implements Writeable, Readable {

	@Override
	public String read() {
		return driver.getValue(this);
	}

	@Override
	public void write(String content) {
		driver.setText(this, content);
	}
}
