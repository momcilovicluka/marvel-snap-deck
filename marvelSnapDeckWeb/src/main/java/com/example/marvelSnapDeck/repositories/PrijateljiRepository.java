package com.example.marvelSnapDeck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Korisnik;
import model.Prijatelji;

public interface PrijateljiRepository extends JpaRepository<Prijatelji, Integer> {

	@Query("SELECT p FROM Prijatelji p WHERE p.korisnik1 = ?1 AND p.korisnik2 = ?2")
	public Prijatelji findByKorisnik1AndKorisnik2(Korisnik k1, Korisnik k2);

}