package com.mykare.main.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mykare.main.entity.UserEntity;
import com.mykare.main.exception.UserException;
import com.mykare.main.repository.UserRepository;
import com.mykare.main.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserEntity registerUser(UserEntity user) throws UserException {
		
		/*Checking if a registered user is found the the provided email*/
		Optional<UserEntity> opt = userRepository.findByEmail(user.getEmail());
		
		/*If found then throw UserException(custom exception*/
		if(opt.isPresent()) throw new UserException("User already exist with email "+user.getEmail());
		
		/*Checking if password and roles are valid*/
		if(user.getPassword().isEmpty()) throw new UserException("Password should not be empty");
		
		if(user.getRole().equalsIgnoreCase("admin") || user.getRole().equalsIgnoreCase("user")) {
			
			/*Encoding the password before saving it*/
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			/*converting role into the required pattern*/
			user.setRole("ROLE_"+user.getRole().toUpperCase());
			
		}else throw new UserException("Please enter a valid role Admin/User");
		
		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() throws UserException {
		
		List<UserEntity> listOfAllUsers = userRepository.findAll();
		/*Checking if the list is empty, if so then throw exception*/
		if(listOfAllUsers.isEmpty()) throw new UserException("No users found.");
		
		return listOfAllUsers;
	}

	@Override
	public String deleteUserByEmail(String email) throws UserException {
		/*Checking if the user is present with the given email, if not then throw exception*/
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> 
			new UserException("No user found with email :"+email)
		);
		
		userRepository.delete(user);
		return user.getName()+" is deleted successfully.";
	}

	@Override
	public UserEntity findUserByEmail(String email) throws UserException {
			
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() ->
			new UserException("No user found with email: "+email)
		);
		return user;
	}

}
