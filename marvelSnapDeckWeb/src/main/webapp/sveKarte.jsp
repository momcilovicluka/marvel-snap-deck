<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta charset="UTF-8" />
<title>Karte</title>
</head>
<body style="background-image: url('images/background.jpg')">
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
					<td><img src="data:image/png;base64,${k.slika64 }" width="190"
						height="265" /></td>
				</tr>
			</c:forEach>
		</table>

		<c:forEach items="${karte }" var="k" varStatus="status">
			<c:if test="${status.index % 3 == 0 }">
				<div class="containerImage" style="border-radius: 30px;">
			</c:if>
			<div class="box">
				<div class="imgBx">
					<img src="data:image/png;base64,${k.slika64 }" />
				</div>
				<div class="content">
					<div>
						<h2>${k.naziv }</h2>
						<p>Tip: ${k.tip.tip }</p>
						<p>Opis: ${k.opis }</p>
					</div>
				</div>
			</div>
			<c:if test="${status.index % 3 == 2 }">
				</div>
			</c:if>
		</c:forEach>
	</c:if>
</body>
</html>
