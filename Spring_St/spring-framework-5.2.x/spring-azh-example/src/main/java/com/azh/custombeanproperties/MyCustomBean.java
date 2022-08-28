package com.azh.custombeanproperties;

public class MyCustomBean {
	private String name;

	private CustomBeanAddress customBeanAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomBeanAddress getCustomBeanAddress() {
		return customBeanAddress;
	}

	public void setCustomBeanAddress(CustomBeanAddress customBeanAddress) {
		this.customBeanAddress = customBeanAddress;
	}

	@Override
	public String toString() {
		return "MyCustomBean{" +
				"name='" + name + '\'' +
				", customBeanAddress=" + customBeanAddress +
				'}';
	}
}
