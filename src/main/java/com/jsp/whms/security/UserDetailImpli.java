package com.jsp.whms.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.whms.entity.Admin;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class UserDetailImpli implements UserDetails {
	
	private Admin admin;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return admin.getAdminType().getPrivileges().stream().map(privilege -> 
		new SimpleGrantedAuthority(privilege.name()))
		.toList();
		
	}

	@Override
	public String getPassword() {
		
		
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		
		
		return admin.getEmail();
	}

	

}
