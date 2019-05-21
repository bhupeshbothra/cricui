package com.stackroute.usercricservice.service;

import com.stackroute.usercricservice.domain.User;
import com.stackroute.usercricservice.exception.UserAlreadyExistsException;
import com.stackroute.usercricservice.exception.UserNotFoundException;



public interface UserCricPlayerService {

	public User registerUser(User user) throws UserAlreadyExistsException;
	public User validateCredential(String userName, String password) throws UserNotFoundException;
}
