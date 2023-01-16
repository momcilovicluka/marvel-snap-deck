package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Deck;
import model.Karta;
import model.Kartadecka;

public interface KartadeckaRepository extends JpaRepository<Kartadecka, Integer> {

	public List<Kartadecka> findByDeck(Deck deck);

	public List<Karta> findKartaByDeck(Deck deck);
}