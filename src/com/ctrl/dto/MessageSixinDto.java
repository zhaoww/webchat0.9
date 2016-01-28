/**
 * @author CTRL 
 * Classname : MessageSixinDto 
 * Version information : modified 
 * Date : 2016-01-25
 * Copyright notice : CTRL
 */
package com.ctrl.dto;

import java.util.Date;

public class MessageSixinDto {
	private String fromName;
	private String toName;
	private Date created_dt;
	private String content;

	public MessageSixinDto() {
		// TODO Auto-generated constructor stub
	}

	public MessageSixinDto(String fromName, String toName, java.util.Date date,
			String content) {
		super();
		this.fromName = fromName;
		this.toName = toName;
		this.created_dt = date;
		this.content = content;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public java.util.Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(java.util.Date created_dt) {
		this.created_dt = created_dt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
