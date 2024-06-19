package com.jsp.whms.serviceimpli;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Client;
import com.jsp.whms.entity.Inventory;
import com.jsp.whms.entity.Storage;
import com.jsp.whms.exception.ClientNotFoundByIdException;
import com.jsp.whms.exception.InventoryNotFoundByIdException;
import com.jsp.whms.exception.SpaceOrWeightNotAvailableException;
import com.jsp.whms.exception.StorageNotFoundByIdException;
import com.jsp.whms.mapper.InventoryMapper;
import com.jsp.whms.repository.ClientRepository;
import com.jsp.whms.repository.InventoryRepository;
import com.jsp.whms.repository.StorageRepository;
import com.jsp.whms.requestdto.InventoryRequest;
import com.jsp.whms.responsedto.AdminResponse;
import com.jsp.whms.responsedto.InventoryResponse;
import com.jsp.whms.service.InventoryService;
import com.jsp.whms.util.ResponseStructure;

@Service
public class InventoryServiceImpli implements InventoryService {
	
	@Autowired
	private InventoryMapper inventoryMapper;
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest, int storageId,int clientId) {
		
		Storage storage = storageRepository.findById(storageId)
		.orElseThrow(() -> new StorageNotFoundByIdException("No Storage found for the Id"));
		
		Client client = clientRepository.findById(clientId)
		.orElseThrow(() -> new ClientNotFoundByIdException("No client found for the id"));
		
		Inventory inventory = inventoryMapper.mapToInventory(inventoryRequest, new Inventory());
		inventory.setClient(client);
		inventory.setRestockedAt(LocalDate.now());
		storage.getInventories().add(inventory);
		
		double wholeWeight = inventory.getWeightInKg()*inventory.getQuantity();
		double area = inventory.getBreadthInMeters()*inventory.getHeightInMeters()*inventory.getLengthInMeters();
		
		storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg()-wholeWeight);
		storage.setAvailableArea(storage.getAvailableArea()-area);
		storage.setSellerId(inventory.getSellerId());
		
		
		inventory = inventoryRepository.save(inventory);
		storageRepository.save(storage);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setData(inventoryMapper.mapToInventoryResponse(inventory))
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory Created"));
		
		
	}
	
	

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findByInventoryId(int inventoryId) {
		
		return inventoryRepository.findById(inventoryId).map(inventory -> {
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<InventoryResponse>()
							.setData(inventoryMapper.mapToInventoryResponse(inventory))
							.setMessage("Inventory Found")
							.setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new InventoryNotFoundByIdException("The inventory Id does not exists"));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findAllInventory() {
		
		List<InventoryResponse> inventories = inventoryRepository.findAll().stream().map(inventory -> 
		inventoryMapper.mapToInventoryResponse(inventory))
		.toList();
		
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<InventoryResponse>>()
						.setData(inventories)
						.setMessage("inventories Found")
						.setStatus(HttpStatus.FOUND.value()));
		
		
	}



	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> updateClientById(InventoryRequest inventoryRequest, int productId) {
		
		return inventoryRepository.findById(productId).map(inventory -> {
			
			int oldQuantity = inventory.getQuantity();
		 double originalWeight = inventory.getWeightInKg() * inventory.getQuantity();
		 double originalArea = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();
		 
		 inventory = inventoryMapper.mapToInventory(inventoryRequest, inventory);
		 
		 if(oldQuantity != inventory.getQuantity())
		   inventory.setRestockedAt(LocalDate.now());

		   double newWeight = inventory.getWeightInKg() * inventory.getQuantity();
		   double newArea = inventory.getBreadthInMeters() * inventory.getHeightInMeters() * inventory.getLengthInMeters();

		
		inventory.getStorages().forEach(storage -> {
			
			if(storage.getAvailableArea() >0 && storage.getMaxAdditionalWeightInKg() >0)
			{
				storage.setMaxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg() + originalWeight - newWeight);
		        storage.setAvailableArea(storage.getAvailableArea() + originalArea - newArea);
		        
			}
			
			else
			{
				throw new SpaceOrWeightNotAvailableException("No Available Area or Capacity of Storage Full");
			}
	    });
		
		
		
		inventory = inventoryRepository.save(inventory);
	    inventory.getStorages().forEach(storageRepository::save);
	    
	    return ResponseEntity.status(HttpStatus.OK)
	            .body(new ResponseStructure<InventoryResponse>()
	                    .setData(inventoryMapper.mapToInventoryResponse(inventory))
	                    .setMessage("Inventory updated")
	                    .setStatus(HttpStatus.OK.value()));
		
		
	}).orElseThrow(() -> new InventoryNotFoundByIdException("Inventory not found"));
		
		
		
	}

	
}
