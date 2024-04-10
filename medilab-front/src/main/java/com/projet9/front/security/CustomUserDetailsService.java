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

import com.nnk.springboot.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;

	/** Override the default loadUserByUsername. Get the user from the Database from her username.
	 * @param the username as a String
	 * @return a UserDetails object
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.nnk.springboot.domain.User> user = userRepo.findByUsername(username);
		
		if(user.isPresent()) {
			return new User(user.get().getUsername(), user.get().getPassword(), getGrantedAuthorities(user.get().getRole()));
		} else {
			throw new UsernameNotFoundException("Invalide email or password");
		}
	}

	/**
	 * This function create the GrantedAuthority object from the role as a String.
	 * @param the role (user/admin) as a String
	 * @return a list with one GrantedAuthority object
	 */
	private List<GrantedAuthority> getGrantedAuthorities(String role) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}
}