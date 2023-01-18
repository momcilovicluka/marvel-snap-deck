
package com.example.marvelSnapDeck.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.marvelSnapDeck.repositories.KorisnikRepository;

import model.Korisnik;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private KorisnikRepository korisnikRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik user = korisnikRepository.findByUsername(username);
		CustomUserDetails userDetails = new CustomUserDetails(user);
		return userDetails;
	}

}