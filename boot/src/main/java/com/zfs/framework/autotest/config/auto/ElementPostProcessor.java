package com.zfs.framework.autotest.config.auto;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.zfs.framework.autotest.annotation.Element;
import com.zfs.framework.autotest.ui.driver.UIDriver;
import com.zfs.framework.autotest.ui.element.impl.BaseElement;
import com.zfs.framework.autotest.ui.element.locate.ElementEntityLocator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/**
 * 对于Element注解的后置处理
 * 主要用于自动注入,初始化Element
 *
 * @author zfs
 * @date 2020/12/02 22:34
 */
public class ElementPostProcessor
	implements BeanPostProcessor, ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

		ReflectionUtils.doWithLocalFields(bean.getClass(), field -> {
			MergedAnnotation<?> ann = findAnnotation(field);
			if (ann != null) {
				if (!Modifier.isStatic(field.getModifiers())) {
					inject(bean, field, ann);
				}
			}
		});

		return bean;
	}

	private void inject(Object bean, Field field, MergedAnnotation<?> ann) {
		Class<?> type = field.getType();
		if (BaseElement.class.isAssignableFrom(type)) {
			String name = ann.getString("name");
			String express = ann.getString("express");
			Class<BaseElement> trueType = (Class<BaseElement>)type;
			BaseElement target = context.getBean(trueType);
			//处理基本属性
			target.setName(name);
			//处理父子关系
			if (bean instanceof BaseElement) {
				BaseElement parent = (BaseElement)bean;
				target.setParent(parent);
				if (parent.getChildren() == null) {
					parent.setChildren(new ArrayList<>());
				}
				parent.getChildren().add(target);
			}
			//处理locator
			if (StringUtils.hasText(express)) {
				ElementEntityLocator locator = context.getBean(ElementEntityLocator.class);
				locator.bind(target, express);
				target.setLocator(locator);
			}
			//set driver
			UIDriver driver = context.getBean(UIDriver.class);
			target.setDriver(driver);
			field.setAccessible(true);
			try {
				field.set(bean, target);
			} catch (IllegalAccessException ignore) {
			}
		}

	}

	private MergedAnnotation<?> findAnnotation(AccessibleObject ao) {
		MergedAnnotations annotations = MergedAnnotations.from(ao);
		MergedAnnotation<?> annotation = annotations.get(Element.class);
		if (annotation.isPresent()) {
			return annotation;
		}
		return null;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
}
