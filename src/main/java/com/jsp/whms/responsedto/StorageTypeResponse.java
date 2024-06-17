package com.jsp.whms.responsedto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageTypeResponse {
	
	private int storageTypeId;
	private double capacityInKg;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private int unitsAvailable;

}
