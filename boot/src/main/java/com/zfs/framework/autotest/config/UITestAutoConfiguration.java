package com.zfs.framework.autotest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Auto Config
 *
 * @author zfs
 * @date 2020/12/02 20:47
 */
@Configuration
@Import(UIAutoConfig.class)
public class UITestAutoConfiguration {
}
