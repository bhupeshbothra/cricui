package com.stackroute.favouriteservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.exception.UserAlreadyExistsException;
import com.stackroute.favouriteservice.service.UserCricPlayerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/favoriteservice")
public class FavoriteController {

	private ResponseEntity responseEntity;

	private UserCricPlayerService userCricPlayerService;

	public FavoriteController() {
		super();
	}

	@Autowired
	public FavoriteController(UserCricPlayerService userCricPlayerService) {
		super();
		this.userCricPlayerService = userCricPlayerService;
	}

//	@PostMapping("/register")
//	public ResponseEntity registerUser(@RequestBody User user) throws UserAlreadyExistsException {
//
//		try {
//			System.out.println("user info in User Track Controller" + user.toString());
//			User fetchedUser = userCricPlayerService.registerUser(user);
//			responseEntity = new ResponseEntity<User>(user, HttpStatus.CREATED);
//		} catch (UserAlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			throw new UserAlreadyExistsException();
//		}
//		return responseEntity;
//
//	}

	@PostMapping("/user/{username}/player")
	public ResponseEntity<?> AddUserPlayerToFavoriteList(@RequestBody CricPlayer cricPlayer,
			@PathVariable String username) throws PlayerAlreadyExistsException {

		try {
			User fetchedUser = userCricPlayerService.saveUserPlayerToFavoriteList(cricPlayer, username);
			responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.CREATED);
		} catch (PlayerAlreadyExistsException e) {
			throw new PlayerAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}

	@DeleteMapping("/user/{username}/player")
	public ResponseEntity<?> deleteTrackToWishList(@RequestBody CricPlayer cricPlayer, @PathVariable String username)
			throws PlayerNotFoundException {

		try {
			User fetchedUser = userCricPlayerService.deleteUserPlayerToFavoriteList(cricPlayer.getPid(), username);
			responseEntity = new ResponseEntity<User>(fetchedUser, HttpStatus.OK);
		} catch (PlayerNotFoundException e) {
			throw new PlayerNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}
	
	@GetMapping("/user/{username}/player")
	public ResponseEntity<?> getAllUserTrackList(@PathVariable String username) throws PlayerNotFoundException {

		try {
			List<CricPlayer> playerList = userCricPlayerService.getAllUserPlayerList(username);
			responseEntity = new ResponseEntity(playerList, HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return responseEntity;

	}


}
