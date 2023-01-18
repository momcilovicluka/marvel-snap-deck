<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodaj Deck</title>
</head>
<body>
	<sf:form modelAttribute="Kartadecka" action="dodajKartuUDeck"
		method="post">
		<table>
			<tr>
				<td>Deck</td>
				<td><sf:select path="deck" items="${deckovi}"
						itemValue="idDeck" itemLabel="naziv" required="true" /></td>
			</tr>
			<tr>
				<td>Karta</td>
				<td><sf:select path="karta" items="${karte}"
						itemValue="idKarta" itemLabel="naziv" required="true" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>