package com.example.marvelSnapDeck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.repositories.TipRepository;

import model.Tip;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	TipRepository tr;

	@GetMapping("vratiPrazanTip")
	public String prazanTip(Model model) {
		Tip t = new Tip();
		model.addAttribute("TipKarte", t);
		return "dodajTip";
	}

	@PostMapping("dodajTip")
	public String dodajTip(@ModelAttribute("TipKarte") Tip t) {
		tr.save(t);
		System.out.println("SAVED Tip");
		return "index";
	}
}
