package com.jsp.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
