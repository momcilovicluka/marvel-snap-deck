package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	private String ime;

	private String password;

	private String prezime;

	private String username;

	//bi-directional many-to-one association to Deck
	@OneToMany(mappedBy="korisnik")
	private List<Deck> decks;

	//bi-directional many-to-one association to Komentar
	@OneToMany(mappedBy="korisnik")
	private List<Komentar> komentars;

	//bi-directional many-to-one association to Userrole
	@ManyToOne
	@JoinColumn(name="idUserRole")
	private Userrole userrole;

	//bi-directional many-to-one association to Omiljeni
	@OneToMany(mappedBy="korisnik")
	private List<Omiljeni> omiljenis;

	//bi-directional many-to-one association to Prijatelji
	@OneToMany(mappedBy="korisnik1")
	private List<Prijatelji> prijateljis1;

	//bi-directional many-to-one association to Prijatelji
	@OneToMany(mappedBy="korisnik2")
	private List<Prijatelji> prijateljis2;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Deck> getDecks() {
		return this.decks;
	}

	public void setDecks(List<Deck> decks) {
		this.decks = decks;
	}

	public Deck addDeck(Deck deck) {
		getDecks().add(deck);
		deck.setKorisnik(this);

		return deck;
	}

	public Deck removeDeck(Deck deck) {
		getDecks().remove(deck);
		deck.setKorisnik(null);

		return deck;
	}

	public List<Komentar> getKomentars() {
		return this.komentars;
	}

	public void setKomentars(List<Komentar> komentars) {
		this.komentars = komentars;
	}

	public Komentar addKomentar(Komentar komentar) {
		getKomentars().add(komentar);
		komentar.setKorisnik(this);

		return komentar;
	}

	public Komentar removeKomentar(Komentar komentar) {
		getKomentars().remove(komentar);
		komentar.setKorisnik(null);

		return komentar;
	}

	public Userrole getUserrole() {
		return this.userrole;
	}

	public void setUserrole(Userrole userrole) {
		this.userrole = userrole;
	}

	public List<Omiljeni> getOmiljenis() {
		return this.omiljenis;
	}

	public void setOmiljenis(List<Omiljeni> omiljenis) {
		this.omiljenis = omiljenis;
	}

	public Omiljeni addOmiljeni(Omiljeni omiljeni) {
		getOmiljenis().add(omiljeni);
		omiljeni.setKorisnik(this);

		return omiljeni;
	}

	public Omiljeni removeOmiljeni(Omiljeni omiljeni) {
		getOmiljenis().remove(omiljeni);
		omiljeni.setKorisnik(null);

		return omiljeni;
	}

	public List<Prijatelji> getPrijateljis1() {
		return this.prijateljis1;
	}

	public void setPrijateljis1(List<Prijatelji> prijateljis1) {
		this.prijateljis1 = prijateljis1;
	}

	public Prijatelji addPrijateljis1(Prijatelji prijateljis1) {
		getPrijateljis1().add(prijateljis1);
		prijateljis1.setKorisnik1(this);

		return prijateljis1;
	}

	public Prijatelji removePrijateljis1(Prijatelji prijateljis1) {
		getPrijateljis1().remove(prijateljis1);
		prijateljis1.setKorisnik1(null);

		return prijateljis1;
	}

	public List<Prijatelji> getPrijateljis2() {
		return this.prijateljis2;
	}

	public void setPrijateljis2(List<Prijatelji> prijateljis2) {
		this.prijateljis2 = prijateljis2;
	}

	public Prijatelji addPrijateljis2(Prijatelji prijateljis2) {
		getPrijateljis2().add(prijateljis2);
		prijateljis2.setKorisnik2(this);

		return prijateljis2;
	}

	public Prijatelji removePrijateljis2(Prijatelji prijateljis2) {
		getPrijateljis2().remove(prijateljis2);
		prijateljis2.setKorisnik2(null);

		return prijateljis2;
	}

}