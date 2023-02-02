<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log in</title>
</head>
<body style="background-image: url('../images/background.jpg')">
	<c:url var="loginUrl" value="/login" />
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h1 class="heading-section"
						style="font-size: 60px; transform: translateY(-10%)">Marvel
						Snap Deck</h1>
				</div>
			</div>
			<c:if test="${param.error}">
				<div class="row justify-content-center">
					<div class="col-md-6 text-center mb-5">
						<h1 class="heading-section"
							style="font-size: 30px; transform: translateY(-10%)">Invalid
							username or password.</h1>
					</div>
				</div>
			</c:if>
			<div class="row justify-content-center"
				style="transform: translateY(9%)">
				<div class="col-md-6 col-lg-4"
					style="background-color: rgba(0, 0, 45, 0.8); backdrop-filter: blur(15px); border-radius: 20px; padding: 40px;">
					<div class="login-wrap p-0">
						<form action="${loginUrl}" method="post" class="signin-form">
							<%
							String error1 = (String) request.getParameter("error");
							if (error1 != null)
								out.println(
								"<div style='color: white; text-align: center; background-color: #ff32539c; border-radius: 20px; backdrop-filter: blur(8px); font-size: 30px; padding: 0 10; margin-bottom: 15;'>"
										+ "Wrong username or password!" + "</div>");
							%>
							<div class="form-group">
								<input type="text" name="username" placeholder="Enter Username"
									class="form-control" required
									style="backdrop-filter: blur(0px);">
							</div>
							<div class="form-group">
								<input type="password" name="password"
									placeholder="Enter Password" class="form-control" required
									style="backdrop-filter: blur(0px);">
							</div>

							<div class="form-group">
								<input type="submit" value="Log in"
									class="form-control btn btn-primary submit px-3"
									style="color: white !important;">
							</div>

							<div class="form-group d-md-flex">
								<div class="w-50">
									<label class="checkbox-wrap checkbox-primary">Remember
										Me <input type="checkbox" name="remember-me" /> <span
										class="checkmark"></span>
									</label>
								</div>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>
						<div class="social d-flex text-center">
							<a href="/marvelSnapDeck/auth/registerUser"
								class="px-2 py-2 mr-md-1 rounded">Sign Up</a>
						</div>
						<div>
							<br>
						</div>
						<div class="social d-flex text-center"
							style="margin-top: 20px; margin-bottom: 20px; border-top: 1px solid white; border-bottom: 1px solid white; padding-top: 20px; padding-bottom: 20px;">
							<a href="/marvelSnapDeck/auth/sveKarte"
								style="width: 100%; color: white !important; text-decoration: none !important; font-size: 20px;"
								class="px-2 py-2 mr-md-1 rounded">Pregled Karata</a>
						</div>
					</div>
				</div>
			</div>
	</section>
</body>
</html>