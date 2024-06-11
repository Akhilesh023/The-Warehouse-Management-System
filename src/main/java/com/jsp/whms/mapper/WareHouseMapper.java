package com.jsp.whms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;

@Component
public class WareHouseMapper {
	
	
	public WareHouse mapToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse) {
		wareHouse.setName(wareHouseRequest.getName());
		return wareHouse;
	}
	
	
	public WareHouseResponse mapToWareHouseResponse(WareHouse wareHouse) {
		return WareHouseResponse.builder()
				.wareHouseId(wareHouse.getWareHouseId())
				.name(wareHouse.getName())
				.totalCapacity(0)
				.build();
		
	}

}
