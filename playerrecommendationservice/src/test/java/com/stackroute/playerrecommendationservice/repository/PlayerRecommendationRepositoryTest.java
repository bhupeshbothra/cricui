package com.stackroute.playerrecommendationservice.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.playerrecommendationservice.domain.Player;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlayerRecommendationRepositoryTest{


	@Autowired 
	PlayerRecommendationRepository playerRecommendationRepository;
	
	public Player player;
	
	
	@Before
	public void setup() {
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
	public void savePlayer() {
		int count=0;
		Player pla = playerRecommendationRepository.findBypid(player.getPid());
		if(pla!=null) {
			count = pla.getTotalcount();
			pla.setTotalcount(count+1);
			player =pla;
		}
		
		Player plaobj = playerRecommendationRepository.save(player);
		Assert.assertEquals(plaobj.getPid(), player.getPid());
		//System.out.println(user.toString());
		//userCricPlayerRepository.deletebyname(user.getUsername());
		
	}
	
}
