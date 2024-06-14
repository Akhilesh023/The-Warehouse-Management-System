package com.jsp.whms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Address;
import com.jsp.whms.entity.WareHouse;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	public List<Address> findByCity(String city);

}
