package com.jsp.whms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.whms.entity.Client;


public interface ClientRepository  extends JpaRepository<Client, Integer> {

}
