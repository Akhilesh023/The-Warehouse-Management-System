package com.jsp.whms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.StorageType;
import com.jsp.whms.requestdto.StorageTypeRequest;
import com.jsp.whms.responsedto.StorageTypeResponse;
@Component
public class StorageTypeMapper {
	
	public StorageType mapToStorageType(StorageTypeRequest storageTypeRequest,StorageType storageType) {
		storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
		storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
		storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
		storageType.setCapacityInKg(storageTypeRequest.getCapacityInKg());
		return storageType;
	}
	
	public StorageTypeResponse mapToStorageTypeResponse(StorageType storageType) {
		return StorageTypeResponse.builder()
				.storageTypeId(storageType.getStorageTypeId())
				.lengthInMeters(storageType.getLengthInMeters())
				.breadthInMeters(storageType.getBreadthInMeters())
				.heightInMeters(storageType.getHeightInMeters())
				.capacityInKg(storageType.getCapacityInKg())
				.unitsAvailable(storageType.getUnitsAvailable())
				.build();
	} 

}
