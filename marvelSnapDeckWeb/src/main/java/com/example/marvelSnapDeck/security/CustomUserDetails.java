
package com.example.marvelSnapDeck.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.marvelSnapDeck.repositories.KorisnikRepository;

import model.Korisnik;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Korisnik user;

	@Autowired
	KorisnikRepository kr;

	public CustomUserDetails(Korisnik user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserrole().getNaziv()));
		return authorities;
	}

	@Override
	public String getPassword() { // TODO Auto-generated method stub
		if(user == null)
			return null;
		return user.getPassword();
	}

	@Override
	public String getUsername() { // TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() { // TODO Auto-generated
		return true;
	}

	@Override
	public boolean isAccountNonLocked() { // TODO Auto-generated method
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() { // TODO Auto-generated
		return true;
	}

	@Override
	public boolean isEnabled() { // TODO Auto-generated method stub
		return true;
	}
}
