package com.jsp.whms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseNotFoundInCityException extends RuntimeException {
	
	private String message;

}
