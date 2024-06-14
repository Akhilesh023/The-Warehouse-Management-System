package com.jsp.whms.requestdto;

import java.util.List;

import com.jsp.whms.enums.MaterialTypes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageRequest {
	
	private String blockName;
	private String section;
	private double capacityInKg;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private List<MaterialTypes> materialTypes;

}
