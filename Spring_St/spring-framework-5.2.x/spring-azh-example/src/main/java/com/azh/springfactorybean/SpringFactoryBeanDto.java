package com.azh.springfactorybean;

import org.springframework.beans.factory.FactoryBean;

public class SpringFactoryBeanDto implements FactoryBean<FactoryBeanDto> {
	@Override
	public FactoryBeanDto getObject() throws Exception {
		return new FactoryBeanDto();
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}
}
