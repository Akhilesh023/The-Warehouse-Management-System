package com.jsp.whms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.whms.requestdto.StorageTypeRequest;
import com.jsp.whms.responsedto.StorageTypeResponse;
import com.jsp.whms.util.ResponseStructure;

public interface StorageTypeService {

	ResponseEntity<ResponseStructure<StorageTypeResponse>> addStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest, int storageTypeId);

	ResponseEntity<ResponseStructure<List<StorageTypeResponse>>> findAllWareHouses();

}
