package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the poruka database table.
 * 
 */
@Entity
@NamedQuery(name="Poruka.findAll", query="SELECT p FROM Poruka p")
public class Poruka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPoruka;

	private String poruka;

	//bi-directional many-to-one association to Prijatelji
	@ManyToOne
	@JoinColumn(name="idPrijatelj1")
	private Prijatelji prijatelji1;

	//bi-directional many-to-one association to Prijatelji
	@ManyToOne
	@JoinColumn(name="idPrijatelji2")
	private Prijatelji prijatelji2;

	public Poruka() {
	}

	public int getIdPoruka() {
		return this.idPoruka;
	}

	public void setIdPoruka(int idPoruka) {
		this.idPoruka = idPoruka;
	}

	public String getPoruka() {
		return this.poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public Prijatelji getPrijatelji1() {
		return this.prijatelji1;
	}

	public void setPrijatelji1(Prijatelji prijatelji1) {
		this.prijatelji1 = prijatelji1;
	}

	public Prijatelji getPrijatelji2() {
		return this.prijatelji2;
	}

	public void setPrijatelji2(Prijatelji prijatelji2) {
		this.prijatelji2 = prijatelji2;
	}

}