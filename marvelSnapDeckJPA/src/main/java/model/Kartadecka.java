package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the kartadecka database table.
 * 
 */
@Entity
@NamedQuery(name="Kartadecka.findAll", query="SELECT k FROM Kartadecka k")
public class Kartadecka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKartaDecka;

	//bi-directional many-to-one association to Deck
	@ManyToOne
	@JoinColumn(name="idDeck")
	private Deck deck;

	//bi-directional many-to-one association to Karta
	@ManyToOne
	@JoinColumn(name="idKarta")
	private Karta karta;

	public Kartadecka() {
	}

	public int getIdKartaDecka() {
		return this.idKartaDecka;
	}

	public void setIdKartaDecka(int idKartaDecka) {
		this.idKartaDecka = idKartaDecka;
	}

	public Deck getDeck() {
		return this.deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Karta getKarta() {
		return this.karta;
	}

	public void setKarta(Karta karta) {
		this.karta = karta;
	}

}