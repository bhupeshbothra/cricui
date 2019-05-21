package com.stackroute.favouriteservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.config.Producer;
import com.stackroute.favouriteservice.domain.CricPlayer;
import com.stackroute.favouriteservice.domain.User;
import com.stackroute.favouriteservice.exception.PlayerAlreadyExistsException;
import com.stackroute.favouriteservice.exception.PlayerNotFoundException;
import com.stackroute.favouriteservice.rabittmq.domain.CricDTO;
import com.stackroute.favouriteservice.repository.UserCricPlayerRepository;

@Service
public class UserCricPlayerServiceImpl implements UserCricPlayerService {

	private UserCricPlayerRepository userCricPlayerRepository;
	public Producer producer;

	

	 
	@Autowired
	public UserCricPlayerServiceImpl(UserCricPlayerRepository userCricPlayerRepository ,Producer producer) {
		super();
		this.userCricPlayerRepository = userCricPlayerRepository;
		this.producer =producer;
	}

	@Override
	public User saveUserPlayerToFavoriteList(CricPlayer player, String username) throws PlayerAlreadyExistsException {
		// TODO Auto-generated method stub
		System.out.println("username" + username);
		User fetchedUserObj = userCricPlayerRepository.findByUsername(username);

		if (fetchedUserObj == null) {
			fetchedUserObj = new User(username, new ArrayList<CricPlayer>());
		}
		

		List<CricPlayer> playerList = fetchedUserObj.getPlayerList();

		if (playerList != null) {
			
			System.out.println("If Block");
			
			for (CricPlayer t : playerList) {

				if (t.getPid().equals(player.getPid())) {
					throw new PlayerAlreadyExistsException();
				}
			}

			playerList.add(player);
			
			System.out.println("Saving Data if block");
			fetchedUserObj.setPlayerList(playerList);

			 CricDTO cricdto = new CricDTO(); 
			 cricdto.setFullName(player.getFullName());
			 cricdto.setName(player.getName());
			 cricdto.setPid(player.getPid());
		
		
			 

			userCricPlayerRepository.save(fetchedUserObj);

			producer.sendAddFaviroteToMQ(cricdto);
			//rabbitMQSender.send(cricdto);

		}

		else {
			System.out.println(" else block");
			playerList = new ArrayList();
			playerList.add(player);

			fetchedUserObj.setPlayerList(playerList);

			 CricDTO cricdto = new CricDTO(); 
			 cricdto.setFullName(player.getFullName());
			 cricdto.setName(player.getName());
			 cricdto.setPid(player.getPid());
			 
			System.out.println("Saving Data if block" + fetchedUserObj.toString());
			userCricPlayerRepository.save(fetchedUserObj);
			producer.sendAddFaviroteToMQ(cricdto);

		}
		return fetchedUserObj;

	}

	@Override
	public User deleteUserPlayerToFavoriteList(String pId, String username) throws PlayerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("username" + username);
		User fetchedUserObj = userCricPlayerRepository.findByUsername(username);
		if (fetchedUserObj != null) {
			System.out.println("deleteUserPlayerToFavoriteList User" + fetchedUserObj.toString());
		}
		boolean trackFound = false;
		int indexnum = 0;
		List<CricPlayer> playerList = fetchedUserObj.getPlayerList();

		if (playerList != null && playerList.size() > 0) {
			for (CricPlayer t : playerList) {
				indexnum++;
				if (t.getPid().equals(pId)) {
					playerList.remove(indexnum - 1);
					fetchedUserObj.setPlayerList(playerList);
					userCricPlayerRepository.save(fetchedUserObj);
					
					 CricDTO cricdto = new CricDTO(); 
					 cricdto.setFullName(t.getFullName());
					 cricdto.setName(t.getName());
					 cricdto.setPid(t.getPid());
					System.out.println("Saving Data if block" + fetchedUserObj.toString());
					producer.sendDeleteFaviroteToMQ(cricdto);
					break;
				}
			}

		}

		else {
			throw new PlayerNotFoundException();
		}
		return fetchedUserObj;
	}

	@Override
	public List<CricPlayer> getAllUserPlayerList(String username) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("username" + username);
		User fetchedUserObj = userCricPlayerRepository.findByUsername(username);

		if (fetchedUserObj != null) {
			System.out.println("getAllUserPlayerList User" + fetchedUserObj.toString());
		}

		return fetchedUserObj.getPlayerList();
	}

//	@Override
//	public User registerUser(User user) throws UserAlreadyExistsException {
//		// TODO Auto-generated method stub
//		User fetchedUserObj = userCricPlayerRepository.findByUsername(user.getUsername());
//
//		if (fetchedUserObj != null) {
//
//			System.out.println("Register User" + fetchedUserObj.toString());
//			throw new UserAlreadyExistsException();
//		} else {
//			userCricPlayerRepository.save(user);
//			// producer.sendMessageToMQ(userDto);
//		}
//		return user;
//	}

}
