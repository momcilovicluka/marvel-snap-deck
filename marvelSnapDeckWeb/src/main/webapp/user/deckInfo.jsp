<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/style.css">
<meta charset="UTF-8" />
<title>Info o decku</title>
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
	<c:if test="${!empty deck}">
		<h1>ID: ${deck.idDeck }</h1>
		<h1>Naziv: ${deck.naziv }</h1>
		<h1>Opis: ${deck.opis }</h1>
		<h1>Kategorija: ${deck.kategorija.naziv }</h1>

		<%@ page import="java.util.List, model.Korisnik, model.Deck"%>
		<%
		Korisnik korisnik = (Korisnik) request.getAttribute("korisnik");
		Deck deck = (Deck) request.getAttribute("deck");
		if (korisnik.getOmiljenis().stream().anyMatch(o -> o.getDeck().getIdDeck() == deck.getIdDeck()))
			out.println("<a href=\"ukloniOmiljeni?idDeck=" + deck.getIdDeck() + "\"><i class=\"fa fa-heart\"></i></a>");
		else
			out.println("<a href=\"dodajOmiljeni?idDeck=" + deck.getIdDeck() + "\"><i class=\"fa fa-heart-o\"></i></a>");
		%>

		<c:if test="${!empty karte}">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Naziv</th>
					<th>Opis</th>
					<th>Tip</th>
					<th>Slika</th>
				</tr>
				<c:forEach items="${karte }" var="k">
					<tr>
						<td>${k.idKarta }</td>
						<td>${k.naziv }</td>
						<td>${k.opis }</td>
						<td>${k.tip.tip }</td>
						<td><img src="data:image/png;base64,${k.slika64 }"
							width="190" height="265" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br>
		<c:if test="${!empty deck.komentars}">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Komentar</th>
					<th>Korisnik</th>
				</tr>
				<c:forEach items="${deck.komentars }" var="k">
					<tr>
						<td>${k.idKomentar }</td>
						<td>${k.komentar }</td>
						<td>${k.korisnik.username }</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		<br>
		<sf:form modelAttribute="komentar" action="dodajKomentar"
			method="post">
			<p>Dodajte komentar:</p>
			<sf:input path="komentar" />
			<sf:input type="hidden" path="idDeck" value="${deck.idDeck }" />
			<sf:input type="hidden" path="idKorisnik"
				value="${korisnik.idKorisnik }" />
			<input type="submit" value="Dodaj Komentar">
		</sf:form>

	</c:if>
</body>
</html>
