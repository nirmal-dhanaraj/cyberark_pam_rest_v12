package com.pam.v12.service;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pam.v12.model.AuthRequest;
import com.pam.v12.util.AuthRequestBuilder;
import com.pam.v12.util.AuthUtil;
//import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GetSafeService {
	@Value("${application.logon.url}")
	private String access_token_url;
	@Value("${application.logout.url}")
	private String logOff_url;
	@Value("${auth.username}")
	private String username;
	@Value("${auth.pass}")
	private String password;
	
	@Autowired
	private AuthService authService;
	
	public ResponseEntity<String> getSafeLists() throws IOException
	{		
		String authorizationToken1= AuthUtil.authlogin();		
		
		RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers1 = new HttpHeaders();
	    String authorizationToken=authorizationToken1.substring(1, authorizationToken1.length()-1);
	    System.out.println("Token : "+authorizationToken); 
	    
	    //adding the query params to the URL
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://compsrv1.corpad.com/PasswordVault/api/Safes")
                .queryParam("includeAccounts", "true");
        
        
	    headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    headers1.setContentType(MediaType.APPLICATION_JSON);
	    headers1.add("User-Agent", "Spring's RestTemplate" ); 
        headers1.add("Authorization", authorizationToken);
	    
		HttpEntity<String> requestEntity = new HttpEntity<String>(null,headers1);
	    
		ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );		
		
		System.out.println("Response --------------------------"+responseEntity.getBody()); 
		
		ResponseEntity<String> logoutResponse = authService.logoutAccessToken(authorizationToken1);
	    System.out.println("Logout Response : "+logoutResponse.getBody().toString()); 
	    
		return responseEntity;

	}
	
}
