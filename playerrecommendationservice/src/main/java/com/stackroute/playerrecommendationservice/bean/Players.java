package com.stackroute.playerrecommendationservice.bean;

import java.util.List;

import com.stackroute.playerrecommendationservice.domain.Player;

public class Players {

	List<Player> playerList;

	/**
	 * @return the playerList
	 */
	public List<Player> getPlayerList() {
		return playerList;
	}

	/**
	 * @param playerList the playerList to set
	 */
	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	
	
}
