<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<table border='1'>
	<a href="Adduser">Dodaj nowego uzytkownika</a>
		<tr>
			<th colspan='3'>Uzytkownicy:</th>
		</tr>
		<tr>
			<td>Nazwa uzytkownika</td>
			<td>Adres e-mail</td>
			<td>Akcje</td>
		</tr>
		
			<c:forEach var='u' items='${users}'>
			<tr>
				<td>${u.username}</td>
				<td>${u.email}</td>
				<td><a href="Edituser?id=${u.id}">Edytuj</a> <a href="Deleteuser?id=${u.id}">Usuń</a></td>

	</tr>
			</c:forEach>

	
	</table>
</body>
</html>