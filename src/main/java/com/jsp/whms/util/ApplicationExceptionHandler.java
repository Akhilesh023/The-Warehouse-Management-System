package com.jsp.whms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.whms.exception.IllegalOperationException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
		
		List<ObjectError> errors = e.getAllErrors();
		
		Map<String, String> allErrors = new HashMap<String, String>();
		
		errors.forEach(error -> {
			
			FieldError fieldError = (FieldError) error;
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			allErrors.put(field, message);
		});
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseStructure<Map<String, String>>()
						.setStatus(HttpStatus.BAD_REQUEST.value())
						.setMessage("Invalid output")
						.setData(allErrors));
	}
	@ExceptionHandler
	public ResponseEntity<ErrorStructure> handleIllegalOperationException(IllegalOperationException e){
		ErrorStructure es = new ErrorStructure();
		es.setStatus(HttpStatus.FORBIDDEN.value());
		es.setMessage(e.getMessage());
		es.setRootcause("Illegal Operations occured Super Admin Already Exists");
		
		return new ResponseEntity<ErrorStructure>(es,HttpStatus.FORBIDDEN);
	}
	
	public ResponseEntity<ErrorStructure> handleUsernameNotFound(UsernameNotFoundException e){
		ErrorStructure es = new ErrorStructure();
		
		es.setStatus(HttpStatus.NOT_FOUND.value());
		es.setMessage(e.getMessage());
		es.setRootcause("The admin username does not exist");
		
		return new ResponseEntity<ErrorStructure>(es,HttpStatus.NOT_FOUND);
	}

}
