package com.jsp.whms.serviceimpli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Address;
import com.jsp.whms.exception.AddressNotFoundByIdException;
import com.jsp.whms.exception.WarehouseNotFoundByIdException;
import com.jsp.whms.mapper.AddressMapper;
import com.jsp.whms.repository.AddressRepository;
import com.jsp.whms.repository.WareHouseRepository;
import com.jsp.whms.requestdto.AddressRequest;
import com.jsp.whms.responsedto.AddressResponse;
import com.jsp.whms.service.AddressService;
import com.jsp.whms.util.ResponseStructure;

@Service
public class AddressServiceImpli implements AddressService {

	@Autowired
	AddressMapper addressMapper;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	WareHouseRepository wareHouseRepository;
	
	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest, int wareHouseId) {
		
		return wareHouseRepository.findById(wareHouseId).map(warehouse -> {
			Address address = addressMapper.mapToAddress(addressRequest, new Address());
			
			address.setWareHouse(warehouse);
			//warehouse.setAddress(address); //.
			address = addressRepository.save(address);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AddressResponse>()
							.setData(addressMapper.mapToAddressResponse(address))
							.setStatus(HttpStatus.OK.value())
							.setMessage("Address Created !!!!!!!!!!"));
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("No WareHouse Id Found"));
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddressById(AddressRequest addressRequest, int addressId) {
		
		return addressRepository.findById(addressId).map(address -> {
			
			 address = addressRepository.save(addressMapper.mapToAddress(addressRequest, address));
			 
			 return ResponseEntity.status(HttpStatus.OK)
					 .body(new ResponseStructure<AddressResponse>()
							 .setData(addressMapper.mapToAddressResponse(address))
							 .setStatus(HttpStatus.OK.value())
							 .setMessage("Address Updated"));
		}).orElseThrow(() -> new AddressNotFoundByIdException("Failed to Update the address"));
		
		
		
		
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> findByAddressId(int addressId) {
		
		return addressRepository.findById(addressId).map(address -> {
			
			return ResponseEntity.status(HttpStatus.FOUND)
					.body(new ResponseStructure<AddressResponse>()
							.setData(addressMapper.mapToAddressResponse(address))
							.setMessage("Address Found !!!!!")
							.setStatus(HttpStatus.FOUND.value()));
		}).orElseThrow( () -> new AddressNotFoundByIdException("Address not found for the Id"));
		
		
		
	}

}
