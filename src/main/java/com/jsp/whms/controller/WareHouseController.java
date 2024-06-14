package com.jsp.whms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.repository.WareHouseRepository;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;
import com.jsp.whms.service.WareHouseService;
import com.jsp.whms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;

	@PreAuthorize("hasAuthority('CREATE_WAREHOUSE')") 
	@PostMapping("/warehouses")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> addWareHouse(@RequestBody WareHouseRequest wareHouseRequest){
		return wareHouseService.addWareHouse(wareHouseRequest);
	}
	
	@PreAuthorize("hasAuthority('UPDATE_WAREHOUSE')") 
	@PutMapping("/warehouses/{wareHouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(@RequestBody WareHouseRequest wareHouseRequest, @PathVariable int wareHouseId){
		return wareHouseService.updateWareHouse(wareHouseRequest,wareHouseId);
	}
	
	@GetMapping("/warehouses/{wareHouseId}")
	public ResponseEntity<ResponseStructure<WareHouseResponse>> findByWarehouseId(@PathVariable int wareHouseId){
		
		return wareHouseService.findByWarehouseId(wareHouseId);
	}
	
	@GetMapping("/warehouses")
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findAllWareHouses(){
		return wareHouseService.findAllWareHouses();
	}
	
	@GetMapping("cities/{city}/warehouses")
	public ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouseByCity( @PathVariable String city){
		return wareHouseService.findWareHouseByCity(city);
	}
	
	
	
	

	
}
