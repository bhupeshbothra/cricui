package com.stackroute.usercricservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.usercricservice.domain.User;
import com.stackroute.usercricservice.exception.UserAlreadyExistsException;
import com.stackroute.usercricservice.exception.UserNotFoundException;
import com.stackroute.usercricservice.repository.UserCricPlayerRepository;

import junit.framework.Assert;

public class UserCricPlayerServiceTest {
	
	@Mock
	private UserCricPlayerRepository userCricPlayerRepository;

	@InjectMocks
	public UserCricPlayerServiceImpl userCricPlayerServiceImpl;

	private User user;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		user = new User();
		user.setEmail("jaindemodemo");;
		user.setUsername("jaindemodemo");
		user.setPassword("jaindemodemo");
	}

	@After
	public void delete() {
		user = null;
	}
	
	@Test
	public void registerUser() throws UserAlreadyExistsException {
		
		Mockito.when(userCricPlayerRepository.save(user)).thenReturn(user);
		User fetchedUser = userCricPlayerServiceImpl.registerUser(user);
		Assert.assertEquals(fetchedUser, user);
		verify(userCricPlayerRepository, times(1)).save(user);
	}
	
	@Test
	public void validateCredential() throws UserNotFoundException {
		Mockito.when(userCricPlayerRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword())).thenReturn(user);
		User fetchedUser = userCricPlayerServiceImpl.validateCredential(user.getUsername(), user.getPassword());
		Assert.assertEquals(fetchedUser, user);
		verify(userCricPlayerRepository, times(1)).findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
