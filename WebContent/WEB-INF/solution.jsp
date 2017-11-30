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
		<tr>
			<th>Id rozwiazania</th>
			<th>Data dodania</th>
			<th>Opis rozwiazania</th>
			<th>Numer zadania</th>
		</tr>
		<tr>
			<td>${solution.id}</td>
			<td>${solution.updated}</td>
			<td>${solution.description}</td>
			<td>${solution.exerciseId}</td>
		</tr>
	</table>
</html>