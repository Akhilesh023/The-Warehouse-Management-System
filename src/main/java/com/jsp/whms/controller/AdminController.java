package com.jsp.whms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.requestdto.AdminRequest;
import com.jsp.whms.responsedto.AdminResponse;
import com.jsp.whms.service.AdminService;
import com.jsp.whms.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
		return adminService.addSuperAdmin(adminRequest);
	}
	
	@PostMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@RequestBody AdminRequest adminRequest){
		return adminService.addAdmin(adminRequest);
	}
	
	

}
