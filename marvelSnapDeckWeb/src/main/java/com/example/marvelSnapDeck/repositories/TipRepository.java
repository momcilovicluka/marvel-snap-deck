package com.example.marvelSnapDeck.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Tip;

public interface TipRepository extends JpaRepository<Tip, Integer> {

	Tip findByTip(String naziv);

}