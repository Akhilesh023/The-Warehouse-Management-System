package com.jsp.whms.entity;

import java.time.LocalDate;
import java.util.List;

import com.jsp.whms.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToMany(mappedBy = "inventories")
	private List<Storage> storages;
	
	@ManyToOne
	private Client client;
	

}
