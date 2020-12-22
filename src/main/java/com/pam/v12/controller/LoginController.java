package com.pam.v12.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pam.v12.service.AuthService;
import com.pam.v12.service.GetSafeService;


@Controller
public class LoginController {
	
private final AuthService authService;
private final GetSafeService getSafeService;
	
	@Autowired
	LoginController(final AuthService authService, final GetSafeService getSafeService){
		this.authService= authService;
		this.getSafeService= getSafeService;
	}
	
	@RequestMapping("/getAccessToken")
	public ResponseEntity<String> getAccessToken() throws Exception {
		return authService.getAccessToken();
	}
	
	@RequestMapping("/getSafeLists")
	public ResponseEntity<String> getSafeLists() throws Exception {
		return getSafeService.getSafeLists();
	}

}