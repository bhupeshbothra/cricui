package com.stackroute.usercricservice.service;

import java.util.Map;

import com.stackroute.usercricservice.domain.User;

public interface SecurityTokenGenerator {

	public Map<String,String> generateToken(User user);
	
}
