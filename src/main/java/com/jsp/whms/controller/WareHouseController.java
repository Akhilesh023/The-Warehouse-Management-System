package com.jsp.whms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.entity.WareHouse;
import com.jsp.whms.requestdto.WareHouseRequest;
import com.jsp.whms.responsedto.WareHouseResponse;
import com.jsp.whms.service.WareHouseService;
import com.jsp.whms.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class WareHouseController {
	
	@Autowired
	private WareHouseService wareHouseService;
	
	@GetMapping("/warehouses")
	public String addWareHouse(){
		return "WareHouse Found";
	}

}
