<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>

<html>


<body>
	<a href="/marvelSnapDeck/auth/pretragaKarata">Pretraga karata</a><br/>
	<br>
	<c:url var="loginUrl" value="/login" />
	<c:if test="${not empty param.error}">
		<div class="alert alert-danger">
			<p>Invalid username and password.</p>
		</div>
	</c:if>
	<form action="${loginUrl}" method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"
					placeholder="Enter Username" required></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"
					placeholder="Enter Password" required></td>
			</tr>
			 <tr>
                <td>Remember Me:</td>
                <td><input type="checkbox" name="remember-me" /></td>
            </tr>
			<tr>
				<td><input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" /></td>
				<td><input type="submit" value="Log in"></td>
			</tr>
		</table>
		Nemate nalog? <a href="/marvelSnapDeck/auth/registerUser">Registrujte se</a>
	</form>
</body>
</html>