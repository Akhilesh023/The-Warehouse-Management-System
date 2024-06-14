package com.jsp.whms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StorageNotFoundByIdException extends RuntimeException{
	
	private String message;

}
