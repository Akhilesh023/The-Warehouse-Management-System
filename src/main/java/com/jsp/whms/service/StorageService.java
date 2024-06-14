package com.jsp.whms.service;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.requestdto.StorageRequest;
import com.jsp.whms.responsedto.StorageResponse;
import com.jsp.whms.util.ResponseStructure;
import com.jsp.whms.util.SimpleResponseStructure;

public interface StorageService {

	ResponseEntity<SimpleResponseStructure<String>> addStorage(StorageRequest storageRequest, int wareHouseId, int noOfStorageUnits);

	ResponseEntity<ResponseStructure<StorageResponse>> updateResponse(StorageRequest storageRequest, int storageId);

}
