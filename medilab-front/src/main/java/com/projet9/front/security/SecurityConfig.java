package com.projet9.front.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomUserDetailsService userDetailsServ;

    /**
     * In this function, we defined which pages can be acceded without authentification, how the logout is done, and how to memorize the user on her computer.
     * Here we used the default login page, but it can be customized in this function.
     * @param a HttpSecurity object (http)
     * @return a SecurityFilterChain object
     * @throws Exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/home", "/user/**", "/css/**").permitAll()
			.requestMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form.defaultSuccessUrl("/bidList/list", true))
		.logout((logout) -> logout.logoutUrl("/app-logout")
				.clearAuthentication(true)
				.invalidateHttpSession(true)
				.permitAll())
		.rememberMe((remember) -> remember
				.rememberMeServices(rememberMeServices(userDetailsServ))
			);

		return http.build();
	}
	
	/**
	 * This function choose the way of encryption of the passwords.
	 * @return a PasswordEncoder object
	 */
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	/**
	 * This function define how the remember-me action must be done.
	 * @param a CustomUserDetailsService object (which implement UserDetailsService)
	 * @return a RememberMeServices object
	 */
	@Bean
	RememberMeServices rememberMeServices(CustomUserDetailsService userDetailsService) {
		RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
		TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("superKeySecrete", userDetailsService, encodingAlgorithm);
		rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
		return rememberMe;
	}
	
}
