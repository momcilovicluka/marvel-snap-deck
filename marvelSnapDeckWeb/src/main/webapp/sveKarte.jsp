<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<meta charset="UTF-8" />
<title>Karte</title>
</head>
<body style="background-image: url('images/background.jpg')">
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

	<form action="sveKarte" method="get" style="margin-top: 20px;">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 col-lg-4">
					<div class="login-wrap p-0" style="text-align: center;">
						<input type="text" name="naziv" placeholder="Naziv karte"
							class="form-control" /> <select name="tip" class="form-control">
							<option value="" style="background-color: #000050;">Svi
								tipovi</option>
							<c:forEach items="${tipovi }" var="t">
								<option value="${t.tip }" style="background-color: #000030;">${t.tip }</option>
							</c:forEach>
						</select> <input type="submit" value="Pretrazi" class="btn btn-primary"
							style="margin-top: 10px; color: white !important;" />
					</div>
				</div>
			</div>
		</div>
	</form>

	<c:if test="${!empty karte}">
		<!-- 
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
	 -->

		<c:forEach items="${karte }" var="k" varStatus="status">
			<c:if test="${status.index % 3 == 0 }">
				<div class="containerImage" style="border-radius: 30px;">
			</c:if>
			<div class="box">
				<div class="imgBx">
					<img src="data:image/png;base64,${k.slika64 }" />
				</div>
				<a href="izvestajKarta?id=${k.idKarta }">
					<div class="content">
						<div>
							<h2>${k.naziv }</h2>
							<p>Tip: ${k.tip.tip }</p>
							<p>Opis: ${k.opis }</p>
						</div>
					</div>
				</a>
			</div>
			<c:if test="${status.index % 3 == 2 }">
				</div>
			</c:if>
		</c:forEach>
	</c:if>

	<a href="#" class="back-to-top"
		style="position: fixed; bottom: 20px; right: 20px; z-index: 9999;"><i
		class="fa fa-chevron-up" style="font-size: 50px;"></i></a>

</body>
</html>
