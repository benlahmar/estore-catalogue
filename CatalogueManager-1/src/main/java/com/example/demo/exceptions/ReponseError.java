package com.example.demo.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

public class ReponseError {

	String msg;
	LocalDate date;
	HttpStatus code;
	
	
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public ReponseError(String msg, LocalDate date, HttpStatus code) {
		super();
		this.msg = msg;
		this.date = date;
		this.code = code;
	}
	public ReponseError(String msg, LocalDate date) {
		super();
		this.msg = msg;
		this.date = date;
	}
	public ReponseError() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
}
