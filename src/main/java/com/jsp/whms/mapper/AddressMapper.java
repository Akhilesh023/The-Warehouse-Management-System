package com.jsp.whms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.Address;
import com.jsp.whms.requestdto.AddressRequest;
import com.jsp.whms.responsedto.AddressResponse;

@Component
public class AddressMapper {
	
	public Address mapToAddress(AddressRequest addressRequest, Address address) {
		address.setAddressLine(addressRequest.getAddressLine());
		address.setCity(addressRequest.getCity());
		address.setCountry(addressRequest.getCountry());
		address.setLatitude(addressRequest.getLatitude());
		address.setLongitutde(addressRequest.getLongitutde());
		address.setState(addressRequest.getState());
		address.setPincode(addressRequest.getPincode());
		return address;
	}
	
	public AddressResponse mapToAddressResponse(Address address) {
		return AddressResponse.builder()
				.addressId(address.getAddressId())
				.addressLine(address.getAddressLine())
				.city(address.getCity())
				.country(address.getCountry())
				.latitude(address.getLatitude())
				.longitutde(address.getLongitutde())
				.state(address.getState())
				.pincode(address.getPincode())
				.build();
	}

}
