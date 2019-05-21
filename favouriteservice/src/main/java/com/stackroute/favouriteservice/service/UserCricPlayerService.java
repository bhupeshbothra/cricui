package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;



public interface UserCricPlayerService {

	public User saveUserPlayerToFavoriteList(CricPlayer player, String username) throws PlayerAlreadyExistsException;
	public User deleteUserPlayerToFavoriteList(String trackId , String username ) throws PlayerNotFoundException;

	public List<CricPlayer> getAllUserPlayerList(String username ) throws Exception;
	//public User updateCommentUserPlayerList(String playerId , String comments, String username ) throws PlayerNotFoundException;
//	public User registerUser(User user) throws UserAlreadyExistsException;

}
