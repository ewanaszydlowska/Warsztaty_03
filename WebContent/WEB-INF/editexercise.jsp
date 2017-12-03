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
<form action='Editexercise' method='post'>
		<label>Id <input type='text' name='id' value='${id}'></input></label>
		<label> Wpisz nowa nazwe zadania <input type='text' name='exe-name'></input></label>
		<label> Wpisz nowy opis zadania <input type='text' name='exe-desc'></input></label> 
		<input type='submit'></input>
	</form>
</body>
</html>