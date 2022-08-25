package com.azh.customelementparse.parser;

import com.azh.customelementparse.dto.CustomElementAzh;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class CustomElementParser extends AbstractSingleBeanDefinitionParser {

	// 获取属性类
	@Override
	protected Class<?> getBeanClass(Element element) {
		return CustomElementAzh.class;
	}

	// 获取属性值
	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String name = element.getAttribute("user-name");
		if (StringUtils.hasText(name)) {
			builder.addPropertyValue("userName", name);
		}

		String email = element.getAttribute("email");
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}

		String userPhone = element.getAttribute("user-phone");
		if (StringUtils.hasText(userPhone)) {
			builder.addPropertyValue("userPhone", userPhone);
		}
	}
}
