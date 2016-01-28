/**
 * @author CTRL 
 * Classname : MessageDto 
 * Version information : modified 
 * Date : 2016-01-25
 * Copyright notice : CTRL
 */
package com.ctrl.vo;


public class MessageDto {
	private Message message;
	private String name;
	
	public MessageDto() {
		// TODO Auto-generated constructor stub
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public MessageDto(Message message, String name) {
		super();
		this.message = message;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
