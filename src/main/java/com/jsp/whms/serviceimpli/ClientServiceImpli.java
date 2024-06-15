package com.jsp.whms.serviceimpli;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Client;
import com.jsp.whms.exception.ClientNotFoundByIdException;
import com.jsp.whms.mapper.ClientMapper;
import com.jsp.whms.repository.ClientRepository;
import com.jsp.whms.requestdto.ClientRequest;
import com.jsp.whms.responsedto.ApiKeyResponse;
import com.jsp.whms.responsedto.ClientResponse;
import com.jsp.whms.service.ClientService;
import com.jsp.whms.util.ResponseStructure;

@Service
public class ClientServiceImpli implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientMapper clientMapper;

	
	
	@Override
	public ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest) {
		
		
		Client client = clientRepository.save(clientMapper.mapToClient(clientRequest, new Client()));
		String apiKey = UUID.randomUUID().toString();
		client.setApiKey(apiKey);
		clientRepository.save(client);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<ApiKeyResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Client created Successfully")
						.setData(clientMapper.maptoApiKeyResponse(client)));
		
	}

	@Override
	public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest, int clientId) {
		
		return clientRepository.findById(clientId).map(existingClient -> {
			
			Client updatedClient = clientMapper.mapToClient(clientRequest, existingClient);
			 updatedClient = clientRepository.save(updatedClient);
			 
			 return ResponseEntity.status(HttpStatus.OK)
					 .body(new ResponseStructure<ClientResponse>()
							 .setData(clientMapper.mapClientResponse(existingClient))
							 .setMessage("Client Updated")
							 .setStatus(HttpStatus.OK.value()));
		}).orElseThrow(() -> new ClientNotFoundByIdException("Cleint failed to update"));
		
	}

}
