package com.jsp.whms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;
import com.jsp.whms.util.ResponseStructure;


public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> addWareHouse(WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,
			int wareHouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findByWarehouseId(int wareHouseId);

}
