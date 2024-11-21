package com.springboot.jpademo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
	private Status status;
	private Integer errorCode;
	private String errorMessage;
	private Object result;
	
	public ResponseDTO(Status status, Object result) {
		//super();
		this.status = status;
		this.result = result;
	}
	
	public static enum Status{
		SUCCESS,FAIL
	}


}
