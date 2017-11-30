<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<p>Profil uzytkownika ${user.username}</p>
	<table border='1'>
		<tr>
			<th>Nazwa uzytkownika</th>
			<th>Email</th>
		</tr>
		<tr>
			<td>${user.username}</td>
			<td>${user.email}</td>
		</tr>
	</table>

	Wszystkie rozwiazania uzytkownika:
	<table border="1">
		<tr>
			<th>Numer zadania</th>
			<th>Opis rozwiazania</th>
			<th>Data</th>
		</tr>
		<c:forEach var='s' items='${sols}'>
			<tr>
				<td>${s.exerciseId}</td>
				<td>${s.description}</td>
				<td>${s.updated}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>