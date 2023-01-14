package com.example.marvelSnapDeck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Deck;

public interface DeckRepository extends JpaRepository<Deck, Integer> {

}
