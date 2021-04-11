package com.din.pridesys.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.din.pridesys.dto.PrideUserDto;
import com.din.pridesys.model.PrideUser;
import com.din.pridesys.repository.PrideUserRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired
	private PrideUserRepo prideUserRepo;
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		PrideUser prideUser = prideUserRepo.findByUsername(username);
		if (prideUser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(prideUser.getUsername(), prideUser.getPassword(),
				new ArrayList<>());
		
//		if ("javainuse".equals(username)) { //
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		} 
		
	}
	
	

	public PrideUser save(PrideUserDto prideUserDto) {
		PrideUser prideUser = new PrideUser();
		prideUser.setUsername(prideUserDto.getUsername());
		prideUser.setPassword(bcryptEncoder.encode(prideUserDto.getPassword()));
		return prideUserRepo.save(prideUser);
	}

}
