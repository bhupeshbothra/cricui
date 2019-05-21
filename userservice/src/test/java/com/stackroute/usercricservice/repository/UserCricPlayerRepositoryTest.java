package com.stackroute.usercricservice.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.usercricservice.domain.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserCricPlayerRepositoryTest {

	@Autowired
	private UserCricPlayerRepository userCricPlayerRepository;

	private User user;

	@Before
	public void setup() {
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
	public void registerUser() {
		userCricPlayerRepository.deletebyname(user.getUsername());
		
		userCricPlayerRepository.save(user);
		User userObj = userCricPlayerRepository.findByUsername(user.getUsername());
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		System.out.println(user.toString());
		userCricPlayerRepository.deletebyname(user.getUsername());
		
	}
	
	@Test
	public void validateCredential() {
		userCricPlayerRepository.deletebyname(user.getUsername());
		
		userCricPlayerRepository.save(user);
		User userObj = userCricPlayerRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		Assert.assertEquals(userObj.getUsername(), user.getUsername());
		System.out.println(user.toString());
		userCricPlayerRepository.deletebyname(user.getUsername());
		
	}
}
