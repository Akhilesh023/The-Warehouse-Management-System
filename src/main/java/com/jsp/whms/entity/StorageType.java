package com.jsp.whms.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageType {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int storageTypeId;
	private double capacityInKg;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private int unitsAvailable;

}
