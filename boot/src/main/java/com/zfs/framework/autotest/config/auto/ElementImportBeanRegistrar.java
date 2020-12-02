package com.zfs.framework.autotest.config.auto;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.zfs.framework.autotest.ui.element.impl.BaseElement;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 元素自动扫描导入
 *
 * @author zfs
 * @date 2020/12/02 21:05
 */
public class ElementImportBeanRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
	private static final String DEFAULT_SCAN_PACKAGE = "com.zfs.framework.autotest.ui.element.impl";
	private ResourceLoader resourceLoader;

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes attributes = AnnotationAttributes
			.fromMap(importingClassMetadata.getAnnotationAttributes(ElementScan.class.getName()));
		assert attributes != null;
		String[] basePackages = attributes.getStringArray("basePackage");
		for (String basePackage : basePackages) {
			scanPackage(basePackage, registry);
		}
		scanPackage(DEFAULT_SCAN_PACKAGE, registry);
		registerElementProcessor(registry);
	}

	private void registerElementProcessor(BeanDefinitionRegistry registry) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(ElementPostProcessor.class);
		beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
		registry.registerBeanDefinition(ElementPostProcessor.class.getName(), beanDefinition);
	}

	private void scanPackage(String packagePath, BeanDefinitionRegistry registry) {
		String filePath = packagePath.replaceAll("\\.", "/");
		Resource resource = resourceLoader.getResource(filePath);
		File scanRoot;
		if (!resource.exists()) {
			throw new IllegalArgumentException("Element路径配置错误，请检查参数:" + filePath);
		}
		try {
			scanRoot = resource.getFile();
			List<Class<BaseElement>> result = new ArrayList<>();
			parse(packagePath, scanRoot, result, false);
			for (Class<BaseElement> baseElementClass : result) {
				register(baseElementClass, registry);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void register(Class<BaseElement> clazz, BeanDefinitionRegistry registry) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(clazz);
		beanDefinition.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
		registry.registerBeanDefinition(clazz.getName(), beanDefinition);
	}

	private void parse(String basePath, File file, List<Class<BaseElement>> result, boolean needAppend) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				basePath = needAppend ? basePath + "." + file.getName() : basePath;
				for (File f : files) {
					parse(basePath, f, result, true);
				}
			}
		} else {
			String clazzFile = file.getName();
			String name = clazzFile.split("\\.")[0];
			String className = basePath + "." + name;
			try {
				Class<BaseElement> aClass = (Class<BaseElement>)Class.forName(className);
				if (!Modifier.isAbstract(aClass.getModifiers()) && BaseElement.class.isAssignableFrom(aClass)) {
					result.add(aClass);
				}
			} catch (ClassNotFoundException ig) {
				ig.printStackTrace();
			}
		}

	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

}
