package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the omiljeni database table.
 * 
 */
@Entity
@NamedQuery(name="Omiljeni.findAll", query="SELECT o FROM Omiljeni o")
public class Omiljeni implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOmiljeni;

	//bi-directional many-to-one association to Deck
	@ManyToOne
	@JoinColumn(name="idDeck")
	private Deck deck;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik")
	private Korisnik korisnik;

	public Omiljeni() {
	}

	public int getIdOmiljeni() {
		return this.idOmiljeni;
	}

	public void setIdOmiljeni(int idOmiljeni) {
		this.idOmiljeni = idOmiljeni;
	}

	public Deck getDeck() {
		return this.deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}