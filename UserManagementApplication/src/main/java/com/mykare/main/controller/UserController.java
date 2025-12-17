package com.mykare.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.mykare.main.entity.UserEntity;
import com.mykare.main.exception.UserException;
import com.mykare.main.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/*Can be accessed by all*/
	@PostMapping("/register")
	public ResponseEntity<UserEntity> registerUser(@Valid @RequestBody UserEntity user) throws UserException {
		
		UserEntity savedUser = userService.registerUser(user);
		
		return new ResponseEntity<UserEntity>(savedUser, HttpStatus.CREATED);
	}
	
	/*Can be access only by ADMIN*/
	@GetMapping("/all")
	public ResponseEntity<List<UserEntity>> getAllUsers() throws UserException{
		
		List<UserEntity> listOfUsers = userService.getAllUsers();
		
		return new ResponseEntity<List<UserEntity>>(listOfUsers, HttpStatus.OK);
	}
	
	/*Can be access only by ADMIN*/
	@DeleteMapping("/{email}")
	public ResponseEntity<String> deleteUserByEmail(@PathVariable("email") String email) throws UserException{
		
		String result = userService.deleteUserByEmail(email);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	/*Accessible both by  USER and ADMIN*/
	@GetMapping("/{email}")
	public ResponseEntity<UserEntity> findUserByEmail(@PathVariable("email") String email) throws UserException{
		
		UserEntity user = userService.findUserByEmail(email);
		
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	/*Accessible both by  USER and ADMIN*/
	@GetMapping("/signin")
	public ResponseEntity<String> getLoggedInUserDetailsHandler(Authentication auth) throws UserException{
		
		UserEntity user = userService.findUserByEmail(auth.getName());
		return new ResponseEntity<String>(user.getName()+" is Logged in successfully", HttpStatus.ACCEPTED);
	}

}



