package com.jsp.whms.serviceimpli;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Storage;
import com.jsp.whms.entity.StorageType;
import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.exception.StorageNotFoundByIdException;
import com.jsp.whms.exception.StorageTypeNotFoundByIdException;
import com.jsp.whms.exception.WarehouseNotFoundByIdException;
import com.jsp.whms.mapper.StorageMapper;
import com.jsp.whms.repository.StorageRepository;
import com.jsp.whms.repository.StorageTypeRepository;
import com.jsp.whms.repository.WareHouseRepository;
import com.jsp.whms.requestdto.StorageRequest;
import com.jsp.whms.responsedto.StorageResponse;
import com.jsp.whms.service.StorageService;
import com.jsp.whms.util.ResponseStructure;
import com.jsp.whms.util.SimpleResponseStructure;

@Service
public class StorageServiceImpli implements StorageService {
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private StorageMapper storageMapper;

	@Autowired
	private StorageTypeRepository storageTypeRepository;
	
	@Override
	public ResponseEntity<SimpleResponseStructure<String>> addStorage(StorageRequest storageRequest, int wareHouseId, int noOfStorageUnits, int storageTypeId) {
		
		WareHouse wareHouse = wareHouseRepository.findById(wareHouseId)
				.orElseThrow(() -> new WarehouseNotFoundByIdException("No warehouse exists for the Id"));
		
		StorageType storageType = storageTypeRepository.findById(storageTypeId)
		.orElseThrow(() -> new StorageTypeNotFoundByIdException("No storage Type Found"));
		
		List<Storage> storages = new ArrayList<Storage>();
		
		int count=0;
		
		while(noOfStorageUnits>0) {
			
			Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());
			
			storage.setWareHouse(wareHouse);
			storage.setStorageType(storageType);
			
			storage.setMaxAdditionalWeightInKg(storageType.getCapacityInKg());
			storageType.setUnitsAvailable(storageType.getUnitsAvailable() + noOfStorageUnits);
			storages.add(storage);
			count++;
			noOfStorageUnits--;
		}
		
		storageRepository.saveAll(storages);
		wareHouseRepository.save(wareHouse);
		storageTypeRepository.save(storageType);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new SimpleResponseStructure<String>()
						.setStatuscode(HttpStatus.CREATED.value())
						.setMessage(""+count+"Storages Created"));
		
	}


	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateResponse(StorageRequest storageRequest, int storageId) {
		
		return storageRepository.findById(storageId).map(storage -> {
			 storage = storageMapper.mapToStorage(storageRequest, storage);
			 
			 storageRepository.save(storage);
			 
			return  ResponseEntity.status(HttpStatus.OK)
			 .body(new ResponseStructure<StorageResponse>()
					 .setData(storageMapper.mapToStorageResponse(storage))
					 .setMessage("Storage is Updated")
					 .setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new StorageNotFoundByIdException("Storage not found for the Id"));
		
		
		
	}

}









 










