package com.stackroute.usercricservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.usercricservice.domain.User;
import com.stackroute.usercricservice.exception.UserAlreadyExistsException;
import com.stackroute.usercricservice.exception.UserNotFoundException;
import com.stackroute.usercricservice.repository.UserCricPlayerRepository;

@Service
public class UserCricPlayerServiceImpl implements UserCricPlayerService {

	private UserCricPlayerRepository userCricPlayerRepository;

	@Autowired
	public UserCricPlayerServiceImpl(UserCricPlayerRepository userCricPlayerRepository) {
		super();
		this.userCricPlayerRepository = userCricPlayerRepository;
	}

	@Override
	public User registerUser(User user) throws UserAlreadyExistsException{
		// TODO Auto-generated method stub

		User userexisting = userCricPlayerRepository.findByUsername(user.getUsername());

		if (userexisting != null) {
			throw new UserAlreadyExistsException();
		}

		return userCricPlayerRepository.save(user);
	}

	@Override
	public User validateCredential(String userName, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userCricPlayerRepository.findByUsernameAndPassword(userName, password);

		if (user == null) {
			throw new UserNotFoundException();
		}

		return user;
	}

}
