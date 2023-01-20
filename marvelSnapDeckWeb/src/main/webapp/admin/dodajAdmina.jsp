<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
<title>Insert title here</title>
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
	<sf:form modelAttribute="user" action="registerAdmin" method="post">
		<table>
			<tr>
				<td>Ime:</td>
				<td><sf:input path="ime" required="true" /></td>
			</tr>
			<tr>
				<td>Prezime:</td>
				<td><sf:input path="prezime" required="true" /></td>
			</tr>
			<tr>
				<td>Korisnicko ime:</td>
				<td><sf:input path="username" required="true" /></td>
			</tr>
			<tr>
				<td>Sifra:</td>
				<td><sf:password path="password" required="true" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>