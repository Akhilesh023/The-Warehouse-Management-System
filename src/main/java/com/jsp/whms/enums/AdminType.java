package com.jsp.whms.enums;

import java.util.List;

public enum AdminType {
	
	SUPER_ADMIN(List.of(Privilege.CREATE_ADMIN,Privilege.CREATE_WAREHOUSE,Privilege.CREATE_ADDRESS,Privilege.CREATE_STORAGE
			,Privilege.READ,Privilege.UPDATE_ADDRESS,Privilege.UPDATE_ADMIN,Privilege.UPDATE_STORAGE,Privilege.UPDATE_WAREHOUSE
			,Privilege.DELETE_ADDRESS,Privilege.DELETE_ADMIN,Privilege.DELETE_STORAGE,Privilege.DELETE_WAREHOUSE)),
	
	ADMIN(List.of(Privilege.CREATE_STORAGE,Privilege.READ,Privilege.UPDATE_ADMIN,Privilege.UPDATE_STORAGE,Privilege
			.DELETE_STORAGE));
	
	private List<Privilege> privileges;
	
	 AdminType(List<Privilege> privileges) {
		this.privileges=privileges;
	}
	 
	public List<Privilege> getPrivileges(){
		 return this.privileges;
	 }
	
	
	
	

}
