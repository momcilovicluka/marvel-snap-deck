package com.example.marvelSnapDeck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Deck;
import model.Korisnik;
import model.Omiljeni;

public interface OmiljeniRepository extends JpaRepository<Omiljeni, Integer> {

	Omiljeni findByDeckAndKorisnik(Deck d, Korisnik k);

}