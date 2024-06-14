package com.jsp.whms.serviceimpli;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Storage;
import com.jsp.whms.exception.StorageNotFoundByIdException;
import com.jsp.whms.exception.WarehouseNotFoundByIdException;
import com.jsp.whms.mapper.StorageMapper;
import com.jsp.whms.repository.StorageRepository;
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

	
	@Override
	public ResponseEntity<SimpleResponseStructure<String>> addStorage(StorageRequest storageRequest, int wareHouseId, int noOfStorageUnits) {
		
		return wareHouseRepository.findById(wareHouseId).map(warehouse -> {
			List<Storage> storages = new ArrayList<Storage>();
			
			int storageUnits = noOfStorageUnits;
			
			while(storageUnits>0) 
			{
				Storage storage = storageMapper.mapToStorage(storageRequest, new Storage());
				storage.setAvailableArea(storageRequest.getLengthInMeters()*storageRequest.getBreadthInMeters()*storageRequest.getHeightInMeters());
				storage.setMaxAdditionalWeightInKg(storageRequest.getCapacityInKg());
				storage.setWareHouse(warehouse);
				storages.add(storage);
				
				storageUnits--;
			}
			
			storageRepository.saveAll(storages);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new SimpleResponseStructure<String>()
							.setMessage("Storages Created")
							.setStatuscode(HttpStatus.CREATED.value()));			
		}).orElseThrow(() -> new  WarehouseNotFoundByIdException("WareHouse Not Found"));
	
		
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









 










