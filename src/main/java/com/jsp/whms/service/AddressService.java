package com.jsp.whms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.requestdto.AddressRequest;
import com.jsp.whms.responsedto.AddressResponse;
import com.jsp.whms.util.ResponseStructure;

public interface AddressService {

	ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest,int wareHouseId);

	ResponseEntity<ResponseStructure<AddressResponse>> updateAddressById(AddressRequest addressRequest, int addressId);

	ResponseEntity<ResponseStructure<AddressResponse>> findByAddressId(int addressId);

}
