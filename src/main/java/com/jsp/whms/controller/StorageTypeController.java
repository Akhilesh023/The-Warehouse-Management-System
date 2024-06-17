package com.jsp.whms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.requestdto.StorageTypeRequest;
import com.jsp.whms.responsedto.StorageTypeResponse;
import com.jsp.whms.service.StorageTypeService;
import com.jsp.whms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class StorageTypeController {
	
	@Autowired
	private StorageTypeService storageTypeService;
	
	@PostMapping("/storageTypes")
	private ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType(@RequestBody StorageTypeRequest storageTypeRequest){
		return storageTypeService.addStorageType(storageTypeRequest);
		
	}
	
	@PutMapping("/storageTypes/{storageTypeId}")
	private ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(@RequestBody StorageTypeRequest storageTypeRequest,@PathVariable int storageTypeId){
		return storageTypeService.updateStorageType(storageTypeRequest,storageTypeId);
	}
	
	@GetMapping("/storageTypes")
	private ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllWareHouses(){
		return storageTypeService.findAllWareHouses();
		
	}

}
