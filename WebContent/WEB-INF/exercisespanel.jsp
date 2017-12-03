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
	<a href="Addexercise">Dodaj nowe zadanie</a>
	<table border='1'>
		<tr>
			<th colspan='4'>Zadania:</th>
		</tr>
		<tr>
			<td>Numer zadania</td>
			<td>Nazwa zadania</td>
			<td>Opis</td>
			<td>Akcje</td>
		</tr>
		
			<c:forEach var='e' items='${exercises}'>
			<tr>
				<td>${e.id}</td>
				<td>${e.name}</td>
				<td>${e.description}</td>
				<td><a href="Editexercise?id=${e.id}">Edytuj</a> <a href="Deleteexercise?id=${e.id}">Usuń</a></td>

	</tr>
			</c:forEach>

	
	</table>
</body>
</html>