<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<c:if test="${!empty karte}">
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
					<td><img src="data:image/png;base64,${k.slika64 }" width="380"
						height="530" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
