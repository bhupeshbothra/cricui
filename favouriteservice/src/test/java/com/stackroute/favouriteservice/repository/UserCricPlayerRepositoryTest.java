package com.stackroute.favouriteservice.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserCricPlayerRepositoryTest {

	@Autowired
	UserCricPlayerRepository userCricPlayerRepository;
	
	private User user;
	
	private CricPlayer cricPlayer;
	
	private List list;
	
	@Before
	public void setup() {
		
		cricPlayer = new CricPlayer();
		cricPlayer.setFullName("demoplayer");
		cricPlayer.setName("demoplayer");
		cricPlayer.setPid("12");
		list = new ArrayList();
		list.add(cricPlayer);
		
		user = new User();
		user.setUsername("bhupesh");
		
		user.setPlayerList(list);
		
	}

	@After
	public void delete() {
		user = null;
		cricPlayer =null;
		list =null;
	}
	
	@Test
	public void testSavePlayerList() {
		userCricPlayerRepository.delete(user);
		
		userCricPlayerRepository.save(user);
		User userObj = userCricPlayerRepository.findByUsername(user.getUsername());
		
		List<CricPlayer> cricplayerList = userObj.getPlayerList();
		Assert.assertEquals(cricplayerList.get(0).getPid(), user.getPlayerList().get(0).getPid());
		
		userCricPlayerRepository.delete(user);
		
	}
	
	
}
