package com.mykare.main.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mykare.main.entity.UserEntity;
import com.mykare.main.repository.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> opt= userRepository.findByEmail(username);
		
		/*Checking if the user is present with the given email, if not then throw exception*/
		if(opt.isPresent()) {
			UserEntity user = opt.get();
			
			List<GrantedAuthority> authorities= new ArrayList<>();
			SimpleGrantedAuthority sga= new SimpleGrantedAuthority(user.getRole());
			authorities.add(sga);
			
			/*Using the predefined User class which is an implementation of UserDetails interface. However I can created my 
			 * own CustomUserDetails class but the predefined class is very flexible and secure so I used it*/
			return new User(user.getEmail(), user.getPassword(), authorities);
		}else 
			throw new BadCredentialsException("User Details not found with this username: "+username);
	}


}


