/**
 * @author CTRL 
 * Classname : User 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.vo;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;//姓名
	private String code;//工号
	private String pwd;//密码
	private String state;//可用状态
	private String flag;//禁言状态

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public User(Integer id, String name, String code, String pwd, String state,
			String flag) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.pwd = pwd;
		this.state = state;
		this.flag = flag;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", code=" + code
				+ ", pwd=" + pwd + ", state=" + state + ", flag=" + flag + "]";
	}

}
