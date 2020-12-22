package com.pam.v12.util;


import com.pam.v12.model.AuthRequest;

public class AuthRequestBuilder {
	public AuthRequest buildAuthRequest(String username, String password)
	{
		AuthRequest authReq = new AuthRequest();
		authReq.setUsername(username);
		authReq.setPassword(password);
		authReq.setConcurrentSession("false");
		authReq.setSecureMode(false);
		return authReq;
		
	}
}
