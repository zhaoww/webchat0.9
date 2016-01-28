/**
 * @author CTRL 
 * Classname : MessageSixin 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.vo;

import java.util.Date;

public class MessageSixin {
	private int id;
	private String fromCode;
	private String toCode;
	private String content;
	private Date created_dt;

	public MessageSixin() {
		// TODO Auto-generated constructor stub
	}

	public MessageSixin(String fromCode, String toCode, String content,
			Date created_dt) {
		super();
		this.fromCode = fromCode;
		this.toCode = toCode;
		this.content = content;
		this.created_dt = created_dt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromCode() {
		return fromCode;
	}

	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}

	public String getToCode() {
		return toCode;
	}

	public void setToCode(String toCode) {
		this.toCode = toCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}

	@Override
	public String toString() {
		return "MessageSixin [id=" + id + ", fromCode=" + fromCode
				+ ", toCode=" + toCode + ", content=" + content
				+ ", created_dt=" + created_dt + "]";
	}
}
