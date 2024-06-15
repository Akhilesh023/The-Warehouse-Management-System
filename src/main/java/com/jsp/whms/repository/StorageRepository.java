package com.jsp.whms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Integer> {
	
//	public List<Storage> findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(Double capacityInKg,Double lengthInMeters,
//			Double breadthInMeters,Double heightInMeters);

}
