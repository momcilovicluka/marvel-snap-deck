package com.example.marvelSnapDeck.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.repositories.KorisnikRepository;
import com.example.marvelSnapDeck.repositories.UserroleRepository;

import model.Korisnik;
import model.Userrole;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	KorisnikRepository kr;

	@Autowired
	UserroleRepository rr;

	@GetMapping("loginPage")
	public String loginPage() {
		return "login";
	}

	@ModelAttribute
	public void getRoles(Model model) {
		List<Userrole> roles = rr.findAll();
		model.addAttribute("roles", roles);
	}

	@GetMapping("registerUser")
	public String newUser(Model model) {
		Korisnik u = new Korisnik();
		model.addAttribute("user", u);
		return "register";
	}

	@PostMapping("register")
	public String saveUser(@ModelAttribute("user") Korisnik u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));

		Userrole role = rr.findById(u.getUserrole().getIdUserRole()).get();

		u.setUserrole(role);
		role.addKorisnik(u);

		kr.save(u);
		System.out.println("SAVED");
		return "login";
	}
}