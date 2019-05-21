package com.stackroute.playerrecommendationservice.domain;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {

	@Id @GeneratedValue
	private int playerId;
	
	private String fullName;
	private String name;
	private String pid;
	
	private int totalcount;
	
	
	public Player() {
		super();
	}
	
	public Player(String fullName, String name, String pid, int totalcount) {
		super();
		this.fullName = fullName;
		this.name = name;
		this.pid = pid;
		this.totalcount = totalcount;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}
	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}
	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the totalcount
	 */
	public int getTotalcount() {
		return totalcount;
	}
	/**
	 * @param totalcount the totalcount to set
	 */
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", fullName=" + fullName + ", name=" + name + ", pid=" + pid
				+ ", totalcount=" + totalcount + "]";
	}
	
	
	
	
}
