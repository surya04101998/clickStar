package com.clickstar.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.clickstar.models.UserAuthBean;


@Repository
public interface UserBeanRepo extends MongoRepository<UserAuthBean, Long>{
	
public UserAuthBean findByUsername(String username);
public UserAuthBean findByUsernameAndPassword(String username, String password);
}
