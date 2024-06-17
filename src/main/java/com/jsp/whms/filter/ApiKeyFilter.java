package com.jsp.whms.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.whms.entity.Client;
import com.jsp.whms.exception.ApiKeyOrUsernameNotFoundException;
import com.jsp.whms.exception.IllegalOperationException;
import com.jsp.whms.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter{


	//@Autowired
	private ClientRepository clientRepository;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		if(request.getSession(false) != null) {
		 	
			throw new IllegalOperationException("Admin cannot access Client end point");
		}

		if(!request.getRequestURI().equals("/api/v1/client/register")) {

			String apiKey = request.getHeader("API-KEY");
			String username = request.getHeader("Username");

			if(apiKey != null || username != null)

			{	
				Client client = clientRepository.findByEmail(username).orElseThrow(() -> new ApiKeyOrUsernameNotFoundException("User not found"));

				if(!apiKey.equals(client.getApiKey())) {
					throw new BadCredentialsException("Bad Credentials Entered");
				}
			}
			else {
				throw new UsernameNotFoundException("Username is not found");
			}
		}
		filterChain.doFilter(request, response);  //must be the last line to execute in a filter.
	}

}
