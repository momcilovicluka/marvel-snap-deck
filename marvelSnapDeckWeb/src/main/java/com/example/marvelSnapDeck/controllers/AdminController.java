package com.example.marvelSnapDeck.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.marvelSnapDeck.domain.KartaImage;
import com.example.marvelSnapDeck.repositories.DeckRepository;
import com.example.marvelSnapDeck.repositories.KartaRepository;
import com.example.marvelSnapDeck.repositories.KategorijaRepository;
import com.example.marvelSnapDeck.repositories.TipRepository;

import model.Deck;
import model.Karta;
import model.Kategorija;
import model.Tip;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	TipRepository tipRepository;

	@Autowired
	KategorijaRepository kategorijaRepository;

	@Autowired
	KartaRepository kartaRepository;

	@Autowired
	DeckRepository deckRepository;

	@GetMapping("vratiPrazanTip")
	public String prazanTip(Model model, Principal p) {
		if (p == null)
			return "index";
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

	@ModelAttribute
	public void getTips(Model model) {
		List<Tip> tipovi = tipRepository.findAll();
		model.addAttribute("tipovi", tipovi);
	}

	@GetMapping("vratiPraznuKartu")
	public String praznaKarta(Model model, Principal p) {
		if (p == null)
			return "index";
		KartaImage ki = new KartaImage();
		model.addAttribute("KartaImage", ki);
		return "admin/dodajKartu";
	}

	@PostMapping("dodajKartu")
	public String dodajKartu(Model model, @ModelAttribute("Karta") KartaImage ki, Principal p) throws IOException {
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
		return praznaKarta(model, p);
	}

	@GetMapping("vratiPraznuKategoriju")
	public String praznaKategorija(Model model, Principal p) {
		if (p == null)
			return "index";
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

	@GetMapping("statistika")
	public String statistikaMain(Model m, Principal p) {
		if (p == null)
			return "index";
		List<Kategorija> kategorije = kategorijaRepository.findAll();
		m.addAttribute("kategorije", kategorije);
		return "admin/statistika";
	}

	@GetMapping("brojDeckovaKategorija")
	public void izvestajDeckoviKategorija(Model model, HttpServletRequest request, HttpServletResponse response,
			Principal p) throws JRException, IOException {

		List<Deck> deckovi = deckRepository.findAll();

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(deckovi);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/deckoviPoKategorijama.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		inputStream.close();

		Map<String, Object> params = new HashMap<>();

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment; filename=deckoviPoKategorijama.pdf");

		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}

	@GetMapping("deckoviPoDatumu")
	public void izvestajDeckoviDatum(Model model, HttpServletRequest request, HttpServletResponse response, Principal p)
			throws JRException, IOException {

		String startDateString = request.getParameter("startDate");
		String endDateString = request.getParameter("endDate");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate startDateLocal = LocalDate.parse(startDateString, formatter);
		LocalDate endDateLocal = LocalDate.parse(endDateString, formatter);

		Date startDate = Date.from(startDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(endDateLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

		List<Deck> deckovi = deckRepository.findByDate(startDate, endDate);

		response.setContentType("text/html");
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(deckovi);
		InputStream inputStream = this.getClass().getResourceAsStream("/jasperreports/deckoviPoDatumu.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		inputStream.close();

		Map<String, Object> params = new HashMap<>();
		params.put("startDate", startDateString);
		params.put("endDate", endDateString);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

		response.setContentType("application/x-download");
		response.addHeader("Content-disposition",
				"attachment; filename=deckoviOd" + startDateString + "Do" + endDateString + ".pdf");

		OutputStream out = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, out);
	}
}
