package com.jsp.whms.requestdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageTypeRequest {
	
	private double capacityInKg;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;

}
