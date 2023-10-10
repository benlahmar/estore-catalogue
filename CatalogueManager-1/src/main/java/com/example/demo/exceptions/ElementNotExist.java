package com.example.demo.exceptions;

public class ElementNotExist extends RuntimeException{

	String msg;
	
	public ElementNotExist() {
		super();
	}

	public ElementNotExist(String message) {
		super(message);
		this.msg=message;
	}

	
}
