package com.stackroute.playerrecommendationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.playerrecommendationservice.domain.Player;

public interface PlayerRecommendationRepository  extends JpaRepository<Player, Integer> {

	public Player findBypid(String pid);	
	public Player findByname(String name);	
	
	@Transactional
	@Modifying
	@Query("UPDATE Player p SET p.totalcount = :totalcount  WHERE p.pid = :pid")
	int updatePlayerCount(@Param("pid") String pid ,@Param("totalcount") int totalcount);
}
