package com.azh.customelementparse.namespacehandler;

import com.azh.customelementparse.parser.CustomElementParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CustomElementHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("azh", new CustomElementParser());
	}
}
