<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sf:form modelAttribute="user" action="registerAdmin" method="post">
		<table>
			<tr>
				<td>Ime:</td>
				<td><sf:input path="ime" /></td>
			</tr>
			<tr>
				<td>Prezime:</td>
				<td><sf:input path="prezime" /></td>
			</tr>
			<tr>
				<td>Korisnicko ime:</td>
				<td><sf:input path="username" /></td>
			</tr>
			<tr>
				<td>Sifra:</td>
				<td><sf:password path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>