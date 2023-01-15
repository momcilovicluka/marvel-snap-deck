<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dodaj kategoriju</title>
</head>
<body>
	<sf:form modelAttribute="Kategorija" action="dodajKategoriju"
		method="post">
		<table>
			<tr>
				<td>Kategorija:</td>
				<td><sf:input path="naziv" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Sacuvaj"></td>
			</tr>
		</table>
	</sf:form>
</body>
</html>