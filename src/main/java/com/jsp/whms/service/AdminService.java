package com.jsp.whms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.requestdto.AdminRequest;
import com.jsp.whms.responsedto.AdminResponse;
import com.jsp.whms.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {

	ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin( AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest,int wareHouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest, int adminId);

	ResponseEntity<ResponseStructure<AdminResponse>> findByAdminId(int adminId);

	ResponseEntity<ResponseStructure<List<AdminResponse>>> findAllAdmin();

	

}
