package com.jsp.whms.responsedto;

import com.jsp.whms.enums.AdminType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareHouseResponse {
	
	private int wareHouseId;
	private String name;
	
	

}
