package com.stackroute.usercricservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.usercricservice.domain.User;
import com.stackroute.usercricservice.exception.UserAlreadyExistsException;
import com.stackroute.usercricservice.exception.UserNotFoundException;
import com.stackroute.usercricservice.service.SecurityTokenGenerator;
import com.stackroute.usercricservice.service.UserCricPlayerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/usercricservice")
public class UserCricController {

	private ResponseEntity responseEntity;

    private SecurityTokenGenerator secTokenGenerator;
	
	
	private UserCricPlayerService userCricPlayerService;

	public UserCricController() {
		super();
	}

	@Autowired
	public UserCricController(UserCricPlayerService userCricPlayerService , SecurityTokenGenerator secTokenGenerator) {
		super();
		this.userCricPlayerService = userCricPlayerService;
		this.secTokenGenerator = secTokenGenerator;
	}

	@PostMapping("/register")
	public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {

		try {
			System.out.println("user info in User Track Controller" + user.toString());
			User fetchedUser = userCricPlayerService.registerUser(user);
			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			throw new UserAlreadyExistsException();
		}
		return responseEntity;

	}

	@RequestMapping(method=RequestMethod.POST, path="/login")
	public ResponseEntity loginUser(@RequestBody User user) throws UserNotFoundException {
		
		Map<String, String> 	map =null;
		try {
			User userObj = userCricPlayerService.validateCredential(user.getUsername(), user.getPassword());
			
			if(userObj.getUsername().equals(user.getUsername())) {
				map = secTokenGenerator.generateToken(userObj);
			}
			responseEntity = new ResponseEntity(map, HttpStatus.OK);
			
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			throw new UserNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity("try after some time", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;
	}

	


}
