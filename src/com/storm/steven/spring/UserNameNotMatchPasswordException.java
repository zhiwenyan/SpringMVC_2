package com.storm.steven.spring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="�û��������벻ƥ��!")  //����
public class UserNameNotMatchPasswordException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
