package com.jsp.whms.controller;

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

import com.jsp.whms.requestdto.AddressRequest;
import com.jsp.whms.responsedto.AddressResponse;
import com.jsp.whms.service.AddressService;
import com.jsp.whms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PreAuthorize("hasAuthority('CREATE_ADDRESS')")
	@PostMapping("/warehouses/{wareHouseId}/addresses")
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@RequestBody AddressRequest addressRequest, @PathVariable int wareHouseId){
		return addressService.addAddress(addressRequest, wareHouseId);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
	@PutMapping("/addresses/{addressId}")
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddressById(@RequestBody AddressRequest addressRequest,@PathVariable int addressId){
		
		return addressService.updateAddressById(addressRequest,addressId);
	}
	
	
	@GetMapping("/address/{addressId}") 
	ResponseEntity<ResponseStructure<AddressResponse>> findByAddressId(@PathVariable int addressId){
		return addressService.findByAddressId(addressId);
	}

}
