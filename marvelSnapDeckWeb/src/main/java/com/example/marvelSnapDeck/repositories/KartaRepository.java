package com.example.marvelSnapDeck.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Karta;
import model.Tip;

public interface KartaRepository extends JpaRepository<Karta, Integer> {

	@Query("SELECT k FROM Karta k WHERE k.naziv LIKE CONCAT('%',?1,'%')")
	List<Karta> findByNaziv(String naziv);

	List<Karta> findByTip(Tip tip);

	List<Karta> findByNazivAndTip(String naziv, Tip tip);
}