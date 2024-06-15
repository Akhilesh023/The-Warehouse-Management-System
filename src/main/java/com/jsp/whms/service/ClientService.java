package com.jsp.whms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.requestdto.ClientRequest;
import com.jsp.whms.responsedto.ApiKeyResponse;
import com.jsp.whms.responsedto.ClientResponse;
import com.jsp.whms.util.ResponseStructure;

public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> registerClient(ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest, int clientId);

}
