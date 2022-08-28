package com.azh.custombeanproperties;

import java.beans.PropertyEditorSupport;

public class CustomBeanPropertyEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] str = text.split("_");
		CustomBeanAddress customBeanAddress = new CustomBeanAddress();
		customBeanAddress.setProvince(str[0]);
		customBeanAddress.setCity(str[1]);
		customBeanAddress.setTown(str[2]);
		setValue(customBeanAddress);
	}
}
