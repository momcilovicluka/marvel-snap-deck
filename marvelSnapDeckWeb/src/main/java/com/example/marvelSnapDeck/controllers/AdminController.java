package com.example.marvelSnapDeck.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.repositories.KategorijaRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;

import model.Kategorija;
import model.Tip;
import model.Userrole;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	TipRepository tr;
	
	@Autowired
	KategorijaRepository kr;

	@GetMapping("vratiPrazanTip")
	public String prazanTip(Model model) {
		Tip t = new Tip();
		model.addAttribute("TipKarte", t);
		return "admin/dodajTip";
	}

	@PostMapping("dodajTip")
	public String dodajTip(@ModelAttribute("TipKarte") Tip t) {
		tr.save(t);
		System.out.println("SAVED Tip");
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
		kr.save(k);
		System.out.println("SAVED Kategorija");
		return "index";
	}
}
