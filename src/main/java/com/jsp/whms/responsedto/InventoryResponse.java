package com.jsp.whms.responsedto;

import java.time.LocalDate;
import java.util.List;

import com.jsp.whms.enums.AdminType;
import com.jsp.whms.enums.MaterialTypes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {
	
	private int productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	private int quantity;
	private List<MaterialTypes> materialTypes;
	private LocalDate restockedAt;
	private int sellerId;

}
