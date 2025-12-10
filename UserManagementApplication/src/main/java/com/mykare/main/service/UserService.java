package com.mykare.main.service;

import java.util.List;

import com.mykare.main.entity.UserEntity;
import com.mykare.main.exception.UserException;

public interface UserService {
	
	public UserEntity registerUser(UserEntity user)throws UserException;
	
	public List<UserEntity> getAllUsers() throws UserException;
	
	public String deleteUserByEmail(String email) throws UserException;
	
	public UserEntity findUserByEmail(String email) throws UserException;

}

