package com.example.marvelSnapDeck.controllers;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.Principal;
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
import org.springframework.web.multipart.MultipartFile;

import com.example.marvelSnapDeck.domain.KartaImage;
import com.example.marvelSnapDeck.domain.KomentarInt;
import com.example.marvelSnapDeck.repositories.DeckRepository;
import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KartadeckaRepository;
import com.example.marvelSnapDeck.repositories.KategorijaRepository;
import com.example.marvelSnapDeck.repositories.KomentarRepository;
import com.example.marvelSnapDeck.repositories.KorisnikRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;

import model.Deck;
import model.Karta;
import model.Kartadecka;
import model.Kategorija;
import model.Komentar;
import model.Korisnik;
import model.Tip;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	TipRepository tr;

	@Autowired
	KartaRepository kr;

	@Autowired
	DeckRepository dr;

	@Autowired
	KategorijaRepository kar;

	@Autowired
	KartadeckaRepository kdr;

	@Autowired
	KorisnikRepository kor;

	@Autowired
	KomentarRepository komr;

	@ModelAttribute
	public void getTips(Model model) {
		List<Tip> tipovi = tr.findAll();
		model.addAttribute("tipovi", tipovi);
	}

	public static void spremiSlike(List<Karta> karte) throws UnsupportedEncodingException {
		for (Karta karta : karte) {
			byte[] encodeBase64 = Base64.encodeBase64(karta.getSlika());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			karta.setSlika64(base64Encoded);
		}
	}

	@GetMapping("vratiPraznuKartu")
	public String praznaKarta(Model model) {
		KartaImage ki = new KartaImage();
		model.addAttribute("KartaImage", ki);
		return "user/dodajKartu";
	}

	@PostMapping("dodajKartu")
	public String dodajKartu(@ModelAttribute("Karta") KartaImage ki) throws IOException {
		Karta k = new Karta();
		k.setIdKarta(ki.getIdKarta());
		k.setNaziv(ki.getNaziv());
		k.setOpis(ki.getOpis());
		k.setTip(ki.getTip());

		MultipartFile file = ki.getSlika();
		String fileName = file.getOriginalFilename();
		String filePath;
		filePath = System.getProperty("user.dir");
		System.out.println("Putanja je " + filePath);
		File imageFile = new File(filePath + "/res/images", fileName);
		file.transferTo(imageFile);
		k.setSlika(Files.readAllBytes(imageFile.toPath()));

		kr.save(k);
		System.out.println("SAVED Karta");
		return "index";
	}

	@ModelAttribute
	public void getKategorije(Model model) {
		List<Kategorija> kategorije = kar.findAll();
		model.addAttribute("kategorije", kategorije);
	}

	@GetMapping("vratiPrazanDeck")
	public String prazanDeck(Model model) {
		Deck d = new Deck();
		model.addAttribute("Deck", d);
		return "user/dodajDeck";
	}

	@PostMapping("dodajDeck")
	public String dodajDeck(@ModelAttribute("Deck") Deck d) {
		dr.save(d);
		System.out.println("SAVED Deck");
		return "index";
	}

	@ModelAttribute
	public void getDeckovi(Model model) {
		List<Deck> deckovi = dr.findAll();
		model.addAttribute("deckovi", deckovi);
	}

	@ModelAttribute
	public void getKarte(Model model) {
		List<Karta> karte = kr.findAll();
		model.addAttribute("karte", karte);
	}

	@GetMapping("vratiPraznuKartadecka")
	public String praznaKartadecka(Model model) {
		Kartadecka kd = new Kartadecka();
		model.addAttribute("Kartadecka", kd);
		return "user/dodajKartuUDeck";
	}

	@PostMapping("dodajKartuUDeck")
	public String dodajKartuUDeck(@ModelAttribute("Kartadecka") Kartadecka kd) {
		String message = "";

		List<Kartadecka> kartedecka = kdr.findByDeck(kd.getDeck());
		int n = kartedecka.size();
		if (n > 11)
			message += "Deck je pun";

		if (kartedecka.parallelStream().filter(k -> k.getKarta().equals(kd.getKarta())).count() > 0)
			message += " Karta je vec u decku";
		System.out.println(message);
		if (!message.isEmpty())
			return "user/dodajKartuUDeck";

		kdr.save(kd);
		System.out.println("SAVED Kartadecka");
		return "index";
	}

	@GetMapping("sviDeckovi")
	public String getSviDeckovi(Model model) {
		return "user/sviDeckovi";
	}

	@GetMapping("vratiDeck")
	public String getInfoODecku(Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		Deck deck = dr.findById(Integer.parseInt(request.getParameter("idDeck"))).get();
		List<Karta> karte = kdr.findKarteByDeckId(deck.getIdDeck());
		spremiSlike(karte);
		model.addAttribute("karte", karte);
		model.addAttribute("deck", deck);
		KomentarInt k = new KomentarInt();
		model.addAttribute("komentar", k);
		Korisnik korisnik = kor.findByUsername(p.getName());
		model.addAttribute("korisnik", korisnik);
		return "user/deckInfo";
	}

	@PostMapping("dodajKomentar")
	public String dodajKomentar(@ModelAttribute KomentarInt ki, Model model, HttpServletRequest request, Principal p)
			throws UnsupportedEncodingException {
		Komentar k = new Komentar();
		k.setKomentar(ki.getKomentar());
		k.setKorisnik(kor.findById(Integer.parseInt(ki.getIdKorisnik())).get());
		k.setDeck(dr.findById(Integer.parseInt(ki.getIdDeck())).get());
		komr.save(k);
		return getInfoODecku(model, request, p);
	}
}
