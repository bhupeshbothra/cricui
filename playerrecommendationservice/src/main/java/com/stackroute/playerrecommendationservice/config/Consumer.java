package com.stackroute.playerrecommendationservice.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.rabbitmq.domain.PlayerDTO;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationServiceImpl;

@Component
public class Consumer {


	@Autowired
	PlayerRecommendationServiceImpl playerRecommendationServiceImpl;
	
	@RabbitListener(queues = "favirote_add_queue")
	public void addPlayerToList(PlayerDTO playerDTO) throws Exception {
		
		Player player  = new Player();
		
		player.setFullName(playerDTO.getFullName());
		player.setName(playerDTO.getName());
		player.setPid(playerDTO.getPid());
		player.setTotalcount(0);
		
		playerRecommendationServiceImpl.savePlayer(player);
	}
	
	@RabbitListener(queues = "favirote_delete_queue")
	public void removePlayerFromList(PlayerDTO playerDTO) throws Exception {
		
		Player player  = new Player();
		
		player.setFullName(playerDTO.getFullName());
		player.setName(playerDTO.getName());
		player.setPid(playerDTO.getPid());
		player.setTotalcount(0);
		
		playerRecommendationServiceImpl.deletePlayer(player);
	}
	
}
