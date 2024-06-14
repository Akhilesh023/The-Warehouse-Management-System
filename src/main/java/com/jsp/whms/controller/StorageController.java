package com.jsp.whms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.requestdto.StorageRequest;
import com.jsp.whms.responsedto.StorageResponse;
import com.jsp.whms.service.StorageService;
import com.jsp.whms.util.ResponseStructure;
import com.jsp.whms.util.SimpleResponseStructure;


@RestController
@RequestMapping("/api/v1")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	
	@PostMapping("/warehouses/{wareHouseId}/storages")
	public ResponseEntity<SimpleResponseStructure<String>> addStorage(@RequestBody StorageRequest storageRequest, @PathVariable int wareHouseId,@RequestParam("no_of_storage_units") int noOfStorageUnits){
		return storageService.addStorage(storageRequest,wareHouseId,noOfStorageUnits);
	}  
	
	@PutMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> updateResponse(@RequestBody StorageRequest storageRequest,@PathVariable int storageId){
		return storageService.updateResponse(storageRequest,storageId);
	}

}
