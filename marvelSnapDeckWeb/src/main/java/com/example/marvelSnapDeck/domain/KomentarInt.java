package com.example.marvelSnapDeck.domain;

import java.io.Serializable;

public class KomentarInt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7170392082678872970L;
	String komentar, idDeck, idKorisnik;

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public String getIdDeck() {
		return idDeck;
	}

	public void setIdDeck(String idDeck) {
		this.idDeck = idDeck;
	}

	public String getIdKorisnik() {
		return idKorisnik;
	}

	public void setIdKorisnik(String idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

}
