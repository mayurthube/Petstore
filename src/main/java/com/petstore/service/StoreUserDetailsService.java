package com.petstore.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.petstore.model.User;
import com.petstore.model.UserRepository;

@Service
public class StoreUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	/*@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		return new User("foo", "foo", new ArrayList<>());
	}*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user =userRepo.findByUserName(username);
		if(user ==null) {
			throw new UsernameNotFoundException("User name "+username+"not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getGrantedAuthority(user));
	}

	private Collection<GrantedAuthority> getGrantedAuthority(User user){
		Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
			grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return grantedAuthority;
		
	}
}
