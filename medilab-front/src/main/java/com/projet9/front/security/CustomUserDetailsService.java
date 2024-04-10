package com.projet9.front.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet9.front.model.CustomUser;
import com.projet9.front.repository.CustomUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private CustomUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CustomUser> user = userRepo.findByUsername(username);
		
		if(user.isPresent()) {
			return new User(user.get().getUsername(), user.get().getPassword(), getGrantedAuthorities("USER"));
		} else {
			throw new UsernameNotFoundException("Invalide email or password");
		}
	}

	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}