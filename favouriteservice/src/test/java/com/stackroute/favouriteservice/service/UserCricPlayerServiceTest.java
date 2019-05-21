package com.stackroute.favouriteservice.service;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.repository.UserCricPlayerRepository;

import junit.framework.Assert;

public class UserCricPlayerServiceTest {

	@Mock
	private UserCricPlayerRepository userCricPlayerRepository;

	@Mock
	public Producer producer;

	@InjectMocks
	public UserCricPlayerServiceImpl userCricPlayerServiceImpl;

	private User user;

	private CricPlayer cricPlayer;

	private List list;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		cricPlayer = new CricPlayer();
		cricPlayer.setFullName("demoplayer");
		cricPlayer.setName("demoplayer");
		cricPlayer.setPid("12");
		list = new ArrayList();
		list.add(cricPlayer);

		user = new User();
		user.setUsername("jaindemo");

		user.setPlayerList(list);
	}

	@After
	public void delete() {
		user = null;
	}

	@Test
	public void saveUserPlayerToFavoriteListTest() throws PlayerAlreadyExistsException {

		user = new User();
		user.setUsername("jainDemo1");
		
		Mockito.when(userCricPlayerRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userCricPlayerServiceImpl.saveUserPlayerToFavoriteList(cricPlayer, user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(userCricPlayerRepository, timeout(1)).findByUsername(user.getUsername());
		verify(userCricPlayerRepository, times(1)).save(user);
	}

	@Test
	public void deleteUserPlayerToFavoriteListTest() throws  PlayerNotFoundException {
		Mockito.when(userCricPlayerRepository.findByUsername(user.getUsername())).thenReturn(user);
		User fetchedUser = userCricPlayerServiceImpl.deleteUserPlayerToFavoriteList(cricPlayer.getPid(),user.getUsername());
		Assert.assertEquals(fetchedUser, user);
		verify(userCricPlayerRepository, timeout(1)).findByUsername(user.getUsername());
		verify(userCricPlayerRepository, times(1)).save(user);

	}
	
	@Test
	public void testgetAllFavoriteList() throws Exception {
		Mockito.when(userCricPlayerRepository.findByUsername(user.getUsername())).thenReturn(user);
		List fetchedList = userCricPlayerServiceImpl.getAllUserPlayerList(user.getUsername());
		Assert.assertEquals(fetchedList, list);
		verify(userCricPlayerRepository, times(1)).findByUsername(user.getUsername());

	}

}
