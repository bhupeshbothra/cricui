package com.stackroute.favouriteservice.domain;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CricPlayer {

	
  
  

    @JsonProperty("fullName")
    private String fullName;
    
    @JsonProperty("name")
    private String name;

    @Id
    private String pid;

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

	

	public CricPlayer(String fullName, String name, String pid) {
		super();
		
		this.fullName = fullName;
		this.name = name;
		this.pid = pid;
	}

	public CricPlayer() {
		super();
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CricPlayer [ fullName=" + fullName + ", name=" + name + ", pid=" + pid + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
    
    
}
