package com.example.marvelSnapDeck.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.domain.KomentarInt;
import com.example.marvelSnapDeck.repositories.DeckRepository;
import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KartadeckaRepository;
import com.example.marvelSnapDeck.repositories.KategorijaRepository;
import com.example.marvelSnapDeck.repositories.KomentarRepository;
import com.example.marvelSnapDeck.repositories.KorisnikRepository;
import com.example.marvelSnapDeck.repositories.OmiljeniRepository;
import com.example.marvelSnapDeck.repositories.PorukaRepository;
import com.example.marvelSnapDeck.repositories.PrijateljiRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;
import com.example.marvelSnapDeck.repositories.UserroleRepository;

import model.Deck;
import model.Karta;
import model.Kartadecka;
import model.Kategorija;
import model.Komentar;
import model.Korisnik;
import model.Omiljeni;
import model.Poruka;
import model.Prijatelji;
import model.Tip;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	TipRepository tipRepository;

	@Autowired
	KartaRepository kartaRepository;

	@Autowired
	DeckRepository deckRepository;

	@Autowired
	KategorijaRepository kategorijaRepository;

	@Autowired
	KartadeckaRepository kartaDeckaRepository;

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	KomentarRepository komentarRepository;

	@Autowired
	UserroleRepository UserroleRepository;

	@Autowired
	PrijateljiRepository prijateljiRepository;

	@Autowired
	OmiljeniRepository omiljeniRepository;

	@Autowired
	PorukaRepository porukaRepository;

	@GetMapping("index")
	public String index() {
		return "index";
	}

	@ModelAttribute
	public void getTips(Model model) {
		List<Tip> tipovi = tipRepository.findAll();
		model.addAttribute("tipovi", tipovi);
	}

	public static void spremiSlike(List<Karta> karte) throws UnsupportedEncodingException {
		for (Karta karta : karte) {
			byte[] encodeBase64 = Base64.encodeBase64(karta.getSlika());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			karta.setSlika64(base64Encoded);
		}
	}

	@ModelAttribute
	public void getKategorije(Model model) {
		List<Kategorija> kategorije = kategorijaRepository.findAll();
		model.addAttribute("kategorije", kategorije);
	}

	@GetMapping("vratiPrazanDeck")
	public String prazanDeck(Model model, Principal p) {
		if (p == null)
			return "index";
		Deck d = new Deck();
		model.addAttribute("Deck", d);
		return "user/dodajDeck";
	}

	@PostMapping("dodajDeck")
	public String dodajDeck(@ModelAttribute("Deck") Deck d, Principal p) {
		if (p == null)
			return "index";
		d.setKorisnik(korisnikRepository.findByUsername(p.getName()));
		d.setDatum(new Date());
		deckRepository.save(d);
		System.out.println("SAVED Deck");
		return index();
	}

	@ModelAttribute
	public void getDeckovi(Model model) {
		List<Deck> deckovi = deckRepository.findAll();
		model.addAttribute("deckovi", deckovi);
	}

	@ModelAttribute
	public String getDeckoviKorisnika(Model model, Principal p) {
		if (p == null)
			return "index";
		List<Deck> deckoviKorisnika = deckRepository.findByKorisnik(korisnikRepository.findByUsername(p.getName()));
		model.addAttribute("deckoviKorisnika", deckoviKorisnika);
		return "";
	}

	@ModelAttribute
	public void getKarte(Model model) {
		List<Karta> karte = kartaRepository.findAll();
		karte.sort((k1, k2) -> k1.getNaziv().compareToIgnoreCase(k2.getNaziv()));
		model.addAttribute("karte", karte);
	}

	@GetMapping("vratiPraznuKartadecka")
	public String praznaKartadecka(Model model, Principal p) {
		if (p == null)
			return "index";
		Kartadecka kd = new Kartadecka();
		model.addAttribute("Kartadecka", kd);
		return "user/dodajKartuUDeck";
	}

	@PostMapping("dodajKartuUDeck")
	public String dodajKartuUDeck(Model model, @ModelAttribute("Kartadecka") Kartadecka kd, Principal p) {
		if (p == null)
			return "index";

		String message = "";
		List<Kartadecka> kartedecka = kartaDeckaRepository.findByDeck(kd.getDeck());
		int n = kartedecka.size();
		if (n > 11)
			message += "Deck je pun";

		if (kartedecka.parallelStream().filter(k -> k.getKarta().equals(kd.getKarta())).count() > 0)
			message += " Karta je vec u decku";
		System.out.println(message);
		if (!message.isEmpty())
			return "user/dodajKartuUDeck";

		kartaDeckaRepository.save(kd);
		System.out.println("SAVED Kartadecka");
		return praznaKartadecka(model, p);
	}

	@GetMapping("sviDeckovi")
	public String getSviDeckovi(Model model, Principal p, HttpServletRequest request) {
		if (p == null)
			return "index";
		if (request.getParameter("kategorija") != null && !request.getParameter("kategorija").isBlank()) {
			Kategorija k = kategorijaRepository.findById(Integer.parseInt(request.getParameter("kategorija"))).get();
			List<Deck> deckovi = deckRepository.findByKategorija(k);
			model.addAttribute("deckovi", deckovi);
		} else if (request.getParameter("id") != null && !request.getParameter("id").isBlank()) {
			Karta karta = kartaRepository.findById(Integer.parseInt(request.getParameter("id"))).get();
			List<Deck> deckovi = deckRepository.findByKarta(karta);
			model.addAttribute("deckovi", deckovi);
		}
		model.addAttribute("korisnik", korisnikRepository.findByUsername(p.getName()));
		return "user/sviDeckovi";
	}

	@GetMapping("vratiDeck")
	public String getInfoODecku(Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		if (p == null)
			return "index";
		Deck deck = deckRepository.findById(Integer.parseInt(request.getParameter("idDeck"))).get();
		List<Karta> karte = kartaDeckaRepository.findKarteByDeckId(deck.getIdDeck());
		spremiSlike(karte);
		model.addAttribute("karte", karte);
		model.addAttribute("deck", deck);
		KomentarInt k = new KomentarInt();
		model.addAttribute("komentar", k);
		Korisnik korisnik = korisnikRepository.findByUsername(p.getName());
		model.addAttribute("korisnik", korisnik);
		return "user/deckInfo";
	}

	@PostMapping("dodajKomentar")
	public String dodajKomentar(@ModelAttribute KomentarInt ki, Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		if (p == null)
			return "index";
		Komentar k = new Komentar();
		k.setKomentar(ki.getKomentar());
		k.setKorisnik(korisnikRepository.findById(Integer.parseInt(ki.getIdKorisnik())).get());
		k.setDeck(deckRepository.findById(Integer.parseInt(ki.getIdDeck())).get());
		komentarRepository.save(k);
		return getInfoODecku(model, request, p);
	}

	@GetMapping("sviKorisnici")
	public String getsviKorisnici(Model model, Principal p) {
		if (p == null)
			return "index";
		model.addAttribute("korisnik", korisnikRepository.findByUsername(p.getName()));
		return "user/sviKorisnici";
	}

	@ModelAttribute
	public String getKorisnici(Model model, Principal p) {
		if (p == null)
			return "index";
		List<Korisnik> korisnici = korisnikRepository.findByUserrole(UserroleRepository.findByNaziv("KORISNIK"));
		korisnici.removeIf(k -> k.getUsername().equals(p.getName()));
		model.addAttribute("korisnici", korisnici);
		return "";
	}

	@GetMapping("dodajPrijatelja")
	public String dodajPrijatelja(Model model, HttpServletRequest request, Principal p) {
		if (p == null)
			return "index";
		Prijatelji prijatelji = new Prijatelji();
		prijatelji.setKorisnik1(korisnikRepository.findByUsername(p.getName()));
		prijatelji.setKorisnik2(korisnikRepository.findById(Integer.parseInt(request.getParameter("id"))).get());
		prijateljiRepository.save(prijatelji);
		getKorisnici(model, p);
		return getsviKorisnici(model, p);
	}

	@GetMapping("dodajOmiljeni")
	public String dodajOmiljeni(Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		if (p == null)
			return "index";
		Deck d = deckRepository.findById(Integer.parseInt(request.getParameter("idDeck"))).get();
		Korisnik k = korisnikRepository.findByUsername(p.getName());
		Omiljeni o = new Omiljeni();
		o.setDeck(d);
		o.setKorisnik(k);
		omiljeniRepository.save(o);
		System.out.println("sacuvan omiljeni");
		return getInfoODecku(model, request, p);
	}

	@GetMapping("ukloniOmiljeni")
	public String ukloniOmiljeni(Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		if (p == null)
			return "index";
		Deck d = deckRepository.findById(Integer.parseInt(request.getParameter("idDeck"))).get();
		Korisnik k = korisnikRepository.findByUsername(p.getName());
		Omiljeni o = omiljeniRepository.findByDeckAndKorisnik(d, k);
		omiljeniRepository.delete(o);
		System.out.println("obrisan omiljeni");
		return getInfoODecku(model, request, p);
	}

	@GetMapping("vratiPoruke")
	public String vratiPoruke(Model model, HttpServletRequest request, Principal p) {
		if (p == null)
			return "index";
		Korisnik primalac;
		if (request.getParameter("id") != null)
			primalac = korisnikRepository.findById(Integer.parseInt(request.getParameter("id"))).get();
		else
			primalac = (Korisnik) request.getSession().getAttribute("primalac");

		Korisnik posiljaoc = korisnikRepository.findByUsername(p.getName());

		List<Poruka> poruke = prijateljiRepository.findByKorisnik1AndKorisnik2(posiljaoc, primalac).getPorukas1();
		poruke.addAll(prijateljiRepository.findByKorisnik1AndKorisnik2(posiljaoc, primalac).getPorukas2());

		poruke.sort((p1, p2) -> p1.getDatum().compareTo(p2.getDatum()));

		model.addAttribute("poruke", poruke);
		model.addAttribute("posiljaoc", posiljaoc);
		model.addAttribute("primalac", primalac);
		request.getSession().setAttribute("posiljaoc", posiljaoc);
		request.getSession().setAttribute("primalac", primalac);

		Poruka porukaPrazna = new Poruka();
		model.addAttribute("porukaPrazna", porukaPrazna);

		return "user/posaljiPoruku";
	}

	@PostMapping("posaljiPoruku")
	public String posaljiPoruku(@ModelAttribute("porukaPrazna") Poruka por, Model model, HttpServletRequest request,
			Principal p) {
		if (p == null)
			return "index";
		por.setPoruka(request.getParameter("poruka"));
		por.setDatum(Date.from(Instant.now()));

		Korisnik posiljaoc = (Korisnik) request.getSession().getAttribute("posiljaoc");
		Korisnik primalac = (Korisnik) request.getSession().getAttribute("primalac");

		Prijatelji prijatelji1 = posiljaoc.getPrijateljis1().stream()
				.filter(p1 -> p1.getKorisnik2().getIdKorisnik() == primalac.getIdKorisnik()).findFirst().get();
		Prijatelji prijatelji2 = prijateljiRepository.findByKorisnik1AndKorisnik2(primalac, posiljaoc);

		por.setPrijatelji1(prijatelji1);
		por.setPrijatelji2(prijatelji2);

		porukaRepository.save(por);

		return vratiPoruke(model, request, p);
	}
}
