package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@NamedQuery(name="Kategorija.findAll", query="SELECT k FROM Kategorija k")
public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKategorija;

	private String naziv;

	//bi-directional many-to-one association to Deck
	@OneToMany(mappedBy="kategorija")
	private List<Deck> decks;

	public Kategorija() {
	}

	public int getIdKategorija() {
		return this.idKategorija;
	}

	public void setIdKategorija(int idKategorija) {
		this.idKategorija = idKategorija;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Deck> getDecks() {
		return this.decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}

	public Deck addDeck(Deck deck) {
		getDecks().add(deck);
		deck.setKategorija(this);

		return deck;
	}

	public Deck removeDeck(Deck deck) {
		getDecks().remove(deck);
		deck.setKategorija(null);

		return deck;
	}

}