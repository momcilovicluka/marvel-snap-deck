<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dodaj kartu</title>
</head>
<body>
	<sf:form modelAttribute="KartaImage" action="dodajKartu" method="post"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>Naziv:</td>
				<td><sf:input path="naziv" required /></td>
			</tr>
			<tr>
				<td>Opis:</td>
				<td><sf:input path="opis" required /></td>
			</tr>
			<tr>
				<td>Tip</td>
				<td><sf:select path="tip" items="${tipovi}" itemValue="idTip"
						itemLabel="tip" /></td>
			</tr>
			<tr>
				<td>Slika:</td>
				<td><sf:input type="file" path="slika" accept="image/png"
						required /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>