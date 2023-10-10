package com.example.demo.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptions {

	
	@ExceptionHandler(value= ElementNotExist.class)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public @ResponseBody ReponseError 	abc(ElementNotExist ex)
	{
		return new ReponseError(ex.getMessage(),LocalDate.now(),HttpStatus.NO_CONTENT);
	}
	
	
	@ExceptionHandler(value= AlreadyExitlement.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public @ResponseBody ReponseError 	abc2(AlreadyExitlement ex)
	{
		return new ReponseError(ex.getMessage(),LocalDate.now(),HttpStatus.CONFLICT);
	}
	
}
