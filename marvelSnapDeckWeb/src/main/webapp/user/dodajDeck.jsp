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
	<sf:form modelAttribute="Deck" action="dodajDeck" method="post">
		<table>
			<tr>
				<td>Naziv:</td>
				<td><sf:input path="naziv" /></td>
			</tr>
			<tr>
				<td>Opis:</td>
				<td><sf:input path="opis" /></td>
			</tr>
			<tr>
				<td>Kategorija</td>
				<td><sf:select path="kategorija" items="${kategorije}" itemValue="idKategorija"
						itemLabel="naziv" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>