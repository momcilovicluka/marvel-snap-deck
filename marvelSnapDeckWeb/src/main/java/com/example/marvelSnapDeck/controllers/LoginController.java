package com.example.marvelSnapDeck.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KorisnikRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;
import com.example.marvelSnapDeck.repositories.UserroleRepository;

import model.Deck;
import model.Karta;
import model.Kartadecka;
import model.Korisnik;
import model.Tip;
import model.Userrole;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {

	@Autowired
	KorisnikRepository korisnikRepository;

	@Autowired
	UserroleRepository UserroleRepository;

	@Autowired
	KartaRepository kartaRepository;

	@Autowired
	TipRepository tipRepository;

	@GetMapping("loginPage")
	public String loginPage() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}

	@GetMapping("index")
	public String index() {
		return "index";
	}

	@ModelAttribute
	public void getRoles(Model model) {
		List<Userrole> roles = UserroleRepository.findAll();
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

		Userrole role = UserroleRepository.findByNaziv("Korisnik");

		u.setUserrole(role);
		role.addKorisnik(u);

		korisnikRepository.save(u);
		System.out.println("SAVED");
		return "login";
	}

	@GetMapping("getEmptyAdmin")
	public String newAdmin(Model model, Principal p) {
		if (p == null)
			return "index";
		Korisnik u = new Korisnik();
		model.addAttribute("user", u);
		return "admin/dodajAdmina";
	}

	@PostMapping("registerAdmin")
	public String saveAdmin(@ModelAttribute("user") Korisnik u) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		u.setPassword(passwordEncoder.encode(u.getPassword()));

		Userrole role = UserroleRepository.findByNaziv("Admin");

		u.setUserrole(role);
		role.addKorisnik(u);

		korisnikRepository.save(u);
		System.out.println("SAVED");
		return "index";
	}

	@ModelAttribute
	public void getTips(Model model) {
		List<Tip> tipovi = tipRepository.findAll();
		model.addAttribute("tipovi", tipovi);
	}

	@GetMapping("sveKarte")
	public String getSveKarte(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		List<Karta> karte = null;

		String naziv = request.getParameter("naziv");
		String tip = request.getParameter("tip");

		naziv = naziv == null ? "" : naziv;
		tip = tip == null ? "" : tip;

		if (naziv.isBlank() && tip.isBlank())
			karte = kartaRepository.findAll();
		else if (naziv.isBlank() && !tip.isBlank()) {
			karte = kartaRepository.findByTip(tipRepository.findByTip(tip));
		} else if (tip.isBlank() && !naziv.isBlank()) {
			karte = kartaRepository.findByNaziv(naziv);
		} else {
			karte = kartaRepository.findByNazivAndTip(naziv, tipRepository.findByTip(tip));
		}

		for (Karta karta : karte) {
			byte[] encodeBase64 = Base64.encodeBase64(karta.getSlika());
			String base64Encoded = new String(encodeBase64, "UTF-8");
			karta.setSlika64(base64Encoded);
		}

		model.addAttribute("karte", karte);
		return "sveKarte";
	}

	@GetMapping("izvestajKarta")
	public void izvestajKarta(Model model, HttpServletRequest request, HttpServletResponse response, Principal p)
			throws JRException, IOException {
		if (p == null || !korisnikRepository.findByUsername(p.getName()).getUserrole().getNaziv().equals("ADMIN"))
			response.sendRedirect("sveKarte");

		Karta karta = kartaRepository.findById(Integer.parseInt(request.getParameter("id"))).get();

		List<Deck> deckovi = new LinkedList<>();
		for (Kartadecka kd : karta.getKartadeckas()) {
			deckovi.add(kd.getDeck());
		}

		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(deckovi);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/kartaUDeckovima.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		inputStream.close();

		Map<String, Object> params = new HashMap<>();
		params.put("naziv", karta.getNaziv());
		params.put("tip", karta.getTip().getTip());
		params.put("opis", karta.getOpis());

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/x-download");
		response.addHeader("Content-disposition",
				"attachment; filename=" + karta.getNaziv().replace(" ", "") + "UDeckovima" + ".pdf");

		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
}