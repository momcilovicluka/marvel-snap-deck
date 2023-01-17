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
<meta charset="UTF-8" />
<title>Info o decku</title>
</head>
<body>
	<c:if test="${!empty deck}">
		<h1>${deck.idDeck }</h1>
		<h1>${deck.naziv }</h1>
		<h1>${deck.opis }</h1>
		<h1>${deck.kategorija.naziv }</h1>

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
