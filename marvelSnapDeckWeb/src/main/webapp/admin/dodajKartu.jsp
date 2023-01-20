<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
<title>Dodaj kartu</title>
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
	<sf:form modelAttribute="KartaImage" action="dodajKartu" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Naziv:</td>
				<td><sf:input path="naziv" required="true" /></td>
			</tr>
			<tr>
				<td>Opis:</td>
				<td><sf:input path="opis" required="true" /></td>
			</tr>
			<tr>
				<td>Tip</td>
				<td><sf:select path="tip" items="${tipovi}" itemValue="idTip"
						itemLabel="tip" required="true" /></td>
			</tr>
			<tr>
				<td>Slika:</td>
				<td><sf:input type="file" path="slika" accept="image/png"
						required="true" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>