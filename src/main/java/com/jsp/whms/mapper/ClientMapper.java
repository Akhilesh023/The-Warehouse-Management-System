package com.jsp.whms.mapper;

import org.springframework.stereotype.Component;

import com.jsp.whms.entity.Client;
import com.jsp.whms.requestdto.ClientRequest;
import com.jsp.whms.responsedto.ApiKeyResponse;
import com.jsp.whms.responsedto.ClientResponse;

@Component
public class ClientMapper {
	
	public Client mapToClient(ClientRequest clientRequest,Client client) {
		client.setBuisnessName(clientRequest.getBuisnessName());
		client.setEmail(clientRequest.getEmail());
		client.setContactNumber(clientRequest.getContactNumber());
		
		return client;
	}
	
	public ApiKeyResponse maptoApiKeyResponse(Client client) {
		return ApiKeyResponse.builder()
				.apiKey(client.getApiKey())
				.message("Created Successfully")
				.build();
	}
	
	public ClientResponse mapClientResponse(Client client) {
		return ClientResponse.builder()
				.clientId(client.getClientId())
				.buisnessName(client.getBuisnessName())
				.email(client.getEmail())
				.contactNumber(client.getContactNumber())
				.build();
	}

}
