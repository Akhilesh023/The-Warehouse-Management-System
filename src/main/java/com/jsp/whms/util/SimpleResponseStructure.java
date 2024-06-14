package com.jsp.whms.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleResponseStructure<T> {
	
	private int statuscode;
	private String message;
	
	public SimpleResponseStructure<T> setStatuscode(int statuscode) {
		this.statuscode = statuscode;
		return this;
	}
	public SimpleResponseStructure<T> setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
	
	

}
