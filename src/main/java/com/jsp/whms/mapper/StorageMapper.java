package com.jsp.whms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.Storage;
import com.jsp.whms.requestdto.StorageRequest;
import com.jsp.whms.responsedto.StorageResponse;

@Component
public class StorageMapper {
	
	public Storage mapToStorage(StorageRequest storageRequest,Storage storage) {
		
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setCapacityInKg(storageRequest.getCapacityInKg());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		
		return storage;
		
	}
	
	public StorageResponse mapToStorageResponse(Storage storage) {
		
		return StorageResponse.builder()
				.blockName(storage.getBlockName())
				.section(storage.getSection())
				.capacityInKg(storage.getCapacityInKg())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}

}
