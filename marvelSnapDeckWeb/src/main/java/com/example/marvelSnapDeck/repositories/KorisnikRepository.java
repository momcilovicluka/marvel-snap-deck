package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;
import model.Userrole;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

	Korisnik findByUsername(String username);

	List<Korisnik> findByUserrole(Userrole userrole);
}
