package com.jsp.whms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.requestdto.InventoryRequest;
import com.jsp.whms.responsedto.InventoryResponse;
import com.jsp.whms.util.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest, int storageId, int clientId);

	ResponseEntity<ResponseStructure<InventoryResponse>> findByInventoryId(int inventoryId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory();

	ResponseEntity<ResponseStructure<InventoryResponse>> updateClientById(InventoryRequest inventoryRequest, int productId);

}
