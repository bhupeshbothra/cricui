package com.stackroute.favouriteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.favouriteservice.domain.User;

public interface UserCricPlayerRepository extends MongoRepository<User, String> {

	public User findByUsername(String username);
}
