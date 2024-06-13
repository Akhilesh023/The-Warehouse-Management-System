package com.jsp.whms.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
	
	private String addressLine;
	private String city;
	private String state;
	private String country;
	private int pincode;
	private String longitutde;
	private String latitude;

}
