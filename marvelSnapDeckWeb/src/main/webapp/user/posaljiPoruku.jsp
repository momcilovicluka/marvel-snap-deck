<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
<meta charset="UTF-8">
<title>Posalji poruku</title>
</head>
<body>
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
	</div>
	<h2>Konverzacija sa korisnikom ${primalac.getUsername()}</h2>
	<div class="poruke">
		<c:forEach var="poruka" items="${poruke}">
			<c:choose>
				<c:when
					test="${poruka.getPrijatelji1().getKorisnik1().getUsername() ne posiljaoc.getUsername()}"
					>
					<div class="porukaPrimalac">
						<p style="font-size: 12px; color: rgb(89, 0, 255); margin-bottom: 0px;"
						>${poruka.getDatum()}</p>
						<p>${poruka.getPrijatelji1().getKorisnik1().getUsername()}</p>
						<p>${poruka.getPoruka()}</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="porukaPosiljalac">
						<p style="font-size: 12px; color: rgb(89, 0, 255); margin-bottom: 0px;" >${poruka.getDatum()}</p>
						<p>${poruka.getPrijatelji1().getKorisnik1().getUsername()}</p>
						<p>${poruka.getPoruka()}</p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</div>
	<br>
	<sf:form action="posaljiPoruku" modelAttribute="porukaPrazna"
		method="post">
		<sf:input path="poruka" required="true" />
		<sf:button type="submit">Posalji</sf:button>
	</sf:form>
</body>
</html>