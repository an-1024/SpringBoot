package com.azh.customelementparse.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class CustomElementParser extends AbstractSingleBeanDefinitionParser {

	// 获取属性类
	@Override
	protected Class<?> getBeanClass(Element element) {
		return CustomElementParser.class;
	}

	// 获取属性值
	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		String name = element.getAttribute("name");
		if (StringUtils.hasText(name)) {
			builder.addPropertyReference("name", name);
		}

		String email = element.getAttribute("email");
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}

		String userPhone = element.getAttribute("userPhone");
		if (StringUtils.hasText(userPhone)) {
			builder.addPropertyValue("userPhone", userPhone);
		}
	}
}
