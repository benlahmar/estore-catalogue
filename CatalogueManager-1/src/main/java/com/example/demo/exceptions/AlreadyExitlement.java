package com.example.demo.exceptions;

public class AlreadyExitlement extends RuntimeException{

	String msg;
	public AlreadyExitlement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlreadyExitlement(String message) {
		super(message);
		msg=message;
	}

}
