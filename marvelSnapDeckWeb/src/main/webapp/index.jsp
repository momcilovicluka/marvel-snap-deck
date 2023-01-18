<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css" />
<meta charset="UTF-8" />
<title>Marvel Snap Deck</title>
</head>
<body style="background-image: url('images/background.jpg')">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h1 class="heading-section"
						style="color: white; font-size: 60px; transform: translateY(-25%)">
						Marvel Snap Deck</h1>
				</div>
			</div>
			<div class="row justify-content-center"
				style="transform: translateY(40%)">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
						<div class="social d-flex text-center">
							<a href="/marvelSnapDeck/auth/loginPage"
								class="px-2 py-2 mr-md-1 rounded">Log In</a>
						</div>
						<br />
						<div class="social d-flex text-center">
							<a href="/marvelSnapDeck/auth/sveKarte"
								class="px-2 py-2 mr-md-1 rounded">Pregled
								Karata</a>
						</div>
						<br />
						<security:authorize access="hasRole('ADMIN')">
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/admin/vratiPrazanTip">Dodaj tip</a>
							</div>
							<br />
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/admin/vratiPraznuKategoriju">Dodaj
									kategoriju</a>
							</div>
							<br />
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/auth/getEmptyAdmin">Dodaj admina</a>
							</div>
							<br />
						</security:authorize>
						<security:authorize access="hasRole('KORISNIK')">
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/user/vratiPraznuKartu">Dodaj
									kartu</a>
							</div>
							<br />
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/user/vratiPrazanDeck">Dodaj deck</a>
							</div>
							<br />
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/user/vratiPraznuKartadecka">Dodaj
									kartu u deck</a>
							</div>
							<br />
							<div class="social d-flex text-center">
								<a href="/marvelSnapDeck/user/sviDeckovi">Svi deckovi</a>
							</div>
							<br />
						</security:authorize>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
