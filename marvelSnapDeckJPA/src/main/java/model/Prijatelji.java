package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prijatelji database table.
 * 
 */
@Entity
@NamedQuery(name="Prijatelji.findAll", query="SELECT p FROM Prijatelji p")
public class Prijatelji implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPrijatelji;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="prijatelji1")
	private List<Poruka> porukas1;

	//bi-directional many-to-one association to Poruka
	@OneToMany(mappedBy="prijatelji2")
	private List<Poruka> porukas2;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik1")
	private Korisnik korisnik1;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name="idKorisnik2")
	private Korisnik korisnik2;

	public Prijatelji() {
	}

	public int getIdPrijatelji() {
		return this.idPrijatelji;
	}

	public void setIdPrijatelji(int idPrijatelji) {
		this.idPrijatelji = idPrijatelji;
	}

	public List<Poruka> getPorukas1() {
		return this.porukas1;
	}

	public void setPorukas1(List<Poruka> porukas1) {
		this.porukas1 = porukas1;
	}

	public Poruka addPorukas1(Poruka porukas1) {
		getPorukas1().add(porukas1);
		porukas1.setPrijatelji1(this);

		return porukas1;
	}

	public Poruka removePorukas1(Poruka porukas1) {
		getPorukas1().remove(porukas1);
		porukas1.setPrijatelji1(null);

		return porukas1;
	}

	public List<Poruka> getPorukas2() {
		return this.porukas2;
	}

	public void setPorukas2(List<Poruka> porukas2) {
		this.porukas2 = porukas2;
	}

	public Poruka addPorukas2(Poruka porukas2) {
		getPorukas2().add(porukas2);
		porukas2.setPrijatelji2(this);

		return porukas2;
	}

	public Poruka removePorukas2(Poruka porukas2) {
		getPorukas2().remove(porukas2);
		porukas2.setPrijatelji2(null);

		return porukas2;
	}

	public Korisnik getKorisnik1() {
		return this.korisnik1;
	}

	public void setKorisnik1(Korisnik korisnik1) {
		this.korisnik1 = korisnik1;
	}

	public Korisnik getKorisnik2() {
		return this.korisnik2;
	}

	public void setKorisnik2(Korisnik korisnik2) {
		this.korisnik2 = korisnik2;
	}

}