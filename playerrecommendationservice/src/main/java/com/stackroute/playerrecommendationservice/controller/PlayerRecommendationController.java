package com.stackroute.playerrecommendationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.playerrecommendationservice.bean.Players;
import com.stackroute.playerrecommendationservice.domain.Player;
import com.stackroute.playerrecommendationservice.service.PlayerRecommendationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/playerservice")
public class PlayerRecommendationController {

	
	private ResponseEntity responseEntity;

	private PlayerRecommendationService playerRecommendationService;

	public PlayerRecommendationController() {
		super();
	}

	@Autowired
	public PlayerRecommendationController(PlayerRecommendationService playerRecommendationService) {
		super();
		this.playerRecommendationService = playerRecommendationService;
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllRecommPlayerList() {

		try {
			List<Player> playerList = playerRecommendationService.getAllUserPlayerList();
			Players  ps = new Players();
			ps.setPlayerList(playerList);
			responseEntity = new ResponseEntity(ps, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

}
