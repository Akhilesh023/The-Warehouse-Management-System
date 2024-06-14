package com.jsp.whms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer> {
	
	

}
