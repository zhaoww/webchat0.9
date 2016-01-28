/**
 * @author CTRL 
 * Classname : FuzzyQueryUtils 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */
package com.ctrl.utils;

/**
 * @author CTRL 根据传入的参数，为其变值，以方便sql语句的拼写
 */
public class FuzzyQueryUtils {

	private String name;
	private String code;

	public String getName() {
		if (name != null) {
			name = "%" + name + "%";
		} else {
			name = "%%";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		if (code != null) {
			code = "%" + code + "%";
		} else {
			code = "%%";
		}
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public FuzzyQueryUtils(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

}
