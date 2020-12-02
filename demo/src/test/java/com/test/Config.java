package com.test;

import com.zfs.framework.autotest.config.auto.ElementScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zfs
 * @date 2020/11/29 00:02
 */
@ComponentScan({"com.test"})
@ElementScan(basePackage = "com.test")
@EnableAutoConfiguration
public class Config {

}
