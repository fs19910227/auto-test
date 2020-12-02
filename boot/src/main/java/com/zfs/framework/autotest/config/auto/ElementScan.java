package com.zfs.framework.autotest.config.auto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * 元素扫描注解
 * 可以类比mybatis 的mapper scan
 *
 * @author zfs
 * @date 2020/12/02 20:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(ElementImportBeanRegistrar.class)
public @interface ElementScan {
	String[] basePackage() default {};
}
