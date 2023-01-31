<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodaj Deck</title>
</head>
<body class="img js-fullheight"
style="background-image: url(../images/background.jpg);">
	<div class="container">
		<div class="row justify-content-center"
			style="transform: translateY(20%)">
			<div class="col-md-6 col-lg-4">
				<div class="login-wrap p-0">
					<div class="social d-flex text-center">
						<a href="${pageContext.request.contextPath}"
							class="px-2 py-2 mr-md-1 rounded" style="text-align: center;">HOME</a>
					</div>
				</div>
			</div>
		</div>
	<c:if test="${!empty message}">
		<h3 style="color: rgb(255, 0, 64); text-align: center; font-size: 20px; font-weight: bold; margin-top: 20px;">
		${message}</h3>
	</c:if>
	<div class="row justify-content-center"
			style="transform: translateY(20%)">
			<div class="col-md-6 col-lg-4">
				<div class="login-wrap p-0" style="text-align: center;">
	<sf:form modelAttribute="Kartadecka" action="dodajKartuUDeck" class="signin-form"
		method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td>Deck</td>
				<td><sf:select path="deck" items="${deckoviKorisnika}"
						itemValue="idDeck" itemLabel="naziv" required="true" /></td>
			</tr>
			<tr>
				<td>Karta</td>
				<td><sf:select path="karta" items="${karte}"
						itemValue="idKarta" itemLabel="naziv" required="true" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Dodaj" class="form-control btn btn-primary submit px-3" style="color: white;"></td>
			</tr>
		</table>
	</sf:form>
	</div>
			</div>
		</div>
</body>
</html>