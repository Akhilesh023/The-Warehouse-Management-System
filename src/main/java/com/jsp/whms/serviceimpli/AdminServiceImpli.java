package com.jsp.whms.serviceimpli;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.enums.AdminType;
import com.jsp.whms.enums.Privilege;
import com.jsp.whms.exception.IllegalOperationException;
import com.jsp.whms.mapper.AdminMapper;
import com.jsp.whms.repository.AdminRepository;
import com.jsp.whms.requestdto.AdminRequest;
import com.jsp.whms.responsedto.AdminResponse;
import com.jsp.whms.service.AdminService;
import com.jsp.whms.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpli implements AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin(AdminRequest adminRequest) {
		
//		Optional<Admin> existingSuperAdmin = adminRepository.findByAdminType(AdminType.SUPER_ADMIN);
//		if(existingSuperAdmin.isPresent()) {
//			return ResponseEntity.status(HttpStatus.CREATED)
//					.body(new ResponseStructure<AdminResponse>()
//							.setStatus(HttpStatus.CONFLICT.value())
//							.setMessage("Super Admin Already Exists !!!")
//							.setData(null));
//		}
		
		
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN)) {
			throw new IllegalOperationException("Illegal Operation occurred Super Admin Already Exists");
		}
			
		
//		Admin admin = new Admin();

//		List<Privilege> privs = new ArrayList<Privilege>();
//		privs.add(Privilege.CREATE_ADMIN);
		
		  
		
		
		
		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
		
		admin.setAdminType(AdminType.SUPER_ADMIN);
		
		admin = adminRepository.save(admin);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<AdminResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Super Admin Created!!!")
						.setData(adminMapper.mapToAdminResponse(admin)));
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
