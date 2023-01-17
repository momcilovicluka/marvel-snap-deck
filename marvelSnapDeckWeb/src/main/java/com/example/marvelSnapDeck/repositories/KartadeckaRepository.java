package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Deck;
import model.Karta;
import model.Kartadecka;

public interface KartadeckaRepository extends JpaRepository<Kartadecka, Integer> {

	public List<Kartadecka> findByDeck(Deck deck);

	@Query("SELECT k FROM Karta k WHERE k.id IN (SELECT kd.karta.id FROM Kartadecka kd WHERE kd.deck.id = :deckId)")
	public List<Karta> findKarteByDeckId(@Param("deckId") int deckId);
}