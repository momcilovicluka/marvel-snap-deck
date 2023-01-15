package com.example.marvelSnapDeck.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import model.Tip;

public class KartaImage implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idKarta;
	private String naziv;
	private String opis;
	private MultipartFile slika;
	Tip tip;

	public int getIdKarta() {
		return idKarta;
	}

	public void setIdKarta(int idKarta) {
		this.idKarta = idKarta;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public MultipartFile getSlika() {
		return slika;
	}

	public void setSlika(MultipartFile slika) {
		this.slika = slika;
	}

	public Tip getTip() {
		return tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}