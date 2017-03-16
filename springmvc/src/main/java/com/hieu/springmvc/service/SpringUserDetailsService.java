package com.hieu.springmvc.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hieu.springmvc.model.MyUser;
import com.hieu.springmvc.model.MyUserRole;

@Service
public class SpringUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserService userService;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser myUser = userService.findUserByUsername(username);
		System.out.println("User retrieved");
		Collection<MyUserRole> userRoles = myUser.getUserRole();
		System.out.println("User role retrieved");
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MyUserRole userRole : userRoles) {
			System.out.println("User role is: " +userRole.getRole());
			authorities.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
				
		return new User(myUser.getUsername(),myUser.getPassword(),authorities);
	}
	
}
