/**
 * @author CTRL 
 * Classname : Message 
 * Version information : modified 
 * Date : 2016-01-25 
 * Copyright notice : CTRL
 */

package com.ctrl.vo;

import java.util.Date;

public class Message {

	private Integer id;
	private String content;
	private Integer staff_id;
	private Date created_dt;

	public Date getCreated_dt() {
		return created_dt;
	}

	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(Integer staff_id) {
		this.staff_id = staff_id;
	}

	public Message(String content, Integer staff_id, Date created_td) {
		super();
		this.content = content;
		this.staff_id = staff_id;
		this.created_dt = created_td;
	}

	public Message() {
		super();
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", staff_id="
				+ staff_id + ", created_dt=" + created_dt + "]";
	}

}
