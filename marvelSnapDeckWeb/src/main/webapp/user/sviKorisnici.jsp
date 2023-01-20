<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css" />
	<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8" />
<title>Korisnici</title>
</head>
<body>
<div class="container">
			<div class="row justify-content-center"
				style="transform: translateY(20%)">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
						<div class="social d-flex text-center">
							<a href="${pageContext.request.contextPath}" class="px-2 py-2 mr-md-1 rounded" style="text-align: center;">HOME</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	<c:if test="${!empty korisnici}">
		<table border="1">
			<tr>
				<th>Ime</th>
				<th>Prezime</th>
				<th>Username</th>
				<th>Prijatelj</th>
			</tr>
			<%@ page import="java.util.List, model.Korisnik"%>
			<%
			Korisnik korisnik = (Korisnik) request.getAttribute("korisnik");
			List<Korisnik> korisnici = (List<Korisnik>) request.getAttribute("korisnici");

			for (Korisnik k : korisnici) {
				out.println("<tr>");
				out.println("<td>" + k.getIme() + "</td>");
				out.println("<td>" + k.getPrezime() + "</td>");
				out.println("<td>" + k.getUsername() + "</td>");
				//out.println(korisnik.getPrijateljis1().size());
				//out.println(k.getPrijateljis2().size());
				if (korisnik.getPrijateljis1().stream().anyMatch(p -> p.getKorisnik2().getIdKorisnik() == k.getIdKorisnik())
				&& korisnik.getPrijateljis2().stream().anyMatch(p -> p.getKorisnik1().getIdKorisnik() == k.getIdKorisnik()))
					out.println("<td> <a href='posaljiPoruku?id=" + k.getIdKorisnik() + "'>Posalji poruku</a> </td>");
				else if (korisnik.getPrijateljis1().stream().anyMatch(p -> p.getKorisnik2().getIdKorisnik() == k.getIdKorisnik())
				&& !korisnik.getPrijateljis2().stream()
						.anyMatch(p -> p.getKorisnik1().getIdKorisnik() == k.getIdKorisnik()))
					out.println("<td> Zahtev poslat </td>");
				else if (!korisnik.getPrijateljis1().stream().anyMatch(p -> p.getKorisnik2().getIdKorisnik() == k.getIdKorisnik())
				&& korisnik.getPrijateljis2().stream().anyMatch(p -> p.getKorisnik1().getIdKorisnik() == k.getIdKorisnik()))
					out.println("<td> <a href='dodajPrijatelja?id=" + k.getIdKorisnik() + "'>Prihvati zahtev</a> </td>");
				else
					out.println("<td> <a href='dodajPrijatelja?id=" + k.getIdKorisnik() + "'>Posalji zahtev</a> </td>");
				out.println("</tr>");
			}
			%>
		</table>
	</c:if>
</body>
</html>
