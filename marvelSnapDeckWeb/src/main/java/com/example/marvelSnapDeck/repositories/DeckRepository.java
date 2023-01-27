package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Deck;
import model.Karta;
import model.Kategorija;
import model.Korisnik;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

	List<Deck> findByKorisnik(Korisnik k);

	List<Deck> findByKategorija(Kategorija k);

	@Query("SELECT d FROM Deck d inner join d.kartadeckas kd where kd.karta = :karta")
	List<Deck> findByKarta(@Param("karta") Karta karta);

}