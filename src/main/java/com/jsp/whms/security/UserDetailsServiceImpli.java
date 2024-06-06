package com.jsp.whms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.whms.entity.Admin;
import com.jsp.whms.repository.AdminRepository;

@Service
public class UserDetailsServiceImpli implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;


	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	return adminRepository.findByEmail(username)
			.map(UserDetailImpli :: new).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
		
	}

}
