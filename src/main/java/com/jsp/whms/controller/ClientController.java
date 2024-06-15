package com.jsp.whms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.whms.entity.Storage;
import com.jsp.whms.repository.StorageRepository;
import com.jsp.whms.requestdto.ClientRequest;
import com.jsp.whms.responsedto.ApiKeyResponse;
import com.jsp.whms.responsedto.ClientResponse;
import com.jsp.whms.service.ClientService;
import com.jsp.whms.util.ResponseStructure;

@RequestMapping("/api/v1")
@RestController
public class ClientController {
	
	@Autowired
	private StorageRepository storageRepository;
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/clients")
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(@RequestBody ClientRequest clientRequest){
		return clientService.registerClient(clientRequest);
	}
	
//	@GetMapping("/results")
//	public List<Storage> getResults(Double capacityInKg,Double lengthInMeters, Double breadthInMeters,Double heightInMeters) {
//		return storageRepository.findFirstByCapacityInKgAndLengthInMetersAndBreadthInMetersAndHeightInMeters(100d, 10d, 5d, 10d);
//	}
	
	@PutMapping("/clients/{clientId}")
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@RequestBody ClientRequest clientRequest,@PathVariable int clientId){
		return clientService.updateClient(clientRequest,clientId);
	}
	
}
