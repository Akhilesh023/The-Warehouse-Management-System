package com.jsp.whms.serviceimpli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.enums.AdminType;
import com.jsp.whms.enums.Privilege;
import com.jsp.whms.exception.AdminNotFoundByIdException;
import com.jsp.whms.exception.IllegalOperationException;
import com.jsp.whms.exception.WarehouseNotFoundByIdException;
import com.jsp.whms.mapper.AdminMapper;
import com.jsp.whms.repository.AdminRepository;
import com.jsp.whms.repository.WareHouseRepository;
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

	@Autowired
	private WareHouseRepository wareHouseRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

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
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest,int wareHouseId) {
//		Optional<WareHouse> optional = wareHouseRepository.findById(wareHouseId);
//		if(optional.isEmpty()) {
//			throw new WarehouseNotFoundByIdException("WareHouse ID not found");
//		}
//		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());
//		admin.setAdminType(AdminType.ADMIN);
//		admin = adminRepository.save(admin);
//		WareHouse wareHouse = optional.get();
//		wareHouse.setAdmin(admin);
//		wareHouseRepository.save(wareHouse);
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(new ResponseStructure<AdminResponse>()
//						.setStatus(HttpStatus.CREATED.value())
//						.setMessage("Admin Created")
//						.setData(adminMapper.mapToAdminResponse(admin)));
		
		return wareHouseRepository.findById(wareHouseId).map(warehouse -> {
		Admin admin = adminMapper.mapToAdmin(adminRequest, new Admin());	
			admin.setAdminType(AdminType.ADMIN);
			
			admin = adminRepository.save(admin);
			
			warehouse.setAdmin(admin);
			wareHouseRepository.save(warehouse);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.CREATED.value())
							.setMessage("Admin Created")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("No WareHouse Id found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
		
		String email = SecurityContextHolder.getContext().getAuthentication().getName(); // returns the email in the session
	
		return adminRepository.findByEmail(email).map(admin -> {
			
			admin = adminRepository.save(adminMapper.mapToAdmin(adminRequest, admin));
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin Updated")
							.setData(adminMapper.mapToAdminResponse(admin)));
		}).orElseThrow(() -> new AdminNotFoundByIdException("Failed to update the Admin"));
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdminBySuperAdmin(AdminRequest adminRequest, int adminId) {
		
		return adminRepository.findById(adminId).map(admin -> {
			
			admin = adminRepository.save(adminMapper.mapToAdmin(adminRequest, admin));
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin updated")
							.setData(adminMapper.mapToAdminResponse(admin)));
			
			
		}).orElseThrow(() -> new AdminNotFoundByIdException("Failed to update the admin"));
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
