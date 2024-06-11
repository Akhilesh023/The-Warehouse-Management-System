package com.jsp.whms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/warehouses/{wareHouseId}/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(@RequestBody AdminRequest adminRequest,@PathVariable int wareHouseId){
		return adminService.addAdmin(adminRequest,wareHouseId);
	}
	
	
	@PutMapping("/admins")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest){
		return adminService.updateAdmin(adminRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADMIN')")
	@PutMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(@RequestBody AdminRequest adminRequest, @PathVariable int adminId){
		return adminService.updateAdminBySuperAdmin(adminRequest,adminId);
	}
	
	@GetMapping("/admins/{adminId}")
	public ResponseEntity<ResponseStructure<AdminResponse>> findByAdminId(@PathVariable int adminId){
		return adminService.findByAdminId(adminId);
	}
	
	@GetMapping("/admins")
	public ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmin(){
		return adminService.findAllAdmin();
	}
	
	
	
	
	
	
	
	
	
	
	

}
