package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Deck;
import model.Korisnik;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

	List<Deck> findByKorisnik(Korisnik k);

}