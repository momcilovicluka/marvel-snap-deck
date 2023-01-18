package com.example.marvelSnapDeck.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KorisnikRepository;
import com.example.marvelSnapDeck.repositories.UserroleRepository;

import model.Karta;
import model.Korisnik;
import model.Userrole;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	KorisnikRepository kr;

	@Autowired
	UserroleRepository rr;

	@Autowired
	KartaRepository kar;

	@GetMapping("loginPage")
	public String loginPage() {
		return "login";
	}

	@GetMapping("index")
	public String index() {
		return "index";
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

		Userrole role = rr.findByNaziv("Korisnik");

		u.setUserrole(role);
		role.addKorisnik(u);

		kr.save(u);
		System.out.println("SAVED");
		return "login";
	}

	@GetMapping("getEmptyAdmin")
	public String newAdmin(Model model) {
		Korisnik u = new Korisnik();
		model.addAttribute("user", u);
		return "admin/dodajAdmina";
	}

	@PostMapping("registerAdmin")
	public String saveAdmin(@ModelAttribute("user") Korisnik u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));

		Userrole role = rr.findByNaziv("Admin");

		u.setUserrole(role);
		role.addKorisnik(u);

		kr.save(u);
		System.out.println("SAVED");
		return "index";
	}

	@GetMapping("sveKarte")
	public String getSveKarte(Model model) throws UnsupportedEncodingException {
		List<Karta> karte = kar.findAll();
		for (Karta karta : karte) {
			byte[] encodeBase64 = Base64.encodeBase64(karta.getSlika());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			karta.setSlika64(base64Encoded);
		}
		model.addAttribute("karte", karte);
		return "sveKarte";
	}
}