package com.mykare.main.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain securityFilters(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(auth -> {
			auth
			.requestMatchers(HttpMethod.POST, "/user/register").permitAll()
			.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
			.requestMatchers(HttpMethod.GET, "/user/all").hasRole("ADMIN")
			.requestMatchers(HttpMethod.DELETE , "/user/**").hasRole("ADMIN")
			.requestMatchers(HttpMethod.GET, "/user/**", "/user/signin").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated();
		})
		.csrf(csrf -> csrf.disable())
		.formLogin(Customizer.withDefaults())
		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}

}
