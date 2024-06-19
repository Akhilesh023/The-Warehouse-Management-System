package com.jsp.whms.mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.Inventory;
import com.jsp.whms.requestdto.InventoryRequest;
import com.jsp.whms.responsedto.InventoryResponse;

@Component
public class InventoryMapper {
	
	public Inventory mapToInventory(InventoryRequest inventoryRequest,Inventory inventory) {
		
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setQuantity(inventoryRequest.getQuantity());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setSellerId(inventoryRequest.getSellerId());
		
		return inventory;
	}
	
	public InventoryResponse mapToInventoryResponse(Inventory inventory) {
		
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.lengthInMeters(inventory.getLengthInMeters())
				.breadthInMeters(inventory.getBreadthInMeters())
				.heightInMeters(inventory.getHeightInMeters())
				.weightInKg(inventory.getWeightInKg())
				.quantity(inventory.getQuantity())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.build();
	}

}
