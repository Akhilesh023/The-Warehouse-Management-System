package com.jsp.whms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Client;


public interface ClientRepository  extends JpaRepository<Client, Integer> {
	
	public Optional<Client> findByEmail(String username);

}
