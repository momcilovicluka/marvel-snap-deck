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
<title>Sign up</title>
</head>
<body style="background-image: url('../images/background.jpg')">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center"
				style="transform: translateY(30%)">
				<div class="col-md-6 col-lg-4"
					style="background-color: rgba(0, 0, 30, 0.7); backdrop-filter: blur(8px); border-radius: 20px; padding: 40px;">
					<div class="login-wrap p-0">
						<sf:form modelAttribute="user" action="register" method="post">
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
									<td><input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" /></td>
								</tr>
								<tr>
									<td><input type="submit" value="Register"></td>
								</tr>
							</table>
						</sf:form>
						<a href="${pageContext.request.contextPath}/auth/loginPage">Login</a>
					</div>
				</div>
			</div>
		</div>
</body>
</html>