package com.jsp.whms.entity;

import java.util.List;

import com.jsp.whms.enums.MaterialTypes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storageId;
	private String blockName;
	private String section;
	private double capacityInKg;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private List<MaterialTypes> materialTypes;
	private double maxAdditionalWeightInKg;
	private double availableArea;
	
	@ManyToOne
	private WareHouse wareHouse;

}
