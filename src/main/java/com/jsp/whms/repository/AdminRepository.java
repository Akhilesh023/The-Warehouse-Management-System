package com.jsp.whms.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.enums.AdminType;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	//Optional<Admin> findByAdminType(AdminType adminType);
	
	public boolean existsByAdminType(AdminType adminType);

	public Optional<Admin> findByEmail(String username);
	

}
