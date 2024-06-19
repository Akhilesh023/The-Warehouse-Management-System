package com.jsp.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
