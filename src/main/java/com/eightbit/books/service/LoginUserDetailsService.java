package com.eightbit.books.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eightbit.books.entity.LoginUser;
import com.eightbit.books.repository.LoginUserRepository;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	private final LoginUserRepository loginUserRepository;

	public LoginUserDetailsService(LoginUserRepository loginUserRepository) {
		this.loginUserRepository = loginUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<LoginUser> loginUserOptional = Optional.of(loginUserRepository.findByEmail(email));
		return loginUserOptional.map(loginUser -> new LoginUserDetails(loginUser))
				.orElseThrow(() -> new UsernameNotFoundException("not found"));
	}
}
