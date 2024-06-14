package com.jsp.whms.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WareHouse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wareHouseId;
	private String name;
	private int totalCapacityInKg;
	
	@OneToOne
	private Admin admin;
	
	@OneToMany(mappedBy = "wareHouse")
	private List<Storage> storages;
	
	
	


}
