package com.example.marvelSnapDeck.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.marvelSnapDeck.domain.KartaImage;
import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KategorijaRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;

import model.Karta;
import model.Kategorija;
import model.Tip;
import model.Userrole;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	TipRepository tipRepository;

	@Autowired
	KategorijaRepository kategorijaRepository;

	@Autowired
	KartaRepository kartaRepository;

	@GetMapping("vratiPrazanTip")
	public String prazanTip(Model model) {
		Tip t = new Tip();
		model.addAttribute("TipKarte", t);
		return "admin/dodajTip";
	}

	@PostMapping("dodajTip")
	public String dodajTip(@ModelAttribute("TipKarte") Tip t) {
		tipRepository.save(t);
		System.out.println("SAVED Tip");
		return "index";
	}

	@GetMapping("vratiPraznuKartu")
	public String praznaKarta(Model model) {
		KartaImage ki = new KartaImage();
		model.addAttribute("KartaImage", ki);
		return "admin/dodajKartu";
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

		kartaRepository.save(k);
		System.out.println("SAVED Karta");
		return "index";
	}

	@GetMapping("vratiPraznuKategoriju")
	public String praznaKategorija(Model model) {
		Kategorija k = new Kategorija();
		model.addAttribute("Kategorija", k);
		return "admin/dodajKategoriju";
	}

	@PostMapping("dodajKategoriju")
	public String dodajKategoriju(@ModelAttribute("Kategorija") Kategorija k) {
		kategorijaRepository.save(k);
		System.out.println("SAVED Kategorija");
		return "index";
	}
}
