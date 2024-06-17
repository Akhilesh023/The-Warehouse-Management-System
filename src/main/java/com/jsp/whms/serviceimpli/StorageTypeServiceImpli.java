package com.jsp.whms.serviceimpli;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.StorageType;
import com.jsp.whms.exception.StorageTypeNotFoundByIdException;
import com.jsp.whms.mapper.StorageTypeMapper;
import com.jsp.whms.repository.StorageTypeRepository;
import com.jsp.whms.requestdto.StorageTypeRequest;
import com.jsp.whms.responsedto.StorageTypeResponse;
import com.jsp.whms.service.StorageTypeService;
import com.jsp.whms.util.ResponseStructure;

@Service
public class StorageTypeServiceImpli implements StorageTypeService{
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;

	
	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType( StorageTypeRequest storageTypeRequest) {
		
		StorageType storageType = storageTypeMapper.mapToStorageType(storageTypeRequest, new StorageType());
		
		storageType.setUnitsAvailable(0);
		
		storageType = storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<StorageTypeResponse>()
						.setData(storageTypeMapper.mapToStorageTypeResponse(storageType))
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Storage Type Created"));
	}


	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest, int  storageTypeId) {
		
		return storageTypeRepository.findById(storageTypeId).map(storageType -> {
			
			 storageType = storageTypeRepository.save(storageTypeMapper.mapToStorageType(storageTypeRequest, storageType));
			 
			 return ResponseEntity.status(HttpStatus.OK)
					 .body(new ResponseStructure<StorageTypeResponse>()
							 .setStatus(HttpStatus.OK.value())
							 .setMessage("StorageType UPdated")
							 .setData(storageTypeMapper.mapToStorageTypeResponse(storageType)));
			 
		}).orElseThrow( () -> new StorageTypeNotFoundByIdException("Failed to update the StorageType"));
	}


	@Override
	public ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllWareHouses() {
		
		List<StorageTypeResponse> StorageTypes = storageTypeRepository.findAll().stream().map( storageType -> 
		storageTypeMapper.mapToStorageTypeResponse(storageType)).toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<StorageTypeResponse>>()
						.setData(StorageTypes)
						.setStatus(HttpStatus.FOUND.value())
						.setMessage("Storage Types have been found"));
		
		
	}
	
	

}
