package com.am.generic;

public class SettingInfo {
	
	private boolean isDynamic;
	public String name;
	public boolean isDynamic() {
		return isDynamic;
	}
	public void setDynamic(boolean isDynamic) {
		this.isDynamic = isDynamic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String value;

}
