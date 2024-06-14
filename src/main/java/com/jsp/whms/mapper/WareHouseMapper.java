package com.jsp.whms.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.whms.entity.Address;
import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {
	
	@Autowired
	private AddressMapper addressMapper;
	
	
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse) {
		wareHouse.setName(wareHouseRequest.getName());
		return wareHouse;
	}
	
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.build();
		
	}
	
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse, Address address) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.addressResponse(addressMapper.mapToAddressResponse(address))
				.build();
	}

}
