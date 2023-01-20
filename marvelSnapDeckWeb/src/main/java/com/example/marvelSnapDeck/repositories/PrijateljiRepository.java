package com.example.marvelSnapDeck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;
import model.Prijatelji;

public interface PrijateljiRepository extends JpaRepository<Prijatelji, Integer> {

	public Prijatelji findByKorisnik1AndKorisnik2(Korisnik k1, Korisnik k2);

}