package com.eightbit.books.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eightbit.books.entity.LoginUser;

public class LoginUserDetails implements UserDetails {
	private final LoginUser loginUser;
	private final Collection<? extends GrantedAuthority> authorities;

	public LoginUserDetails(LoginUser loginUser) {
		this.loginUser = loginUser;
//		this.authorities = loginUser.roleList().stream().map(role -> new SimpleGrantedAuthority(role)).toList();
		List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
		list.add(new SimpleGrantedAuthority(loginUser.getRole().getName()));
		this.authorities = list;

	}

	public LoginUser getLoginUser() {
		return loginUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return loginUser.getPassword();
	}

	@Override
	public String getUsername() {
		return loginUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
