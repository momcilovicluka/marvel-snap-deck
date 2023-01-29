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
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8" />
<title>Marvel Snap Deck</title>
</head>
<body
	style="background-image: url('images/background.jpg'); background-color: #000030;">
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0">
						<div class="social d-flex text-center">
							<a href="${pageContext.request.contextPath}"
								class="px-2 py-2 mr-md-1 rounded" style="text-align: center;">HOME</a>
						</div>
					</div>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h1 class="heading-section">
						<i class="fa fa-bar-chart"></i> Statistika
					</h1>
					<c:if test="${!empty kategorije && kategorije.size() > 0 }">
						<div
							style="background-color: #0000aaaa; padding: 20px; border-radius: 50px; display: -ms-inline-grid !important; display: inline-grid !important;"
							class="table-wrapper">
							<h4 style="color: white;">Deckovi po kategoriji</h4>
							<table>
								<tr>
									<th>Kategorija</th>
									<th>Broj deckova</th>
								</tr>
								<c:forEach items="${kategorije}" var="kategorija">
									<tr>
										<td>${kategorija.naziv}</td>
										<td>${kategorija.decks.size()}</td>
									</tr>
								</c:forEach>
							</table>
							<br> <a href="brojDeckovaKategorija"
								style="width: fit-content; margin-left: auto; margin-right: auto;"
								target="_blank"> <i style="font-size: 70px; color: #ff004c;"
								class="fa fa-file-pdf-o"></i></a>
						</div>
					</c:if>
					<br> <br>
					<div
						style="background-color: rgba(0, 0, 170, 0.6); padding: 20px; border-radius: 50px; display: -ms-inline-grid !important; display: inline-grid !important;"
						class="table-wrapper">
						<h4 style="color: white;">Deckovi po datumu kreiranja</h4>
						<div class="login-wrap p-0">
							<form action="deckoviPoDatumu" method="get" class="signin-form">
								<div class="form-group">
									<label style="color: white;" for="startDate">Od:</label> <input
										type="date" name="startDate"
										style="background-color: rgba(0, 0, 255, 0.6); color: white; border-radius: 50px; border: 1px solid rgba(255, 255, 255, 0);" />
								</div>
								<div class="form-group">
									<label style="color: white;" for="endDate">Do:</label> <input
										type="date" name="endDate"
										style="background-color: rgba(0, 0, 255, 0.6); color: white; border-radius: 50px; border: 1px solid rgba(255, 255, 255, 0);" />
								</div>
								<div class="form-group">
									<input type="submit" value="Prikaži"
										class="form-control btn btn-primary submit px-3"
										style="color: white !important;" />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>