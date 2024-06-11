package com.jsp.whms.serviceimpli;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.exception.WarehouseNotFoundByIdException;
import com.jsp.whms.mapper.WareHouseMapper;
import com.jsp.whms.repository.WareHouseRepository;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;
import com.jsp.whms.service.WareHouseService;
import com.jsp.whms.util.ResponseStructure;

@Service
public class WareHouseServiceImpli implements WareHouseService {
	
	@Autowired
	private WareHouseRepository wareHouseRepository;
	 
	@Autowired
	private WareHouseMapper wareHouseMapper;

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> addWareHouse(WareHouseRequest wareHouseRequest) {
		
		WareHouse wareHouse = wareHouseMapper.mapToWareHouse(wareHouseRequest, new WareHouse());
		wareHouse = wareHouseRepository.save(wareHouse);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<WareHouseResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("WareHouse Details are added")
						.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
	}

	@Override
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest, int wareHouseId) {
		
		return wareHouseRepository.findById(wareHouseId).map(wareHouse -> {
			
			wareHouse = wareHouseRepository.save(wareHouseMapper.mapToWareHouse(wareHouseRequest, wareHouse));
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<WareHouseResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Ware House Updated")
							.setData(wareHouseMapper.mapToWareHouseResponse(wareHouse)));
			
		}).orElseThrow(() -> new WarehouseNotFoundByIdException("Failed to update the warehouse"));
		
		
	}

}
