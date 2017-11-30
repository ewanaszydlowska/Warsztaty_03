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
	<table border='1'>
		<tr>
			<th colspan='3'>Grupy:</th>
		</tr>
		<tr>
			<td>Numer grupy</td>
			<td>Nazwa grupy</td>
			<td>Czlonkowie grupy</td>
		</tr>
		
			<c:forEach var='g' items='${groups}'>
			<tr>
				<td>${g.id}</td>
				<td>${g.name}</td>
				<td><a href="GroupUsers?id=${g.id}">Czlonkowie</a></td>

	</tr>
			</c:forEach>

	
	</table>
</body>
</html>