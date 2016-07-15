package com.am.generic;

public class SettingType {
	
	@Setting
	private String attr1 = "test";

	@Setting(isDynamic = false,name="hello",value = "20")
	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

}
