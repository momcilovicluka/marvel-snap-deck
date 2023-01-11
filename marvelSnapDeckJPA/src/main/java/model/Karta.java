package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the karta database table.
 * 
 */
@Entity
@NamedQuery(name="Karta.findAll", query="SELECT k FROM Karta k")
public class Karta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKarta;

	private String naziv;

	private String opis;

	@Lob
	private byte[] slika;

	//bi-directional many-to-one association to Tip
	@ManyToOne
	@JoinColumn(name="idTip")
	private Tip tip;

	//bi-directional many-to-one association to Kartadecka
	@OneToMany(mappedBy="karta")
	private List<Kartadecka> kartadeckas;

	public Karta() {
	}

	public int getIdKarta() {
		return this.idKarta;
	}

	public void setIdKarta(int idKarta) {
		this.idKarta = idKarta;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public byte[] getSlika() {
		return this.slika;
	}

	public void setSlika(byte[] slika) {
		this.slika = slika;
	}

	public Tip getTip() {
		return this.tip;
	}

	public void setTip(Tip tip) {
		this.tip = tip;
	}

	public List<Kartadecka> getKartadeckas() {
		return this.kartadeckas;
	}

	public void setKartadeckas(List<Kartadecka> kartadeckas) {
		this.kartadeckas = kartadeckas;
	}

	public Kartadecka addKartadecka(Kartadecka kartadecka) {
		getKartadeckas().add(kartadecka);
		kartadecka.setKarta(this);

		return kartadecka;
	}

	public Kartadecka removeKartadecka(Kartadecka kartadecka) {
		getKartadeckas().remove(kartadecka);
		kartadecka.setKarta(null);

		return kartadecka;
	}

}