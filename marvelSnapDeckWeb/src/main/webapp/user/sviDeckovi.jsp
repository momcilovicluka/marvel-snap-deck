<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Deckovi</title>
</head>
<body>
	<c:if test="${!empty deckovi}">
		<table border="1">
			<tr>
				<th>ID</th>
				<th>Naziv</th>
				<th>Opis</th>
				<th>Kategorija</th>
			</tr>
			<c:forEach items="${deckovi }" var="d">
				<tr>
					<td>${d.idDeck }</td>
					<td>${d.naziv }</td>
					<td>${d.opis }</td>
					<td><a href="/marvelSnapDeck/user/vratiDeck?idDeck=${d.idDeck }">Detalji</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
