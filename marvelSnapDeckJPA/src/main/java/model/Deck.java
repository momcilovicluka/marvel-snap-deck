package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the deck database table.
 * 
 */
@Entity
@NamedQuery(name="Deck.findAll", query="SELECT d FROM Deck d")
public class Deck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDeck;

	private String opis;

	//bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name="idKategorija")
	private Kategorija kategorija;

	//bi-directional many-to-one association to Kartadecka
	@OneToMany(mappedBy="deck")
	private List<Kartadecka> kartadeckas;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="deck")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Omiljeni
	@OneToMany(mappedBy="deck")
	private List<Omiljeni> omiljenis;

	public Deck() {
	}

	public int getIdDeck() {
		return this.idDeck;
	}

	public void setIdDeck(int idDeck) {
		this.idDeck = idDeck;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public List<Kartadecka> getKartadeckas() {
		return this.kartadeckas;
	}

	public void setKartadeckas(List<Kartadecka> kartadeckas) {
		this.kartadeckas = kartadeckas;
	}

	public Kartadecka addKartadecka(Kartadecka kartadecka) {
		getKartadeckas().add(kartadecka);
		kartadecka.setDeck(this);

		return kartadecka;
	}

	public Kartadecka removeKartadecka(Kartadecka kartadecka) {
		getKartadeckas().remove(kartadecka);
		kartadecka.setDeck(null);

		return kartadecka;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setDeck(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setDeck(null);

		return komentar;
	}

	public List<Omiljeni> getOmiljenis() {
		return this.omiljenis;
	}

	public void setOmiljenis(List<Omiljeni> omiljenis) {
		this.omiljenis = omiljenis;
	}

	public Omiljeni addOmiljeni(Omiljeni omiljeni) {
		getOmiljenis().add(omiljeni);
		omiljeni.setDeck(this);

		return omiljeni;
	}

	public Omiljeni removeOmiljeni(Omiljeni omiljeni) {
		getOmiljenis().remove(omiljeni);
		omiljeni.setDeck(null);

		return omiljeni;
	}

}