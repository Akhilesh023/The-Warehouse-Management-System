package com.jsp.whms.requestdto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.whms.entity.Inventory;
import com.jsp.whms.entity.Storage;
import com.jsp.whms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
	
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	private int quantity;
	private List<MaterialTypes> materialTypes;
	//private LocalDate restockedAt;
	private int sellerId;

}
