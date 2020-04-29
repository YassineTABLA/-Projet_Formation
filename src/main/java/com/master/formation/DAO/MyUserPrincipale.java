package com.master.formation.DAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.master.formation.beans.User;

public class MyUserPrincipale implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public MyUserPrincipale(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();

		GrantedAuthority aut = new SimpleGrantedAuthority("ROLE_" + user.getRole());
		auth.add(aut);
		return auth;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	public String getEmail() {
		return this.user.getEmail();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
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
