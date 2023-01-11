package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tip database table.
 * 
 */
@Entity
@NamedQuery(name="Tip.findAll", query="SELECT t FROM Tip t")
public class Tip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTip;

	private String tip;

	//bi-directional many-to-one association to Karta
	@OneToMany(mappedBy="tip")
	private List<Karta> kartas;

	public Tip() {
	}

	public int getIdTip() {
		return this.idTip;
	}

	public void setIdTip(int idTip) {
		this.idTip = idTip;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public List<Karta> getKartas() {
		return this.kartas;
	}

	public void setKartas(List<Karta> kartas) {
		this.kartas = kartas;
	}

	public Karta addKarta(Karta karta) {
		getKartas().add(karta);
		karta.setTip(this);

		return karta;
	}

	public Karta removeKarta(Karta karta) {
		getKartas().remove(karta);
		karta.setTip(null);

		return karta;
	}

}