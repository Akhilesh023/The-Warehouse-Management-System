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

import com.jsp.whms.requestdto.InventoryRequest;
import com.jsp.whms.responsedto.InventoryResponse;
import com.jsp.whms.service.InventoryService;
import com.jsp.whms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/storages/{storageId}/clients/{clientId}/inventories")
	public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(@RequestBody InventoryRequest inventoryRequest,@PathVariable int storageId,@PathVariable int clientId){
		return inventoryService.addInventory(inventoryRequest,storageId,clientId);
	}
	
	@GetMapping("/inventories/{inventoryId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> findByInventoryId(@PathVariable int inventoryId){
		return inventoryService.findByInventoryId(inventoryId);
	}
	
	@GetMapping("/inventories")
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory(){
		return inventoryService.findAllInventory();
	}
	
	@PutMapping("/invetories/{productId}")
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateClientById(@RequestBody InventoryRequest inventoryRequest,@PathVariable int productId){
		return inventoryService.updateClientById(inventoryRequest,productId);
	}
	
	

}
