package com.stackroute.playerrecommendationservice.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.repository.PlayerRecommendationRepository;

import junit.framework.Assert;

public class PlayerRecommendationServiceTest {

	@Mock
	PlayerRecommendationRepository playerRecommendationRepository;

	@InjectMocks
	PlayerRecommendationServiceImpl playerRecommendationServiceImpl;

	public Player player;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		player = new Player();
		player.setFullName("Bhupesh");
		player.setName("Bhupesh");
		player.setPid("114");
		player.setTotalcount(1);

	}

	@After
	public void delete() {
		player = null;
	}

	@Test
	public void savePlayer() throws Exception {

		Mockito.when(playerRecommendationRepository.save(player)).thenReturn(player);
		Player fetchedPlayer = playerRecommendationServiceImpl.savePlayer(player);
		Assert.assertEquals(fetchedPlayer, player);
		verify(playerRecommendationRepository, times(1)).findBypid(player.getPid());
		verify(playerRecommendationRepository, times(1)).save(player);
	}
	
	@Test
	public void deletePlayer() throws Exception {

		Mockito.when(playerRecommendationRepository.save(player)).thenReturn(player);
		Player fetchedPlayer = playerRecommendationServiceImpl.deletePlayer(player);
		//Assert.assertEquals(fetchedPlayer, player);
		verify(playerRecommendationRepository, times(1)).findBypid(player.getPid());
		
		
	}
	
	@Test
	public void testgetAllUserPlayerList() throws Exception {
		Mockito.when(playerRecommendationRepository.save(player)).thenReturn(player);
		List<Player> players=playerRecommendationServiceImpl.getAllUserPlayerList();
		verify(playerRecommendationRepository, times(1)).findAll();
		
		
	}

}
